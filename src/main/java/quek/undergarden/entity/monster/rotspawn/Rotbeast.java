package quek.undergarden.entity.monster.rotspawn;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGSoundEvents;

public class Rotbeast extends RotspawnMonster {

	private int attackTimer;

	public Rotbeast(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 3.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.22D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.ROTBEAST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.ROTBEAST_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.ROTBEAST_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.ROTBEAST_STEP.get(), 0.15F, 1.0F);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity entity) {
		this.attackTimer = 10;
		this.level().broadcastEntityEvent(this, (byte) 4);
		float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float damage = (int) attackDamage > 0 ? attackDamage / 2.0F + (float) this.getRandom().nextInt((int) attackDamage) : attackDamage;
		DamageSource damageSource = this.damageSources().mobAttack(this);
		boolean hurt = entity.hurtServer(level, damageSource, damage);
		if (hurt) {
			double knockbackResistance = entity instanceof LivingEntity livingEntity ? livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) : 0.0;
			double scale = Math.max(0.0D, 1.0D - knockbackResistance);
			entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.4F * scale, 0.0));
			EnchantmentHelper.doPostAttackEffects(level, entity, damageSource);
		}

		this.playSound(UGSoundEvents.ROTBEAST_ATTACK.get(), 1.0F, 1.0F);
		return hurt;
	}

	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			this.playSound(UGSoundEvents.ROTBEAST_ATTACK.get(), 1.0F, 1.0F);
		} else {
			super.handleEntityEvent(id);
		}
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}
}