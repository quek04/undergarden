package quek.undergarden.entity.cavern;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.registry.UGSoundEvents;

import java.util.EnumSet;

public class NargoyleEntity extends AbstractCavernCreatureEntity {

    public NargoyleEntity(EntityType<? extends AbstractCavernCreatureEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.2F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 128.0D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return UGSoundEvents.NARGOYLE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UGSoundEvents.NARGOYLE_DEATH.get();
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.playSound(UGSoundEvents.NARGOYLE_ATTACK.get(), 1.0F, 1.0F);
        return super.doHurtTarget(entityIn);
    }

    public static class LeapAtTargetGoal extends Goal {
        private final Mob leaper;
        private LivingEntity leapTarget;
        private final float leapMotionY;

        public LeapAtTargetGoal(Mob leapingEntity, float leapMotionYIn) {
            this.leaper = leapingEntity;
            this.leapMotionY = leapMotionYIn;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.leaper.isVehicle()) {
                return false;
            } else {
                this.leapTarget = this.leaper.getTarget();
                if (this.leapTarget == null) {
                    return false;
                } else {
                    double d0 = this.leaper.distanceToSqr(this.leapTarget);
                    if (!(d0 < 4.0D) && !(d0 > 16.0D)) {
                        if (!this.leaper.isOnGround()) {
                            return false;
                        } else {
                            return this.leaper.getRandom().nextInt(5) == 0;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.leaper.isOnGround();
        }

        @Override
        public void start() {
            Vec3 vector3d = this.leaper.getDeltaMovement();
            Vec3 vector3d1 = new Vec3(this.leapTarget.getX() - this.leaper.getX(), 0.0D, this.leapTarget.getZ() - this.leaper.getZ());
            if (vector3d1.lengthSqr() > 1.0E-7D) {
                vector3d1 = vector3d1.normalize().scale(0.4D).add(vector3d.scale(0.2D));
            }

            this.leaper.setDeltaMovement(vector3d1.x * 2, this.leapMotionY, vector3d1.z * 2);
        }
    }
}