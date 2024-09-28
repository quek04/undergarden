package quek.undergarden.entity.monster.rotspawn;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.entity.animal.Mog;
import quek.undergarden.entity.monster.stoneborn.Stoneborn;
import quek.undergarden.registry.UGDamageSources;

import java.util.Optional;
import java.util.function.Predicate;

public class RotspawnMonster extends Monster {

	private int fleeTime = 0;

	protected RotspawnMonster(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new RotspawnTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new RotspawnTargetGoal<>(this, Stoneborn.class, true));
		this.targetSelector.addGoal(3, new RotspawnTargetGoal<>(this, Animal.class, true, (target) -> !(target instanceof Mog)));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.fleeTime > 0) {
			this.fleeTime--;
		}

		if (this.tickCount % 40 == 0 && this.fleeTime == 0) {
			Optional<BlockPos> repelPos = BlockPos.findClosestMatch(this.blockPosition(), 16, 4, pos -> this.level().getBlockState(pos).is(BlockTags.HOGLIN_REPELLENTS));
			repelPos.ifPresent(pos -> this.fleePosition(pos.getCenter()));
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean flag = super.hurt(source, amount);
		if (source.is(UGDamageSources.SHARD_TORCH) && flag) {
			if (source.getSourcePosition() == null)
				throw new IllegalArgumentException("Please pass a Vec3 into the DamageSource when calling SHARD_TORCH, otherwise Rotspawn will not flee them.\nYou can use UGDamageSources.getShardTorchDamage to do this easily.");
			this.fleePosition(source.getSourcePosition());
		}
		return flag;
	}

	private void fleePosition(Vec3 damagePos) {
		//attempt to find a path 5 times
		for (int i = 0; i < 5; i++) {
			Vec3 fleePos = DefaultRandomPos.getPosAway(this, 16, 5, damagePos);
			if (fleePos != null) {
				this.getNavigation().stop();
				this.getNavigation().moveTo(fleePos.x(), fleePos.y(), fleePos.z(), 1.45D);
				this.setTarget(null);
				this.fleeTime = 100;
				break;
			}
		}
	}

	public static boolean canRotspawnSpawn(EntityType<? extends Monster> type, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
		return random.nextInt(10) == 0 && checkMonsterSpawnRules(type, level, reason, pos, random);
	}

	public static class RotspawnTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

		public RotspawnTargetGoal(Mob mob, Class<T> targetClass, boolean mustSee) {
			super(mob, targetClass, mustSee);
		}

		public RotspawnTargetGoal(Mob mob, Class<T> targetClass, boolean mustSee, Predicate<LivingEntity> predicate) {
			super(mob, targetClass, mustSee, predicate);
		}

		@Override
		public boolean canUse() {
			return ((RotspawnMonster) this.mob).fleeTime <= 0 && super.canUse();
		}
	}
}