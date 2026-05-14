package quek.undergarden.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGSoundEvents;

public class Undergar extends WaterAnimal implements NeutralMob {

	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private long persistentAngerEndTime;
	private @Nullable EntityReference<LivingEntity> persistentAngerTarget;

	private boolean attacking;
	private float biteAnim;
	private float biteAnimO;

	public Undergar(EntityType<? extends WaterAnimal> type, Level level) {
		super(type, level);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
		this.goalSelector.addGoal(1, new UndergarAttackGoal(this, 1.5D, false));
		this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 120));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, false));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return WaterAnimal.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 10.0D)
			.add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	public static boolean canUndergarSpawn(EntityType<? extends WaterAnimal> type, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).is(Blocks.WATER) && level.getBlockState(pos.above()).is(Blocks.WATER);
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new WaterBoundPathNavigation(this, level);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.UNDERGAR_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.UNDERGAR_DEATH.get();
	}

	@Override
	public void aiStep() {
		if (!this.isInWater() && this.onGround() && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
			this.setOnGround(false);
			this.needsSync = true;
			this.playSound(UGSoundEvents.UNDERGAR_FLOP.get(), 1.0F, this.getVoicePitch());
		}

		if (this.level().isClientSide()) {
			this.biteAnimO = this.biteAnim;
			if (this.attacking) {
				this.biteAnim += 0.25F;
				if (this.biteAnim >= 1.0F) {
					this.attacking = false;
				}
			} else if (this.biteAnim > 0.0F) {
				this.biteAnim -= 0.1F;
			}
		}

		super.aiStep();
	}

	@Override
	protected void customServerAiStep(ServerLevel level) {
		this.updatePersistentAnger(level, true);
		super.customServerAiStep(level);
	}

	public float getBiteAnim(float partialTicks) {
		return Math.clamp(Mth.lerp(partialTicks, this.biteAnimO, this.biteAnim), 0.0F, 1.0F);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		boolean flag = super.doHurtTarget(level, target);
		if (flag) {
			this.level().broadcastEntityEvent(this, (byte) 80);
		}
		return flag;
	}

	@Override
	protected AABB getAttackBoundingBox(double horizontalExpansion) {
		return super.getAttackBoundingBox(horizontalExpansion).inflate(0.4D, 1.0D, 0.4D).expandTowards(0.0D, -0.5D, 0.0D);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 80) {
			this.attacking = true;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	public void travel(Vec3 travelVector) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.01F, travelVector);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(travelVector);
		}
	}

	@Override
	public long getPersistentAngerEndTime() {
		return this.persistentAngerEndTime;
	}

	@Override
	public void setPersistentAngerEndTime(long endTime) {
		this.persistentAngerEndTime = endTime;
	}

	@Override
	public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> persistentAngerTarget) {
		this.persistentAngerTarget = persistentAngerTarget;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setTimeToRemainAngry(PERSISTENT_ANGER_TIME.sample(this.random));
	}

	private boolean targetIsInWater(@Nullable LivingEntity target) {
		if (target != null) {
			return target.isInWater();
		} else return false;
	}

	static class UndergarAttackGoal extends MeleeAttackGoal {

		private final Undergar undergar;

		public UndergarAttackGoal(Undergar undergar, double speedModifier, boolean followingTargetEvenIfNotSeen) {
			super(undergar, speedModifier, followingTargetEvenIfNotSeen);
			this.undergar = undergar;
		}

		@Override
		public boolean canUse() {
			return super.canUse() && this.undergar.targetIsInWater(this.undergar.getTarget()) && this.undergar.isAngry();
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.undergar.targetIsInWater(this.undergar.getTarget()) && this.undergar.isAngry();
		}
	}
}
