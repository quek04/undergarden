package quek.undergarden.entity.monster.cavern;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.DepthrockPebble;
import quek.undergarden.registry.UGSoundEvents;

public class Sploogie extends CavernMonster implements RangedAttackMob {

	public Sploogie(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 40, 15.0F));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return CavernMonster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.SPLOOGIE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.SPLOOGIE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.SPLOOGIE_DEATH.get();
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		DepthrockPebble pebble = new DepthrockPebble(this.level(), this);
		double xDistance = target.getX() - this.getX();
		double yDistance = target.getY(0.3333333333333333D) - pebble.getY();
		double zDistance = target.getZ() - this.getZ();
		double yMath = Mth.sqrt((float) ((xDistance * xDistance) + (zDistance * zDistance)));
		pebble.shoot(xDistance, yDistance + yMath * 0.1D, zDistance, 1.6F, 1.0F);
		this.playSound(UGSoundEvents.SPLOOGIE_SPIT.get(), 1.0F, this.getVoicePitch());
		this.level().addFreshEntity(pebble);
	}
}