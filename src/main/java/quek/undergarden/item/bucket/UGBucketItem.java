package quek.undergarden.item.bucket;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGDataComponents;

import java.util.Optional;

//TODO GRAHHHHHHHH what the fuck is this new transfer API
public class UGBucketItem extends Item {

	public UGBucketItem(Properties properties) {
		super(properties);
		DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
	}

	@Override
	public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
		var copy = new ItemStack(this);
		copy.applyComponents(instance.typeHolder().components());

		var container = VanillaContainerWrapper.of(new SimpleContainer(copy) {
			// Override to avoid clamping oversized stacks to their max stack size, just in case.
			@Override
			public void setItem(int slot, ItemStack stack, boolean performSideEffects) {
				getItems().set(slot, stack);
			}
		});
		var itemAccess = ItemAccess.forHandlerIndex(container, 0);
		var resourceHandler = itemAccess.getCapability(Capabilities.Fluid.ITEM);
		if (resourceHandler != null) {
			try (var tx = Transaction.openRoot()) {
				resourceHandler.extract(resourceHandler.getResource(0), FluidType.BUCKET_VOLUME, tx);
				tx.commit();
			}
		}

		return ItemStackTemplate.fromNonEmptyStack(copy);
	}

	@Override
	public Component getName(ItemStack stack) {
		//entity names are formatted as Cloggrum Bucket of (X)
		//block/fluid names are formatted as Cloggrum (X) Bucket
		if (!stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).isEmpty()) {
			return Component.translatable(this.getDescriptionId() + ".entity", Component.translatable(getBucketedEntity(stack).map(EntityType::getDescriptionId).orElse("?")));
		} else if (!stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).isEmpty()) {
			return Component.translatable(this.getDescriptionId() + ".block", Component.translatable(stack.get(UGDataComponents.STORED_FLUID).getFluidType().getDescriptionId()));
		} else if (!stack.getOrDefault(UGDataComponents.STORED_BLOCK, Blocks.AIR.defaultBlockState()).isAir()) {
			return Component.translatable(this.getDescriptionId() + ".block", Component.translatable(stack.get(UGDataComponents.STORED_BLOCK).getBlock().getDescriptionId()));
		}
		return super.getName(stack);
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return isMilkBucket(stack) ? ItemUseAnimation.DRINK : ItemUseAnimation.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return isMilkBucket(stack) ? 32 : 0;
	}

	@Override
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
		var fluid = stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).copy();
		if (fluid.isEmpty() && fluid.getAmount() >= FluidType.BUCKET_VOLUME) {
			FurnaceFuel fuel = fluid.getFluid().getBucket().builtInRegistryHolder().getData(NeoForgeDataMaps.FURNACE_FUELS);
			return fuel == null ? 0 : fuel.burnTime();
		}

		return super.getBurnTime(stack, recipeType, fuelValues);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		boolean isEmpty = isBucketEmpty(stack);

		BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, isEmpty ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
		if (blockHitResult.getType() == HitResult.Type.BLOCK) {
			BlockPos hitBlockPos = blockHitResult.getBlockPos();
			BlockState hitBlockState = level.getBlockState(hitBlockPos);
			Direction hitDirection = blockHitResult.getDirection();
			BlockPos relativeBlockPos = hitBlockPos.relative(hitDirection);
			if (isEmpty) {
				//pickup fluid interaction
				InteractionResult pickup = this.tryPickupFluid(stack, level, player);
				if (pickup.consumesAction()) {
					return pickup;
				}
				//pickup block interaction
				if (hitBlockState.getBlock() instanceof BucketPickup bucketPickup && !(hitBlockState.getBlock() instanceof LiquidBlock)) {
					var resultStack = bucketPickup.pickupBlock(player, level, hitBlockPos, hitBlockState);
					if (!resultStack.isEmpty()) {
						player.awardStat(Stats.ITEM_USED.get(this));
						bucketPickup.getPickupSound(hitBlockState).ifPresent(event -> player.playSound(event, 1.0F, 1.0F));
						level.gameEvent(player, GameEvent.FLUID_PICKUP, hitBlockPos);
						ItemStack workBucket = stack.copyWithCount(1);
						if (resultStack.getItem() instanceof BucketItem bucket && bucket.content != Fluids.EMPTY) {
							workBucket.set(UGDataComponents.STORED_FLUID, SimpleFluidContent.copyOf(new FluidStack(bucket.content, FluidType.BUCKET_VOLUME)));
						} else {
							workBucket.set(UGDataComponents.STORED_BLOCK, hitBlockState);
						}
						if (!level.isClientSide()) {
							CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, resultStack);
						}
						var result = ItemUtils.createFilledResult(stack, player, workBucket);
						return InteractionResult.SUCCESS.heldItemTransformedTo(result);
					}
				}
				//entity interaction is handled in interactLivingEntity below
			} else {
				//place fluid interaction
				if (hasFluid(stack)) {
					InteractionResult place = this.tryPlaceFluid(stack, level, player, hand);
					if (place instanceof InteractionResult.Success success && success.heldItemTransformedTo() != null) {
						//place entity if exists
						if (getBucketedEntity(success.heldItemTransformedTo()).isPresent()) {
							ItemStack emptyBucket = this.spawnEntityFromBucket(player, level, success.heldItemTransformedTo(), relativeBlockPos, !player.hasInfiniteMaterials());
							ItemUtils.createFilledResult(stack, player, emptyBucket);
							return InteractionResult.SUCCESS.heldItemTransformedTo(emptyBucket);
						}
						return place;
					}
				} else if (getBucketedEntity(stack).isPresent()) {
					//place entity interaction
					ItemStack emptyBucket = this.spawnEntityFromBucket(player, level, stack, relativeBlockPos, !player.hasInfiniteMaterials());
					return InteractionResult.SUCCESS.heldItemTransformedTo(emptyBucket);
				} else if (containsBlock(stack)) {
					//place block interaction
					BlockState block = stack.get(UGDataComponents.STORED_BLOCK);
					if (block != null) {
						var workBucket = stack.copy();
						InteractionResult interactionResult = block.getBlock().asItem().useOn(new UseOnContext(player, hand, blockHitResult));
						if (interactionResult.consumesAction()) {
							if (!player.hasInfiniteMaterials()) workBucket.remove(UGDataComponents.STORED_BLOCK);
							return InteractionResult.SUCCESS.heldItemTransformedTo(workBucket);
						}
					}
				}
			}
		}
		if (isMilkBucket(stack)) {
			return ItemUtils.startUsingInstantly(level, player, hand);
		}
		return InteractionResult.PASS;
	}

	public ItemStack spawnEntityFromBucket(@Nullable Player player, Level level, ItemStack stack, BlockPos pos, boolean removeTag) {
		if (level instanceof ServerLevel serverLevel) {
			Optional<EntityType<?>> entityType = getBucketedEntity(stack);
			if (entityType.isPresent()) {
				Entity entity = entityType.get().spawn(serverLevel, stack, null, pos, EntitySpawnReason.BUCKET, true, false);
				if (entity instanceof Bucketable bucketable) {
					CustomData customdata = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
					bucketable.loadFromBucketTag(customdata.copyTag());
					bucketable.setFromBucket(true);
				}
				if (player != null) {
					serverLevel.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
				}
				ItemStack workBucket = stack.copy();
				if (removeTag) workBucket.remove(DataComponents.BUCKET_ENTITY_DATA);
				return workBucket;
			}
		}
		return stack.copy();
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return isBucketEmpty(stack) ? 16 : 1;
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		var container = VanillaContainerWrapper.of(new SimpleContainer(stack) {
			// Override to avoid clamping oversized stacks to their max stack size, just in case.
			@Override
			public void setItem(int slot, ItemStack stack, boolean performSideEffects) {
				getItems().set(slot, stack);
			}
		});
		var itemAccess = ItemAccess.forHandlerIndex(container, 0);
		var resourceHandler = itemAccess.getCapability(Capabilities.Fluid.ITEM);
		try (var tx = Transaction.openRoot()) {
			if (entity instanceof Cow cow && !cow.isBaby()) {
				if (resourceHandler != null && resourceHandler.insert(FluidResource.of(NeoForgeMod.MILK.get()), FluidType.BUCKET_VOLUME, tx) > 0) {
					tx.commit();
					player.playSound(SoundEvents.COW_MILK);
					return InteractionResult.SUCCESS;
				}
			} else if (entity instanceof Goat goat && !goat.isBaby()) {
				if (resourceHandler != null && resourceHandler.insert(FluidResource.of(NeoForgeMod.MILK.get()), FluidType.BUCKET_VOLUME, tx) > 0) {
					tx.commit();
					player.playSound(goat.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_MILK : SoundEvents.GOAT_MILK);
					return InteractionResult.SUCCESS;
				}
			} else if (getBucketedEntity(stack).isEmpty() && entity instanceof Bucketable bucketable && entity.isAlive()) {
				//pickup entity interaction
				var workBucket = stack.copy();
				var bucketStack = bucketable.getBucketItemStack();
				if (bucketStack.getItem() instanceof BucketItem bucket) {
					Fluid containedFluid = bucket.content;
					if (stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).is(containedFluid)) {
						entity.playSound(bucketable.getPickupSound());
						bucketable.saveToBucketTag(workBucket);
						String id = entity.getEncodeId();
						if (id != null) {
							CustomData.update(DataComponents.BUCKET_ENTITY_DATA, workBucket, tag -> tag.putString("id", id));
						}
						Level level = entity.level();
						if (!level.isClientSide()) {
							CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucketStack);
						}

						entity.discard();
						return InteractionResult.SUCCESS.heldItemTransformedTo(workBucket);
					}
				}
			}

			return InteractionResult.PASS;
		}
	}

	// [VanillaCopy] of MilkBucketItem#finishUsingItem
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (entity instanceof ServerPlayer player) {
			CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
			player.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!level.isClientSide()) {
			var removeEffects = new ClearAllStatusEffectsConsumeEffect();
			removeEffects.apply(level, stack, entity);
		}

		if (entity instanceof Player player && !player.hasInfiniteMaterials()) {
			//instead of shrinking the stack, drain the fluid
			var container = VanillaContainerWrapper.of(new SimpleContainer(stack) {
				// Override to avoid clamping oversized stacks to their max stack size, just in case.
				@Override
				public void setItem(int slot, ItemStack stack, boolean performSideEffects) {
					getItems().set(slot, stack);
				}
			});
			var itemAccess = ItemAccess.forHandlerIndex(container, 0);
			var resourceHandler = itemAccess.getCapability(Capabilities.Fluid.ITEM);
			try (var tx = Transaction.openRoot()) {
				if (resourceHandler != null) {
					resourceHandler.extract(resourceHandler.getResource(0), FluidType.BUCKET_VOLUME, tx);
					tx.commit();
				}
			}
		}

		return stack;
	}

	private InteractionResult tryPlaceFluid(ItemStack stack, Level level, Player player, InteractionHand hand) {
		if (stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).getAmount() < FluidType.BUCKET_VOLUME)
			return InteractionResult.PASS;

		var trace = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (trace.getType() != HitResult.Type.BLOCK)
			return InteractionResult.PASS;

		var pos = trace.getBlockPos();
		if (level.mayInteract(player, pos)) {
			var targetPos = pos.relative(trace.getDirection());

			if (player.mayUseItemAt(targetPos, trace.getDirection().getOpposite(), stack)) {
				var result = FluidUtil.tryPlaceFluid((ResourceHandler<FluidResource>) null, player, level, hand, targetPos);
				if (!result.isEmpty()) {
					ItemStack emptyStack = ItemUtils.createFilledResult(stack, player, result.getResult());
					if (player instanceof ServerPlayer sp) {
						CriteriaTriggers.PLACED_BLOCK.trigger(sp, targetPos, stack);
					}

					player.awardStat(Stats.ITEM_USED.get(this));

					return InteractionResult.SUCCESS.heldItemTransformedTo(emptyStack);
				}
			}
		}

		return InteractionResult.FAIL;
	}

	private InteractionResult tryPickupFluid(ItemStack stack, Level level, Player player) {
		if (!isBucketEmpty(stack))
			return InteractionResult.PASS;

		var trace = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (trace.getType() != HitResult.Type.BLOCK)
			return InteractionResult.PASS;

		var pos = trace.getBlockPos();
		if (level.mayInteract(player, pos)) {
			var direction = trace.getDirection();
			if (player.mayUseItemAt(pos, direction, stack)) {
				var result = FluidUtil.tryPickupFluid(null, player, level, pos, direction);

				if (!result.isEmpty()) {
					ItemStack filledStack = ItemUtils.createFilledResult(stack, player, result.getResult());
					if (!level.isClientSide()) {
						CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, stack);
					}

					return InteractionResult.SUCCESS.heldItemTransformedTo(filledStack);
				}
			}
		}

		return InteractionResult.FAIL;
	}

	private static boolean isMilkBucket(ItemStack stack) {
		return stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).is(NeoForgeMod.MILK.get());
	}

	public static boolean isBucketEmpty(ItemStack bucket) {
		return !hasFluid(bucket) && !containsBlock(bucket) && getBucketedEntity(bucket).isEmpty();
	}

	public static boolean hasFluid(ItemStack bucket) {
		return !bucket.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).isEmpty();
	}

	public static boolean containsBlock(ItemStack bucket) {
		return !bucket.getOrDefault(UGDataComponents.STORED_BLOCK, Blocks.AIR.defaultBlockState()).isAir();
	}

	public static Optional<EntityType<?>> getBucketedEntity(ItemStack bucket) {
		if (bucket.get(DataComponents.BUCKET_ENTITY_DATA) != null) {
			return EntityType.byString(bucket.get(DataComponents.BUCKET_ENTITY_DATA).copyTag().getString("id"));
		}
		return Optional.empty();
	}
}
