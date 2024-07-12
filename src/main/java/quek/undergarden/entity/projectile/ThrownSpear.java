package quek.undergarden.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;

public class ThrownSpear extends AbstractArrow implements ItemSupplier {
	private static final EntityDataAccessor<Boolean> ENCHANTED_ID = SynchedEntityData.defineId(ThrownSpear.class, EntityDataSerializers.BOOLEAN);
	private static final ItemStack DEFAULT_STACK = new ItemStack(UGItems.SPEAR.get());
	private boolean dealtDamage;

	public ThrownSpear(EntityType<? extends ThrownSpear> entityType, Level level) {
		super(entityType, level);
	}

	public ThrownSpear(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
		super(UGEntityTypes.SPEAR.get(), shooter, level, pickupItemStack, null);
		this.entityData.set(ENCHANTED_ID, pickupItemStack.hasFoil());
	}

	public ThrownSpear(Level level, double x, double y, double z, ItemStack pickupItemStack) {
		super(UGEntityTypes.SPEAR.get(), x, y, z, level, pickupItemStack, pickupItemStack);
		this.entityData.set(ENCHANTED_ID, pickupItemStack.hasFoil());
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ENCHANTED_ID, false);
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 4) {
			this.dealtDamage = true;
		}
		super.tick();
	}

	public boolean isFoil() {
		return this.entityData.get(ENCHANTED_ID);
	}

	@Nullable
	@Override
	protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
		return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity victim = result.getEntity();
		float damage = 8.0F;
		Entity owner = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, owner == null ? this : owner);
		if (this.level() instanceof ServerLevel level) {
			damage = EnchantmentHelper.modifyDamage(level, this.getWeaponItem(), victim, damagesource, damage);
		}

		this.dealtDamage = true;
		if (victim.hurt(damagesource, damage)) {
			if (victim.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (this.level() instanceof ServerLevel level) {
				EnchantmentHelper.doPostAttackEffectsWithItemSource(level, victim, damagesource, this.getWeaponItem());
			}

			if (victim instanceof LivingEntity entity) {
				this.doKnockback(entity, damagesource);
				this.doPostHurtEffects(entity);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
		this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
	}

	@Override
	protected boolean tryPickup(Player player) {
		return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
	}

	@Override
	public void playerTouch(Player pEntity) {
		if (this.ownedBy(pEntity) || this.getOwner() == null) {
			super.playerTouch(pEntity);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.dealtDamage = tag.getBoolean("DealtDamage");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("DealtDamage", this.dealtDamage);
	}

	@Override
	public void tickDespawn() {
		if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
			super.tickDespawn();
		}
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return true;
	}

	@Override
	public ItemStack getItem() {
		return DEFAULT_STACK;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return DEFAULT_STACK;
	}
}