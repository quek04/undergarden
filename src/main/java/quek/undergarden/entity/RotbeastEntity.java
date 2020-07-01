package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DwellerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 80.0D) //hp
                .func_233815_a_(Attributes.field_233826_i_, 3.0D) //armor
                .func_233815_a_(Attributes.field_233823_f_, 10.0D) //attack damage
                .func_233815_a_(Attributes.field_233821_d_, 0.22D) //speed
                .func_233815_a_(Attributes.field_233820_c_, 0.5D); //knockback resist
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return UndergardenMod.ROTSPAWN;
    }

    public static boolean canRotbeastSpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL || worldIn.getBlockState(pos).getBlock() == UndergardenBlocks.tremblecrust.get() && canSpawnOn(type, worldIn, reason, pos, randomIn) && randomIn.nextInt(20) == 0;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
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
        float f = this.getAttackDamage();
        float f1 = f > 0.0F ? f / 2.0F + (float)this.rand.nextInt((int)f) : 0.0F;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D));
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        return flag;
    }

    private float getAttackDamage() {
        return (float)this.getAttribute(Attributes.field_233823_f_).getValue();
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
            rotDweller.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(rotDweller.getPositionVec())), SpawnReason.CONVERSION, null, null);
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
