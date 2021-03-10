package quek.undergarden.entity.cavern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import quek.undergarden.entity.cavern.AbstractCavernCreatureEntity;
import quek.undergarden.registry.UGSoundEvents;
import quek.undergarden.registry.UGTags;

public class MuncherEntity extends AbstractCavernCreatureEntity {

    public MuncherEntity(EntityType<? extends AbstractCavernCreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AbstractCavernCreatureEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.FOLLOW_RANGE, 128.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return UGSoundEvents.MUNCHER_LIVING.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.MUNCHER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.MUNCHER_DEATH.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if(this.horizontalCollision || this.verticalCollision) {
            AxisAlignedBB axisalignedbb = this.getBoundingBox();

            for(BlockPos blockpos : BlockPos.betweenClosed(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
                BlockState blockstate = this.level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block.is(UGTags.Blocks.MUNCHER_BREAKABLES)) {
                    this.level.destroyBlock(blockpos, false, this);
                    this.heal(1.0F);
                    this.playSound(UGSoundEvents.MUNCHER_CHEW.get(), 1.0F, 1.0F);
                }
            }
        }
    }
}