package quek.undergarden.entity.boss;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;

public class MasticatorEntity extends MonsterEntity {

    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(false);

    public MasticatorEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
        this.xpReward = 25;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.4D));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMobAttributes()
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
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UGSoundEvents.MASTICATOR_STEP.get(), 0.20F, 0.5F);
    }

    @Override
    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
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
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(MathHelper.lerp(0.1D, speed, d0));

            if (this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                boolean flag = false;
                AxisAlignedBB axisalignedbb = this.getBoundingBox().inflate(0.2D);

                for(BlockPos blockpos : BlockPos.betweenClosed(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    if (block instanceof LeavesBlock) {
                        flag = this.level.destroyBlock(blockpos, true, this) || flag;
                    }
                }

                if (!flag && this.onGround) {
                    this.jumpFromGround();
                }
            }

        }
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void killed(ServerWorld world, LivingEntity entityLivingIn) {
        super.killed(world, entityLivingIn);
        this.heal(this.getHealth() / 4);
        this.playSound(UGSoundEvents.MASTICATOR_EAT.get(), 1.0F, this.getVoicePitch());
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    protected void blockedByShield(LivingEntity entityIn) {
        this.launch(entityIn);
        entityIn.hurtMarked = true;
    }

    private void launch(Entity target) {
        double distanceX = target.getX() - this.getX();
        double distanceZ = target.getZ() - this.getZ();
        double d2 = Math.max(distanceX * distanceX + distanceZ * distanceZ, 0.001D);
        target.push(distanceX / d2 * 4.0D, 0.2D, distanceZ / d2 * 4.0D);
    }
}
