package quek.undergarden.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;

public class Masticator extends Monster {

	private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);

	public Masticator(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 25;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 150.0D)
				.add(Attributes.ARMOR, 10.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 5.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.40D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 64.0D);
	}

	@Override
	public float maxUpStep() {
		return 1.0F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.MASTICATOR_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return UGSoundEvents.MASTICATOR_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.MASTICATOR_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.MASTICATOR_STEP.get(), 0.20F, 1.0F);
	}

	@Override
	public void setCustomName(@Nullable Component name) {
		super.setCustomName(name);
		this.bossEvent.setName(this.getDisplayName());
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.isAlive()) {
			double d0 = this.getTarget() != null ? 0.35D : 0.3D;
			double speed = this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(Mth.lerp(0.1D, speed, d0));

			if (this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this)) {
				boolean flag = false;
				AABB axisalignedbb = this.getBoundingBox().inflate(0.2D);

				for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(axisalignedbb.minX), Mth.floor(axisalignedbb.minY), Mth.floor(axisalignedbb.minZ), Mth.floor(axisalignedbb.maxX), Mth.floor(axisalignedbb.maxY), Mth.floor(axisalignedbb.maxZ))) {
					BlockState blockstate = this.level().getBlockState(blockpos);
					Block block = blockstate.getBlock();
					if (block instanceof LeavesBlock) {
						flag = this.level().destroyBlock(blockpos, true, this) || flag;
					}
				}

				if (!flag && this.onGround()) {
					this.jumpFromGround();
				}
			}

		}
		this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
	}

	@Override
	public boolean killedEntity(ServerLevel level, LivingEntity entity) {
		this.heal(this.getHealth() / 4);
		this.playSound(UGSoundEvents.MASTICATOR_EAT.get(), 1.0F, this.getVoicePitch());
		return super.killedEntity(level, entity);
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossEvent.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossEvent.removePlayer(player);
	}

	@Override
	protected void blockedByShield(LivingEntity entity) {
		this.launch(entity);
		entity.hurtMarked = true;
	}

	private void launch(Entity target) {
		double distanceX = target.getX() - this.getX();
		double distanceZ = target.getZ() - this.getZ();
		double d2 = Math.max(distanceX * distanceX + distanceZ * distanceZ, 0.001D);
		target.push(distanceX / d2 * 4.0D, 0.2D, distanceZ / d2 * 4.0D);
	}
}