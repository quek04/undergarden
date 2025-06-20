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
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.*;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.registry.UGDataComponents;

import java.util.Optional;

public class UGBucketItem extends Item {

	public UGBucketItem(Properties properties) {
		super(properties);
		DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack stack) {
		var copy = new ItemStack(this);
		copy.applyComponents(stack.getComponents());

		var tank = copy.getCapability(Capabilities.FluidHandler.ITEM);
		if (tank != null) {
			tank.drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
		}

		return copy;
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		var tank = stack.getCapability(Capabilities.FluidHandler.ITEM);
		if (tank != null) {
			return tank.getFluidInTank(0).getAmount() > 0;
		}
		return false;
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
	public UseAnim getUseAnimation(ItemStack stack) {
		return isMilkBucket(stack) ? UseAnim.DRINK : UseAnim.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return isMilkBucket(stack) ? 32 : 0;
	}

	@Override
	public int getBurnTime(ItemStack stack, RecipeType<?> type) {
		var fluid = stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).copy();
		if (fluid.isEmpty() && fluid.getAmount() >= FluidType.BUCKET_VOLUME) {
			FurnaceFuel fuel = fluid.getFluid().getBucket().builtInRegistryHolder().getData(NeoForgeDataMaps.FURNACE_FUELS);
			return fuel == null ? 0 : fuel.burnTime();
		}

		return super.getBurnTime(stack, type);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
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
				InteractionResultHolder<ItemStack> pickup = this.tryPickupFluid(stack, level, player);
				if (pickup.getResult().consumesAction()) {
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

						return InteractionResultHolder.sidedSuccess(result, level.isClientSide());
					}
				}
				//entity interaction is handled in interactLivingEntity below
			} else {
				//place fluid interaction
				if (hasFluid(stack)) {
					InteractionResultHolder<ItemStack> place = this.tryPlaceFluid(stack, level, player, hand);
					if (place.getResult().consumesAction()) {
						//place entity if exists
						if (getBucketedEntity(place.getObject()).isPresent()) {
							ItemStack emptyBucket = this.spawnEntityFromBucket(player, level, place.getObject(), relativeBlockPos, !player.hasInfiniteMaterials());
							ItemUtils.createFilledResult(stack, player, emptyBucket);
							return InteractionResultHolder.sidedSuccess(emptyBucket, level.isClientSide());
						}
						return place;
					}
				} else if (getBucketedEntity(stack).isPresent()) {
					//place entity interaction
					ItemStack emptyBucket = this.spawnEntityFromBucket(player, level, stack, relativeBlockPos, !player.hasInfiniteMaterials());
					if (!player.hasInfiniteMaterials()) player.setItemInHand(hand, emptyBucket);
					return InteractionResultHolder.sidedSuccess(emptyBucket, level.isClientSide());
				} else if (containsBlock(stack)) {
					//place block interaction
					BlockState block = stack.get(UGDataComponents.STORED_BLOCK);
					if (block != null) {
						InteractionResult interactionResult = block.getBlock().asItem().useOn(new UseOnContext(player, hand, blockHitResult));
						if (interactionResult.consumesAction()) {
							var workBucket = stack.copy();
							workBucket.remove(UGDataComponents.STORED_BLOCK);
							ItemUtils.createFilledResult(stack, player, workBucket);
							return InteractionResultHolder.sidedSuccess(workBucket, level.isClientSide());
						}
					}
				}
			}
		}
		if (isMilkBucket(stack)) {
			return ItemUtils.startUsingInstantly(level, player, hand);
		}
		return InteractionResultHolder.pass(stack);
	}

	public ItemStack spawnEntityFromBucket(@Nullable Player player, Level level, ItemStack stack, BlockPos pos, boolean removeTag) {
		if (level instanceof ServerLevel serverLevel) {
			Optional<EntityType<?>> entityType = getBucketedEntity(stack);
			if (entityType.isPresent()) {
				Entity entity = entityType.get().spawn(serverLevel, stack, null, pos, MobSpawnType.BUCKET, true, false);
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
		var tank = stack.getCapability(Capabilities.FluidHandler.ITEM);
		if (entity instanceof Cow cow && !cow.isBaby()) {
			if (tank != null && tank.fill(new FluidStack(NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE) > 0) {
				player.playSound(SoundEvents.COW_MILK);
				return InteractionResult.SUCCESS;
			}
		} else if (entity instanceof Goat goat && !goat.isBaby()) {
			if (tank != null && tank.fill(new FluidStack(NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE) > 0) {
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
						CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, bucketStack);
					}

					player.setItemInHand(hand, workBucket);
					entity.discard();
					return InteractionResult.sidedSuccess(level.isClientSide());
				}
			}
		}

		return InteractionResult.PASS;
	}

	// [VanillaCopy] of MilkBucketItem#finishUsingItem
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (entity instanceof ServerPlayer player) {
			CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
			player.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!level.isClientSide()) {
			entity.removeEffectsCuredBy(EffectCures.MILK);
		}

		if (entity instanceof Player player && !player.hasInfiniteMaterials()) {
			//instead of shrinking the stack, drain the fluid
			var tank = stack.getCapability(Capabilities.FluidHandler.ITEM);
			if (tank != null) {
				tank.drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
			}
		}

		return stack;
	}

	private InteractionResultHolder<ItemStack> tryPlaceFluid(ItemStack stack, Level level, Player player, InteractionHand hand) {
		if (stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).getAmount() < FluidType.BUCKET_VOLUME)
			return InteractionResultHolder.pass(stack);

		var trace = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (trace.getType() != HitResult.Type.BLOCK)
			return InteractionResultHolder.pass(stack);

		var pos = trace.getBlockPos();
		if (level.mayInteract(player, pos)) {
			var targetPos = pos.relative(trace.getDirection());

			if (player.mayUseItemAt(targetPos, trace.getDirection().getOpposite(), stack)) {
				var result = FluidUtil.tryPlaceFluid(player, level, hand, targetPos, stack, stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).copy().copyWithAmount(FluidType.BUCKET_VOLUME));
				if (result.isSuccess()) {
					ItemStack emptyStack = ItemUtils.createFilledResult(stack, player, result.getResult());
					if (player instanceof ServerPlayer sp) {
						CriteriaTriggers.PLACED_BLOCK.trigger(sp, targetPos, stack);
					}

					player.awardStat(Stats.ITEM_USED.get(this));

					return InteractionResultHolder.sidedSuccess(emptyStack, level.isClientSide());
				}
			}
		}

		return InteractionResultHolder.fail(stack);
	}

	private InteractionResultHolder<ItemStack> tryPickupFluid(ItemStack stack, Level level, Player player) {
		if (!isBucketEmpty(stack))
			return InteractionResultHolder.pass(stack);

		var trace = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (trace.getType() != HitResult.Type.BLOCK)
			return InteractionResultHolder.pass(stack);

		var pos = trace.getBlockPos();
		if (level.mayInteract(player, pos)) {
			var direction = trace.getDirection();
			if (player.mayUseItemAt(pos, direction, stack)) {
				var result = FluidUtil.tryPickUpFluid(stack, player, level, pos, direction);

				if (result.isSuccess()) {
					ItemStack filledStack = ItemUtils.createFilledResult(stack, player, result.getResult());
					if (!level.isClientSide()) {
						CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, stack);
					}

					return InteractionResultHolder.success(filledStack);
				}
			}
		}

		return InteractionResultHolder.fail(stack);
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
