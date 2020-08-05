package quek.undergarden.entity.stoneborn;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import quek.undergarden.entity.rotspawn.AbstractRotspawnEntity;
import quek.undergarden.registry.UndergardenSoundEvents;

import javax.annotation.Nullable;
import java.util.UUID;

public class StonebornEntity extends MonsterEntity implements IAngerable {

    private static final DataParameter<Boolean> isChild = EntityDataManager.createKey(StonebornEntity.class, DataSerializers.BOOLEAN);
    private static final UUID BABY_SPEED_MODIFIER_IDENTIFIER = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
    private static final AttributeModifier BABY_SPEED_MODIFIER = new AttributeModifier(BABY_SPEED_MODIFIER_IDENTIFIER, "Baby speed boost", 0.2F, AttributeModifier.Operation.MULTIPLY_BASE);

    private int timeInOverworld = 0;
    private UUID uuid;

    public StonebornEntity(EntityType<? extends StonebornEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void registerGoals() {
        if(this.getHealth() > 25.0D) {
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractRotspawnEntity.class, true));
        }
        else if(this.getHealth() < 25.0D) {
            this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, AbstractRotspawnEntity.class, 32.0F, 0.3D, 0.3D));
        }
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, LivingEntity.class, 32.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AgeableEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 50.0D)
                .func_233815_a_(Attributes.ARMOR, 10.0D)
                .func_233815_a_(Attributes.MOVEMENT_SPEED, 0.3D)
                .func_233815_a_(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
                .func_233815_a_(Attributes.ATTACK_DAMAGE, 10.0D)
                ;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    /* TODO: wait for screem to make the stoneborn sounds

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.;
    }
    */

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(UndergardenSoundEvents.STONEBORN_STEP, 0.5F, 1.0F);
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return !this.isNoDespawnRequired();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.isChild()) {
            compound.putBoolean("IsBaby", true);
        }

        compound.putInt("TimeInOverworld", this.timeInOverworld);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setChild(compound.getBoolean("IsBaby"));
        this.timeInOverworld = compound.getInt("TimeInOverworld");
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(isChild, false);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if (isChild.equals(key)) {
            this.recalculateSize();
        }
    }

    @Override
    public void setChild(boolean childStoneborn) {
        this.getDataManager().set(isChild, childStoneborn);
        if (!this.world.isRemote) {
            ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            modifiableattributeinstance.removeModifier(BABY_SPEED_MODIFIER);
            if (childStoneborn) {
                modifiableattributeinstance.func_233767_b_(BABY_SPEED_MODIFIER);
            }
        }

    }

    @Override
    public boolean isChild() {
        return this.getDataManager().get(isChild);
    }

    public boolean inOverworld() {
        return !this.world.func_230315_m_().func_241509_i_() && !this.isAIDisabled();
    }

    @Override
    protected void updateAITasks() {
        if (this.inOverworld()) {
            ++this.timeInOverworld;
            this.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 0));
        } else {
            this.timeInOverworld = 0;
        }

        if (this.timeInOverworld > 300) {
            this.remove(false);
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3, Explosion.Mode.BREAK);
        }

        if(!isAggressive()) {
            if(world.getGameTime() % 40 == 0) {
                this.heal(1);
            }
        }

    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.90F : 2.3F;
    }

    @Override
    public int func_230256_F__() {
        return 0;
    }

    @Override
    public void func_230260_a__(int i) {

    }

    @Nullable
    @Override
    public UUID func_230257_G__() {
        return this.uuid;
    }

    @Override
    public void func_230259_a_(@Nullable UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void func_230258_H__() {

    }
}
