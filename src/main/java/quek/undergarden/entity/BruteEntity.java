package quek.undergarden.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public class BruteEntity extends MonsterEntity {

    private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = (new AttributeModifier(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.10D, AttributeModifier.Operation.ADDITION)).setSaved(false);
    private int angerLevel;
    private int randomSoundDelay;
    private UUID angerTargetUUID;

    public BruteEntity(EntityType<? extends BruteEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        super.setRevengeTarget(livingBase);
        if (livingBase != null) {
            this.angerTargetUUID = livingBase.getUniqueID();
        }

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0F, false));
        this.targetSelector.addGoal(1, new BruteEntity.HurtByAggressorGoal(this));
        this.targetSelector.addGoal(2, new BruteEntity.TargetAggressorGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, BruteEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    public static boolean canBruteSpawn(EntityType<? extends MonsterEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).getBlock() == UndergardenBlocks.deepturf_block.get();
    }

    @Override
    protected void updateAITasks() {
        IAttributeInstance iattributeinstance = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        LivingEntity livingentity = this.getRevengeTarget();
        if(world.getDifficulty() != Difficulty.PEACEFUL) {
            if (this.isAngry()) {
                if (!this.isChild() && !iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
                    iattributeinstance.applyModifier(ATTACK_SPEED_BOOST_MODIFIER);
                }

                --this.angerLevel;
                LivingEntity livingentity1 = livingentity != null ? livingentity : this.getAttackTarget();
                if (!this.isAngry() && livingentity1 != null) {
                    if (!this.canEntityBeSeen(livingentity1)) {
                        this.setRevengeTarget(null);
                        this.setAttackTarget(null);
                    } else {
                        this.angerLevel = this.func_223336_ef();
                    }
                }
            } else if (iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
                iattributeinstance.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
            }

            if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
                this.playSound(UndergardenSoundEvents.BRUTE_ANGRY, this.getSoundVolume() * 2.0F, 1.0F);
            }

            if (this.isAngry() && this.angerTargetUUID != null && livingentity == null) {
                PlayerEntity playerentity = this.world.getPlayerByUuid(this.angerTargetUUID);
                this.setRevengeTarget(playerentity);
                this.attackingPlayer = playerentity;
                this.recentlyHit = this.getRevengeTimer();
            }
            super.updateAITasks();
        }
        super.updateAITasks();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putShort("Anger", (short)this.angerLevel);
        if (this.angerTargetUUID != null) {
            compound.putString("HurtBy", this.angerTargetUUID.toString());
        } else {
            compound.putString("HurtBy", "");
        }

    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.angerLevel = compound.getShort("Anger");
        String s = compound.getString("HurtBy");
        if (!s.isEmpty()) {
            this.angerTargetUUID = UUID.fromString(s);
            PlayerEntity playerentity = this.world.getPlayerByUuid(this.angerTargetUUID);
            this.setRevengeTarget(playerentity);
            if (playerentity != null) {
                this.attackingPlayer = playerentity;
                this.recentlyHit = this.getRevengeTimer();
            }
        }

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();
            if (entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canEntityBeSeen(entity) && world.getDifficulty() != Difficulty.PEACEFUL) {
                this.func_226547_i_((LivingEntity) entity);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    private boolean func_226547_i_(LivingEntity p_226547_1_) {
        this.angerLevel = this.func_223336_ef();
        this.randomSoundDelay = this.rand.nextInt(40);
        this.setRevengeTarget(p_226547_1_);
        return true;
    }

    private int func_223336_ef() {
        return 400 + this.rand.nextInt(400);
    }

    private boolean isAngry() {
        return this.angerLevel > 0;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UndergardenSoundEvents.BRUTE_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return UndergardenSoundEvents.BRUTE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.BRUTE_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    static class HurtByAggressorGoal extends HurtByTargetGoal {
        public HurtByAggressorGoal(BruteEntity brute) {
            super(brute);
            this.setCallsForHelp(new Class[]{BruteEntity.class});
        }

        protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
            if(targetIn.world.getDifficulty() != Difficulty.PEACEFUL) {
                if (mobIn instanceof BruteEntity && this.goalOwner.canEntityBeSeen(targetIn) && ((BruteEntity) mobIn).func_226547_i_(targetIn)) {
                    mobIn.setAttackTarget(targetIn);
                }
            }
        }
    }

    static class TargetAggressorGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        public TargetAggressorGoal(BruteEntity brute) {
            super(brute, PlayerEntity.class, true);
        }

        public boolean shouldExecute() {
            return ((BruteEntity)this.goalOwner).isAngry() && super.shouldExecute();
        }
    }
}


