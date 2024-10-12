package quek.undergarden.entity.monster.rotspawn;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.entity.animal.Mog;
import quek.undergarden.entity.monster.stoneborn.Stoneborn;
import quek.undergarden.entity.projectile.RotbelcherProjectile;
import quek.undergarden.registry.UGSoundEvents;

public class Rotbelcher extends RotspawnMonster {

	private static final EntityDataAccessor<Boolean> IS_CHARGING = SynchedEntityData.defineId(Rotbelcher.class, EntityDataSerializers.BOOLEAN);
	public AnimationState shootAnimation = new AnimationState();
	public AnimationState attackAnimation = new AnimationState();

	public Rotbelcher(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RotbelcherShootGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false) {
			@Override
			public boolean canUse() {
				return this.mob.getTarget() != null && this.mob.getTarget().distanceToSqr(this.mob) < 40.0D && super.canUse();
			}
		});
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new RotspawnTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new RotspawnTargetGoal<>(this, Stoneborn.class, true));
		this.targetSelector.addGoal(3, new RotspawnTargetGoal<>(this, Animal.class, true, (target) -> !(target instanceof Mog)));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return createMobAttributes()
			.add(Attributes.MAX_HEALTH, 35.0D)
			.add(Attributes.ARMOR, 3.0D)
			.add(Attributes.ATTACK_DAMAGE, 5.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.23D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.ROTBELCHER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.ROTBELCHER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.ROTBELCHER_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.ROTBELCHER_STEP.get(), 0.15F, 1.0F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_CHARGING, false);
	}

	public boolean isCharging() {
		return this.entityData.get(IS_CHARGING);
	}

	public void setCharging(boolean charging) {
		this.entityData.set(IS_CHARGING, charging);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		this.level().broadcastEntityEvent(this, (byte) 4);
		return super.doHurtTarget(entity);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackAnimation.start(this.tickCount);
			this.shootAnimation.stop();
		}
		if (id == 5) {
			this.shootAnimation.start(this.tickCount);
			this.attackAnimation.stop();
		}
	}

	private static class RotbelcherShootGoal extends Goal {

		private final Rotbelcher rotbelcher;
		public int chargeTime;

		public RotbelcherShootGoal(Rotbelcher rotbelcher) {
			this.rotbelcher = rotbelcher;
		}

		@Override
		public boolean canUse() {
			return this.rotbelcher.getTarget() != null && this.rotbelcher.getTarget().distanceToSqr(this.rotbelcher) > 20.0D;
		}

		@Override
		public void start() {
			this.chargeTime = 0;
		}

		@Override
		public void stop() {
			this.rotbelcher.setCharging(false);
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			LivingEntity target = this.rotbelcher.getTarget();
			if (target != null) {
				double distanceToTarget = target.distanceToSqr(this.rotbelcher);
				if (!(distanceToTarget > 40.0D)) {
					this.rotbelcher.getNavigation().stop();
				} else {
					this.rotbelcher.getNavigation().moveTo(target, 1.2F);
				}
				if (this.rotbelcher.hasLineOfSight(target)) {
					Level level = this.rotbelcher.level();
					this.chargeTime++;
					this.rotbelcher.getLookControl().setLookAt(target, 30.0F, 30.0F);
					if (this.chargeTime == 40) {
						level.broadcastEntityEvent(this.rotbelcher, (byte) 5);
						this.rotbelcher.playSound(UGSoundEvents.ROTBELCHER_SHOOT.get(), 1.0F, this.rotbelcher.getVoicePitch());

						Vec3 vec3 = this.rotbelcher.getViewVector(1.0F);
						double x = target.getX() - (this.rotbelcher.getX() + vec3.x);
						double y = target.getY(0.5D) - (0.5D + this.rotbelcher.getY(0.5D));
						double z = target.getZ() - (this.rotbelcher.getZ() + vec3.z);
						Vec3 shootVector = new Vec3(x, y, z);

						RotbelcherProjectile projectile = new RotbelcherProjectile(level, this.rotbelcher, shootVector.normalize());
						projectile.setPos(this.rotbelcher.getX() + vec3.x, this.rotbelcher.getY(0.5D) + 0.5D, projectile.getZ() + vec3.z);
						level.addFreshEntity(projectile);
						this.chargeTime = -8;
					}
				} else if (this.chargeTime > 0) {
					this.chargeTime--;
				}
				this.rotbelcher.setCharging(this.chargeTime > 10);
			}
		}
	}
}