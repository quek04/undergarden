package quek.undergarden.entity.rotspawn;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGSounds;

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
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.30D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(IS_CHILD, false);
    }

    @Override
    public boolean isChild() {
        return this.getDataManager().get(IS_CHILD);
    }

    @Override
    public void setChild(boolean child) {
        this.getDataManager().set(IS_CHILD, child);
        if (this.world != null && !this.world.isRemote) {
            ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            modifiableattributeinstance.removeModifier(BABY_SPEED_BOOST);
            if (child) {
                modifiableattributeinstance.applyNonPersistentModifier(BABY_SPEED_BOOST);
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
    public CreatureAttribute getCreatureAttribute() {
        return UGEntityTypes.ROTSPAWN;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSounds.DWELLER_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSounds.DWELLER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSounds.DWELLER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 0.5F);
    }

}
