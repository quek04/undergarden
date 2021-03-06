package quek.undergarden.entity.boss;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class ForgottenGuardianEntity extends MonsterEntity {

    private int attackTimer;

    public ForgottenGuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.experienceValue = 30;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0D)
                .createMutableAttribute(Attributes.ARMOR, 20.0D)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 5.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSoundEvents.FORGOTTEN_GUARDIAN_LIVING.get();
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
    public boolean isNonBoss() {
        return false;
    }

    @Override
    public void checkDespawn() {
        if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDespawnPeaceful()) {
            this.remove();
        } else {
            this.idleTime = 0;
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if(this.collidedHorizontally && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
            AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(0.2D, 0.0D, 0.2D);

            for(BlockPos blockpos : BlockPos.getAllInBoxMutable(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
                BlockState blockstate = this.world.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if(!block.isIn(BlockTags.WITHER_IMMUNE)) {
                    this.world.destroyBlock(blockpos, false, this);
                }
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte)4);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.rand.nextInt((int)damage) : damage;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag) {
            double x = entityIn.getPosX() - this.getPosX();
            double z = entityIn.getPosZ() - this.getPosZ();
            double modifier = Math.max(x * x + z * z, 0.001D);
            entityIn.setMotion(entityIn.getMotion().add((x / modifier) * 2, 0.2F, (z / modifier) * 2));
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK.get(), 1.0F, 1.0F);
        return flag;
    }

    @Override
    protected void constructKnockBackVector(LivingEntity entityIn) {
        double x = entityIn.getPosX() - this.getPosX();
        double z = entityIn.getPosZ() - this.getPosZ();
        double modifier = Math.max(x * x + z * z, 0.001D);
        entityIn.addVelocity((x / modifier) * 2, 0.2F, (z / modifier) * 2);
        entityIn.velocityChanged = true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        Entity entity = source.getImmediateSource();
        if (entity instanceof ProjectileEntity) {
            this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_DEFLECT.get(), 1.0F, 1.0F);
            return false;
        }
        else return super.attackEntityFrom(source, amount);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK.get(), 1.0F, 1.0F);
        }
        else {
            super.handleStatusUpdate(id);
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
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) { }

    @Override
    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        return false;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        return false;
    }
}