package quek.undergarden.entity.boss;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGSoundEvents;

public class ForgottenGuardianEntity extends MonsterEntity {

    private int attackTimer;

    public ForgottenGuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
        this.xpReward = 30;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSoundEvents.FORGOTTEN_GUARDIAN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.FORGOTTEN_GUARDIAN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.FORGOTTEN_GUARDIAN_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_STEP.get(), 0.5F, 1.0F);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void checkDespawn() {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.remove();
        } else {
            this.noActionTime = 0;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if(this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
            AxisAlignedBB axisalignedbb = this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D);

            for(BlockPos blockpos : BlockPos.betweenClosed(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
                BlockState blockstate = this.level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if(!block.is(BlockTags.WITHER_IMMUNE)) {
                    this.level.destroyBlock(blockpos, false, this);
                }
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            double x = entityIn.getX() - this.getX();
            double z = entityIn.getZ() - this.getZ();
            double modifier = Math.max(x * x + z * z, 0.001D);
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add((x / modifier) * 2, 0.2F, (z / modifier) * 2));
            this.doEnchantDamageEffects(this, entityIn);
        }

        this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK.get(), 1.0F, 1.0F);
        return flag;
    }

    @Override
    protected void blockedByShield(LivingEntity entityIn) {
        double x = entityIn.getX() - this.getX();
        double z = entityIn.getZ() - this.getZ();
        double modifier = Math.max(x * x + z * z, 0.001D);
        entityIn.push((x / modifier) * 2, 0.2F, (z / modifier) * 2);
        entityIn.hurtMarked = true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof ProjectileEntity) {
            this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_DEFLECT.get(), 1.0F, 1.0F);
            return false;
        }
        else return super.hurt(source, amount);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK.get(), 1.0F, 1.0F);
        }
        else {
            super.handleEntityEvent(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) { }

    @Override
    public boolean addEffect(EffectInstance effectInstanceIn) {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected PathNavigator createNavigation(World world) {
        return new ForgottenGuardianEntity.Navigator(this, world);
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
    }

    static class Navigator extends GroundPathNavigator {
        public Navigator(MobEntity entity, World world) {
            super(entity, world);
        }

        protected PathFinder createPathFinder(int range) {
            this.nodeEvaluator = new ForgottenGuardianEntity.Processor();
            return new PathFinder(this.nodeEvaluator, range);
        }
    }

    static class Processor extends WalkNodeProcessor {
        private Processor() {
        }

        protected PathNodeType evaluateBlockPathType(IBlockReader world, boolean p_215744_2_, boolean p_215744_3_, BlockPos pos, PathNodeType pathNodeType) {
            return PathNodeType.WALKABLE;
        }
    }
}