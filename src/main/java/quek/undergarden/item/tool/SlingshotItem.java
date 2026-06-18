 package quek.undergarden.item.tool;

 import net.minecraft.server.level.ServerPlayer;
 import net.minecraft.sounds.SoundSource;
 import net.minecraft.stats.Stats;
 import net.minecraft.world.InteractionHand;
 import net.minecraft.world.InteractionResult;
 import net.minecraft.world.entity.LivingEntity;
 import net.minecraft.world.entity.player.Player;
 import net.minecraft.world.entity.projectile.Projectile;
 import net.minecraft.world.item.Item;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.ItemUseAnimation;
 import net.minecraft.world.item.ProjectileWeaponItem;
 import net.minecraft.world.level.Level;
 import net.minecraft.world.phys.Vec3;
 import net.neoforged.neoforge.common.NeoForge;
 import net.neoforged.neoforge.event.entity.player.ArrowLooseEvent;
 import net.neoforged.neoforge.event.entity.player.ArrowNockEvent;
 import org.jspecify.annotations.Nullable;
 import quek.undergarden.Undergarden;
 import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
 import quek.undergarden.registry.*;

 import java.util.function.Predicate;

public class SlingshotItem extends ProjectileWeaponItem {

	public SlingshotItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		if (Undergarden.registryAccessStatic() != null) {
			int longevity = stack.getEnchantmentLevel(Undergarden.registryAccessStatic().getOrThrow(UGEnchantments.LONGEVITY));
			int durability = super.getMaxDamage(stack);
			if (longevity > 0) {
				return durability * (longevity + 1);
			} else return durability;
		}
		return super.getMaxDamage(stack);
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return (stack) -> stack.has(UGDataComponents.SLINGSHOT_AMMO);
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
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player) {
			boolean isCreative = player.getAbilities().instabuild;
			ItemStack projectileStack = player.getProjectile(stack);
			boolean selfSling = stack.getEnchantmentLevel(level.registryAccess().getOrThrow(UGEnchantments.SELF_SLING)) > 0;

			int useTime = getUseDuration(stack, player) - timeLeft;
			useTime = onArrowLoose(stack, level, player, useTime, !projectileStack.isEmpty() || isCreative || selfSling);
			if (useTime < 0) return isCreative;

			float velocity = getProjectileVelocity(useTime);

			if (selfSling) {
				if (!player.onGround()) {
					return isCreative;
				}
				Vec3 delta = player.getLookAngle();
				player.push(delta.x * (velocity * 2), (delta.y * velocity) + (velocity / 2), delta.z * (velocity * 2));
				if (!level.isClientSide()) {
					stack.hurtAndBreak(1, player, player.getUsedItemHand());
					level.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_SHOOT.get(), SoundSource.PLAYERS, 0.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
			}

			if (!projectileStack.isEmpty() && !selfSling) {
				if (!((double) velocity < 0.1D)) {
					if (!level.isClientSide()) {
						SlingshotProjectile slingshotProjectile = new SlingshotProjectile(player, level, projectileStack);

						slingshotProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity * 2.0F, 1.0F);

						stack.hurtAndBreak(1, player, player.getUsedItemHand());

						int ricochet = stack.getEnchantmentLevel(level.registryAccess().getOrThrow(UGEnchantments.RICOCHET));
						if (ricochet > 0) {
							slingshotProjectile.setRicochetTimes(ricochet + 1);
						}

						level.addFreshEntity(slingshotProjectile);
						level.playSound(null, player.getX(), player.getY(), player.getZ(), projectileStack.get(UGDataComponents.SLINGSHOT_AMMO).shootSound(), SoundSource.PLAYERS, 0.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
						UGCriteria.SLINGSHOT_FIRE.get().trigger((ServerPlayer) player, stack, projectileStack);
					}

					if (!isCreative) {
						projectileStack.shrink(1);
						if (projectileStack.isEmpty()) {
							player.getInventory().removeItem(projectileStack);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		boolean hasAmmo = !player.getProjectile(stack).isEmpty();
		boolean selfSling = stack.getEnchantmentLevel(level.registryAccess().getOrThrow(UGEnchantments.SELF_SLING)) > 0;

		InteractionResult ret = onArrowNock(stack, level, player, hand, hasAmmo);
		if (ret != null) return ret;

		if (!player.hasInfiniteMaterials() && !hasAmmo && !selfSling) {
			return InteractionResult.FAIL;
		} else {
			player.startUsingItem(hand);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_DRAW.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
			return InteractionResult.CONSUME;
		}
	}

	public static InteractionResult onArrowNock(ItemStack stack, Level level, Player player, InteractionHand hand, boolean hasAmmo) {
		ArrowNockEvent event = new ArrowNockEvent(player, stack, hand, level, hasAmmo);
		if (NeoForge.EVENT_BUS.post(event).isCanceled())
			return InteractionResult.FAIL;
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
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.BOW;
	}

	@Override
	public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack projectileWeaponItem) {
		return new ItemStack(UGItems.DEPTHROCK_PEBBLE.get());
	}
}