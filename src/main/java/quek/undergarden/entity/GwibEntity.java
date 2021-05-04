package quek.undergarden.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class GwibEntity extends WaterMobEntity implements IMob {

    public GwibEntity(EntityType<? extends WaterMobEntity> type, World world) {
        super(type, world);
        this.moveControl = new GwibMovementController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.5D, 120));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.5D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractFishEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return WaterMobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    public static boolean canGwibSpawn(EntityType<? extends WaterMobEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getBlockState(pos).is(Blocks.WATER) && worldIn.getBlockState(pos.above()).is(Blocks.WATER) && pos.getY() <= 32;
    }

    @Override
    protected PathNavigator createNavigation(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Override
    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.onGround = false;
            this.hasImpulse = true;
            this.playSound(SoundEvents.COD_FLOP, 1.0F, this.getVoicePitch());
        }

        super.aiStep();
    }

    @Override
    public void travel(Vector3d travelVector) {
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
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.5F;
    }

    static class GwibMovementController extends MovementController {
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

            if (this.operation == MovementController.Action.MOVE_TO && !this.gwib.getNavigation().isDone()) {
                float movementSpeed = (float)(this.speedModifier * this.gwib.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.gwib.setSpeed(MathHelper.lerp(0.125F, this.gwib.getSpeed(), movementSpeed));
                double d0 = this.wantedX - this.gwib.getX();
                double d1 = this.wantedY - this.gwib.getY();
                double d2 = this.wantedZ - this.gwib.getZ();
                if (d1 != 0.0D) {
                    double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.gwib.setDeltaMovement(this.gwib.getDeltaMovement().add(0.0D, (double)this.gwib.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
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