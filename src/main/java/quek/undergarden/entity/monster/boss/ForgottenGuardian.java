package quek.undergarden.entity.monster.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidType;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Set;

public class ForgottenGuardian extends Monster {

	private static final ProjectileDeflection PROJECTILE_DEFLECTION = (projectile, entity, random) -> {
		entity.level().playSound(null, entity, UGSoundEvents.FORGOTTEN_GUARDIAN_DEFLECT.get(), entity.getSoundSource(), 1.0F, 1.0F);
		ProjectileDeflection.REVERSE.deflect(projectile, entity, random);
	};
	private int attackTimer;

	public ForgottenGuardian(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 30;
		this.lookControl = new GuardianLookControl(this);
	}

	@Override
	protected void registerGoals() {
		//this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 10.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 5.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	@Override
	public void playAmbientSound() {
		if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
			super.playAmbientSound();
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.FORGOTTEN_GUARDIAN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.FORGOTTEN_GUARDIAN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.FORGOTTEN_GUARDIAN_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_STEP.get(), 0.5F, 1.0F);
	}

	@Override
	protected float getWaterSlowDown() {
		return 1.0F;
	}

	@Override
	public boolean canUsePortal(boolean ignorePassenger) {
		return false;
	}

	@Override
	public void checkDespawn() {
		if (EventHooks.checkMobDespawn(this)) return;
		this.noActionTime = 0;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@Override
	protected void customServerAiStep(ServerLevel level) {
		super.customServerAiStep(level);
		if (this.isAggressive() && level.getDifficulty() != Difficulty.PEACEFUL) {
			if (this.horizontalCollision && EventHooks.canEntityGrief(level, this)) {
				AABB axisalignedbb = this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D);

				for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(axisalignedbb.minX), Mth.floor(axisalignedbb.minY), Mth.floor(axisalignedbb.minZ), Mth.floor(axisalignedbb.maxX), Mth.floor(axisalignedbb.maxY), Mth.floor(axisalignedbb.maxZ))) {
					BlockState blockstate = this.level().getBlockState(blockpos);
					if (!blockstate.is(BlockTags.WITHER_IMMUNE)) {
						this.level().destroyBlock(blockpos, false, this);
					}
				}
			}
		}
	}

	@Override
	protected boolean isImmobile() {
		return this.level().getDifficulty() != Difficulty.PEACEFUL && super.isImmobile();
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		if (level.getDifficulty() == Difficulty.PEACEFUL && !source.isCreativePlayer() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return false;
		return super.hurtServer(level, source, damage);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity entity) {
		this.attackTimer = 10;
		this.level().broadcastEntityEvent(this, (byte) 4);
		this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK.get(), 1.0F, 1.0F);
		return super.doHurtTarget(level, entity);
	}

	@Override
	protected void blockedByItem(LivingEntity defender) {
		double x = defender.getX() - this.getX();
		double z = defender.getZ() - this.getZ();
		double modifier = Math.max(x * x + z * z, 0.001D);
		defender.push((x / modifier) * 2, 0.2F, (z / modifier) * 2);
		defender.hurtMarked = true;
	}

	@Override
	public ProjectileDeflection deflection(Projectile projectile) {
		return this.is(EntityTypeTags.DEFLECTS_PROJECTILES) ? PROJECTILE_DEFLECTION : ProjectileDeflection.NONE;
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleEntityEvent(id);
		}
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance effectInstance) {
		return false;
	}

	@Override
	public boolean isPushedByFluid(FluidType type) {
		return false;
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new ForgottenGuardian.Navigator(this, level);
	}

	@Override
	public boolean canBeLeashed() {
		return false;
	}

	static class Navigator extends GroundPathNavigation {
		public Navigator(Mob entity, Level level) {
			super(entity, level);
		}

		@Override
		protected PathFinder createPathFinder(int range) {
			this.nodeEvaluator = new ForgottenGuardian.Evaluator();
			return new PathFinder(this.nodeEvaluator, range);
		}
	}

	static class Evaluator extends WalkNodeEvaluator {
		private Evaluator() {
		}

		@Override
		public Set<PathType> getPathTypeWithinMobBB(PathfindingContext context, int width, int height, int depth) {
			return Set.of(PathType.WALKABLE);
		}
	}

	static class GuardianLookControl extends LookControl {

		public GuardianLookControl(Mob mob) {
			super(mob);
		}

		@Override
		public void setLookAt(double x, double y, double z, float yMaxRotSpeed, float xMaxRotAngle) {
			if (this.mob.level().getDifficulty() != Difficulty.PEACEFUL) {
				super.setLookAt(x, y, z, yMaxRotSpeed, xMaxRotAngle);
			}
		}
	}
}