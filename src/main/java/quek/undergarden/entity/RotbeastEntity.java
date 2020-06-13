package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSoundEvents;

import java.util.Random;

public class RotbeastEntity extends MonsterEntity {

    private int attackTimer;

    public RotbeastEntity(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override //temporary ai?
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DwellerEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return UndergardenMod.ROTSPAWN;
    }

    public static boolean canRotbeastSpawn(EntityType<? extends MonsterEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && worldIn.getBlockState(pos.down()).getBlock() == UndergardenBlocks.deepturf_block.get() || worldIn.getBlockState(pos.down()).getBlock() == UndergardenBlocks.depthrock.get() && worldIn.getLightValue(pos) <= 3;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte)4);
        float f = this.func_226511_et_();
        float f1 = f > 0.0F ? f / 2.0F + (float)this.rand.nextInt((int)f) : 0.0F;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D));
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        return flag;
    }

    private float func_226511_et_() {
        return (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
    }

    @Override
    public void onKillEntity(LivingEntity entityLivingIn) {
        super.onKillEntity(entityLivingIn);
        if ((this.world.getDifficulty() == Difficulty.NORMAL || this.world.getDifficulty() == Difficulty.HARD) && entityLivingIn instanceof DwellerEntity) {
            if (this.world.getDifficulty() != Difficulty.HARD && this.rand.nextBoolean()) {
                return;
            }

            DwellerEntity dweller = (DwellerEntity)entityLivingIn;
            RotDwellerEntity rotDweller = UndergardenEntities.ROTDWELLER.get().create(this.world);

            rotDweller.copyLocationAndAnglesFrom(dweller);
            dweller.remove();
            rotDweller.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(rotDweller)), SpawnReason.CONVERSION, new RotDwellerEntity.GroupData(false), (CompoundNBT)null);
            if (dweller.hasCustomName()) {
                rotDweller.setCustomName(dweller.getCustomName());
                rotDweller.setCustomNameVisible(dweller.isCustomNameVisible());
            }

            if (this.isNoDespawnRequired()) {
                rotDweller.enablePersistence();
            }
            rotDweller.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(rotDweller);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UndergardenSoundEvents.ROTBEAST_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.ROTBEAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.ROTBEAST_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 0.5F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }
}
