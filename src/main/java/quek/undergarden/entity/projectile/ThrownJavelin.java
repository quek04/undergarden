package quek.undergarden.entity.projectile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class ThrownJavelin extends AbstractArrow {

	private boolean dealtDamage;

	public ThrownJavelin(EntityType<? extends ThrownJavelin> entityType, Level level) {
		super(entityType, level);
	}

	public ThrownJavelin(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
		super(UGEntityTypes.JAVELIN.get(), shooter, level, pickupItemStack, null);
	}

	public ThrownJavelin(Level level, double x, double y, double z, ItemStack pickupItemStack) {
		super(UGEntityTypes.JAVELIN.get(), x, y, z, level, pickupItemStack, pickupItemStack);
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 4) {
			this.dealtDamage = true;
		}
		super.tick();
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
		if (victim.hurtOrSimulate(damagesource, damage)) {
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
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.dealtDamage = input.getBooleanOr("dealt_damage", false);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putBoolean("dealt_damage", this.dealtDamage);
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
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(UGItems.JAVELIN.get());
	}

	@Override
	public ItemStack getWeaponItem() {
		return this.getPickupItemStackOrigin();
	}
}