package quek.undergarden.item.tool.slingshot;

import it.unimi.dsi.fastutil.objects.AbstractObject2ObjectFunction;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ArrowLooseEvent;
import net.neoforged.neoforge.event.entity.player.ArrowNockEvent;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.registry.UGCriteria;
import quek.undergarden.registry.UGEnchantments;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Map;
import java.util.function.Predicate;

public class SlingshotItem extends ProjectileWeaponItem {

	private static final Map<Item, AbstractSlingshotAmmoBehavior> AMMO_REGISTRY = Util.make(new Object2ObjectOpenHashMap<>(), AbstractObject2ObjectFunction::defaultReturnValue);

	public SlingshotItem() {
		super(new Properties()
				.stacksTo(1)
				.durability(192)
				.rarity(Rarity.UNCOMMON)
		);
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 1;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		if (Undergarden.registryAccessStatic() != null) {
			int longevity = stack.getEnchantmentLevel(Undergarden.registryAccessStatic().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(UGEnchantments.LONGEVITY));
			int durability = super.getMaxDamage(stack);
			if (longevity > 0) {
				return durability * (longevity + 1);
			} else return durability;
		}
		return super.getMaxDamage(stack);
	}

	/**
	 * Register your own ammo to use in the slingshot! <br>
	 * In FMLCommonSetupEvent.enqueueWork, call this method and supply it with an item and an ammo behavior. <br>
	 * Use {@link AbstractSlingshotAmmoBehavior} as a base for the ammo behavior and customize it however you want!
	 */
	public static void registerAmmo(ItemLike item, AbstractSlingshotAmmoBehavior behavior) {
		AMMO_REGISTRY.put(item.asItem(), behavior);
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return (stack) -> AMMO_REGISTRY.containsKey(stack.getItem());
	}

	@Override
	public int getDefaultProjectileRange() {
		return 10;
	}

	@Override
	protected void shootProjectile(LivingEntity entity, Projectile projectile, int x, float y, float z, float velocity, @Nullable LivingEntity target) {
		projectile.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, velocity * 2.0F, 1.0F);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player) {
			boolean isCreative = player.getAbilities().instabuild;
			ItemStack projectileStack = player.getProjectile(stack);
			boolean selfSling = stack.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(UGEnchantments.SELF_SLING)) > 0;

			int useTime = getUseDuration(stack, player) - timeLeft;
			useTime = onArrowLoose(stack, level, player, useTime, !projectileStack.isEmpty() || isCreative || selfSling);
			if (useTime < 0) return;

			float velocity = getProjectileVelocity(useTime);

			if (selfSling) {
				if (!player.onGround()) {
					return;
				}
				Vec3 delta = player.getLookAngle();
				player.push(delta.x * (velocity * 2), (delta.y * velocity) + (velocity / 2), delta.z * (velocity * 2));
				if (!level.isClientSide) {
					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
					level.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_SHOOT.get(), SoundSource.PLAYERS, 0.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
			}

			if (!projectileStack.isEmpty() && !selfSling) {
				//correct the projectile stack in creative. player.getProjectile defaults it to an arrow in creative, but slingshots don't shoot arrows!
				if (projectileStack.is(Items.ARROW)) {
					projectileStack = new ItemStack(UGItems.DEPTHROCK_PEBBLE.get());
				}

				if (!((double) velocity < 0.1D)) {
					if (!level.isClientSide) {
						SlingshotProjectile slingshotProjectile = AMMO_REGISTRY.get(projectileStack.getItem()).getProjectile(level, entity.blockPosition(), player, projectileStack);

						slingshotProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity * 2.0F, 1.0F);

						stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));

						int ricochet = stack.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(UGEnchantments.RICOCHET));
						if (ricochet > 0) {
							slingshotProjectile.setRicochetTimes(ricochet + 1);
						}

						level.addFreshEntity(slingshotProjectile);
						level.playSound(null, player.getX(), player.getY(), player.getZ(), AMMO_REGISTRY.get(projectileStack.getItem()).getFiringSound(), SoundSource.PLAYERS, 0.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
						UGCriteria.SLINGSHOT_FIRE.get().trigger((ServerPlayer) player, stack, projectileStack);
					}
					AMMO_REGISTRY.get(projectileStack.getItem()).addAdditionalFiringEffects(level, player);

					if (!isCreative) {
						projectileStack.shrink(1);
						if (projectileStack.isEmpty()) {
							player.getInventory().removeItem(projectileStack);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		boolean hasAmmo = !player.getProjectile(stack).isEmpty();
		boolean selfSling = stack.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(UGEnchantments.SELF_SLING)) > 0;

		InteractionResultHolder<ItemStack> ret = onArrowNock(stack, level, player, hand, hasAmmo);
		if (ret != null) return ret;

		if (!player.getAbilities().instabuild && !hasAmmo && !selfSling) {
			return InteractionResultHolder.fail(stack);
		} else {
			player.startUsingItem(hand);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_DRAW.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
			return InteractionResultHolder.consume(stack);
		}
	}

	public static InteractionResultHolder<ItemStack> onArrowNock(ItemStack stack, Level level, Player player, InteractionHand hand, boolean hasAmmo) {
		ArrowNockEvent event = new ArrowNockEvent(player, stack, hand, level, hasAmmo);
		if (NeoForge.EVENT_BUS.post(event).isCanceled())
			return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
		return event.getAction();
	}

	public static int onArrowLoose(ItemStack stack, Level level, Player player, int charge, boolean hasAmmo) {
		ArrowLooseEvent event = new ArrowLooseEvent(player, stack, level, charge, hasAmmo);
		if (NeoForge.EVENT_BUS.post(event).isCanceled())
			return -1;
		return event.getCharge();
	}

	public static float getProjectileVelocity(int charge) {
		float f = (float) charge / 5.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return repair.is(ItemTags.PLANKS);
	}
}