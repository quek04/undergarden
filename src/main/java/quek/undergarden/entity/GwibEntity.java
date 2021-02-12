package quek.undergarden.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class GwibEntity extends WaterMobEntity {

    public GwibEntity(EntityType<? extends WaterMobEntity> type, World world) {
        super(type, world);
        this.moveController = new GwibMovementController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.5D, 120));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.5D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractFishEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return WaterMobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.ARMOR, 5.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D);
    }

    public static boolean canGwibSpawn(EntityType<? extends WaterMobEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getBlockState(pos).isIn(Blocks.WATER) && worldIn.getBlockState(pos.up()).isIn(Blocks.WATER) && pos.getY() <= 32;
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    @Override
    public void livingTick() {
        if (!this.isInWater() && this.onGround && this.collidedVertically) {
            this.setMotion(this.getMotion().add((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.onGround = false;
            this.isAirBorne = true;
            this.playSound(SoundEvents.ENTITY_COD_FLOP, 1.0F, this.getSoundPitch());
        }

        super.livingTick();
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.01F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        }
        else {
            super.travel(travelVector);
        }
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
            if (this.gwib.areEyesInFluid(FluidTags.WATER)) {
                this.gwib.setMotion(this.gwib.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.gwib.getNavigator().noPath()) {
                float movementSpeed = (float)(this.speed * this.gwib.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.gwib.setAIMoveSpeed(MathHelper.lerp(0.125F, this.gwib.getAIMoveSpeed(), movementSpeed));
                double d0 = this.posX - this.gwib.getPosX();
                double d1 = this.posY - this.gwib.getPosY();
                double d2 = this.posZ - this.gwib.getPosZ();
                if (d1 != 0.0D) {
                    double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.gwib.setMotion(this.gwib.getMotion().add(0.0D, (double)this.gwib.getAIMoveSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.gwib.rotationYaw = this.limitAngle(this.gwib.rotationYaw, f1, 90.0F);
                    this.gwib.renderYawOffset = this.gwib.rotationYaw;
                }

            }
            else {
                this.gwib.setAIMoveSpeed(0.0F);
            }
        }
    }
}