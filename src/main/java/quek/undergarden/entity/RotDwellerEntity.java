package quek.undergarden.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenSoundEvents;


public class RotDwellerEntity extends MonsterEntity {

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

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 15.0D) //hp
                .func_233815_a_(Attributes.field_233823_f_, 3.0D) //attack damage
                .func_233815_a_(Attributes.field_233821_d_, 0.30D); //speed
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return UndergardenMod.ROTSPAWN;
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

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

}
