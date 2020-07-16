package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.RotDwellerModel;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSoundEvents;

import java.util.UUID;

public class RotDwellerEntity extends MonsterEntity {

    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(RotDwellerEntity.class, DataSerializers.BOOLEAN);
    private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier BABY_SPEED_BOOST = new AttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);

    public RotDwellerEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return UndergardenEntities.ROTSPAWN;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(IS_CHILD, false);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.getBoolean("IsBaby")) {
            this.setChild(true);
        }
    }

    public boolean isChild() {
        return this.getDataManager().get(IS_CHILD);
    }

    public void setChild(boolean childZombie) {
        this.getDataManager().set(IS_CHILD, childZombie);
        if (this.world != null && !this.world.isRemote) {
            IAttributeInstance iattributeinstance = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            iattributeinstance.removeModifier(BABY_SPEED_BOOST);
            if (childZombie) {
                iattributeinstance.applyModifier(BABY_SPEED_BOOST);
            }
        }

    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (IS_CHILD.equals(key)) {
            this.recalculateSize();
        }

        super.notifyDataManagerChange(key);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UndergardenSoundEvents.DWELLER_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UndergardenSoundEvents.DWELLER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UndergardenSoundEvents.DWELLER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 0.5F);
    }

    public static class GroupData implements ILivingEntityData {
        public final boolean isChild;

        public GroupData(boolean isChildIn) {
            this.isChild = isChildIn;
        }
    }
}
