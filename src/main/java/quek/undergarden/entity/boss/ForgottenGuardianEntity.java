package quek.undergarden.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGSoundEvents;

public class ForgottenGuardianEntity extends Monster {

    private int attackTimer;

    public ForgottenGuardianEntity(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
        this.xpReward = 30;
    }

    @Override
    protected void registerGoals() {
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
            this.discard();
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
            AABB axisalignedbb = this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D);

            for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(axisalignedbb.minX), Mth.floor(axisalignedbb.minY), Mth.floor(axisalignedbb.minZ), Mth.floor(axisalignedbb.maxX), Mth.floor(axisalignedbb.maxY), Mth.floor(axisalignedbb.maxZ))) {
                BlockState blockstate = this.level.getBlockState(blockpos);
                if(!blockstate.is(BlockTags.WITHER_IMMUNE)) {
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
        if (entity instanceof Projectile) {
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
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) { }

    @Override
    public boolean canBeAffected(MobEffectInstance pPotioneffect) {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new ForgottenGuardianEntity.Navigator(this, world);
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    static class Navigator extends GroundPathNavigation {
        public Navigator(Mob entity, Level world) {
            super(entity, world);
        }

        protected PathFinder createPathFinder(int range) {
            this.nodeEvaluator = new ForgottenGuardianEntity.Processor();
            return new PathFinder(this.nodeEvaluator, range);
        }
    }

    static class Processor extends WalkNodeEvaluator {
        private Processor() {
        }

        protected BlockPathTypes evaluateBlockPathType(BlockGetter world, boolean p_215744_2_, boolean p_215744_3_, BlockPos pos, BlockPathTypes pathNodeType) {
            return BlockPathTypes.WALKABLE;
        }
    }
}