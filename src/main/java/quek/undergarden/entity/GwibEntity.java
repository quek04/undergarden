package quek.undergarden.entity;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Random;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;

public class GwibEntity extends WaterAnimal implements Enemy {

    public GwibEntity(EntityType<? extends WaterAnimal> type, Level world) {
        super(type, world);
        this.moveControl = new GwibMovementController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.5D, 120));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.5D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractFish.class, true));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    public static boolean canGwibSpawn(EntityType<? extends WaterAnimal> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
        return randomIn.nextInt(10) == 0 && worldIn.getBlockState(pos).is(Blocks.WATER) && worldIn.getBlockState(pos.above()).is(Blocks.WATER) && pos.getY() <= 32;
    }

    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        return new WaterBoundPathNavigation(this, worldIn);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.GWIB_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.GWIB_DEATH.get();
    }

    @Override
    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.onGround = false;
            this.hasImpulse = true;
            this.playSound(UGSoundEvents.GWIB_FLOP.get(), 1.0F, this.getVoicePitch());
        }

        super.aiStep();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        }
        else {
            super.travel(travelVector);
        }
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.5F;
    }

    static class GwibMovementController extends MoveControl {
        private final GwibEntity gwib;

        GwibMovementController(GwibEntity gwib) {
            super(gwib);
            this.gwib = gwib;
        }

        @Override
        public void tick() {
            if (this.gwib.isEyeInFluid(FluidTags.WATER)) {
                this.gwib.setDeltaMovement(this.gwib.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.gwib.getNavigation().isDone()) {
                float movementSpeed = (float)(this.speedModifier * this.gwib.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.gwib.setSpeed(Mth.lerp(0.125F, this.gwib.getSpeed(), movementSpeed));
                double d0 = this.wantedX - this.gwib.getX();
                double d1 = this.wantedY - this.gwib.getY();
                double d2 = this.wantedZ - this.gwib.getZ();
                if (d1 != 0.0D) {
                    double d3 = Mth.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.gwib.setDeltaMovement(this.gwib.getDeltaMovement().add(0.0D, (double)this.gwib.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.gwib.yRot = this.rotlerp(this.gwib.yRot, f1, 90.0F);
                    this.gwib.yBodyRot = this.gwib.yRot;
                }

            }
            else {
                this.gwib.setSpeed(0.0F);
            }
        }
    }
}