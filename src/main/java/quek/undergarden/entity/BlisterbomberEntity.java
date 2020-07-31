package quek.undergarden.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.RottenBlisterberryEntity;

import java.util.EnumSet;
import java.util.Random;

public class BlisterbomberEntity extends FlyingEntity implements IMob {

    public BlisterbomberEntity(EntityType<? extends BlisterbomberEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new MoveHelperController(this);
        this.experienceValue = 5;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomFlyGoal(this));
        this.goalSelector.addGoal(0, new LookAtGoal(this, PlayerEntity.class, 128.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 1000, false, false, (entity) -> Math.abs(entity.getPosY() - this.getPosY()) <= 4.0D));
        this.goalSelector.addGoal(2, new ThrowBlisterberryGoal(this));
        this.goalSelector.addGoal(4, new LookAroundGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 25.0D) //hp
                .func_233815_a_(Attributes.FOLLOW_RANGE, 128.0D); //follow range
    }

    public static boolean canBlisterbomberSpawn(EntityType<? extends BlisterbomberEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && canSpawnOn(type, worldIn, reason, pos, randomIn);
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return true;
    }

    static class RandomFlyGoal extends Goal {
        private final BlisterbomberEntity parentEntity;

        public RandomFlyGoal(BlisterbomberEntity blisterbomber) {
            this.parentEntity = blisterbomber;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            MovementController movementcontroller = this.parentEntity.getMoveHelper();
            if (!movementcontroller.isUpdating()) {
                return true;
            } else {
                double d0 = movementcontroller.getX() - this.parentEntity.getPosX();
                double d1 = movementcontroller.getY() - this.parentEntity.getPosY();
                double d2 = movementcontroller.getZ() - this.parentEntity.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.getPosX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.getPosY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.getPosZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    static class LookAroundGoal extends Goal {
        private final BlisterbomberEntity parentEntity;

        public LookAroundGoal(BlisterbomberEntity blisterbomber) {
            this.parentEntity = blisterbomber;
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.parentEntity.getAttackTarget() == null) {
                Vector3d vec3d = this.parentEntity.getMotion();
                this.parentEntity.rotationYaw = -((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180F / (float)Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            } else {
                PlayerEntity playerEntity = (PlayerEntity) this.parentEntity.getAttackTarget();
                if (playerEntity.getDistanceSq(this.parentEntity) < 4096.0D) {
                    double d1 = playerEntity.getPosX() - this.parentEntity.getPosX();
                    double d2 = playerEntity.getPosZ() - this.parentEntity.getPosZ();
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }

        }
    }

    static class ThrowBlisterberryGoal extends Goal {
        private final BlisterbomberEntity parentEntity;
        public int attackTimer;

        public ThrowBlisterberryGoal(BlisterbomberEntity blisterbomber) {
            this.parentEntity = blisterbomber;
        }

        public boolean shouldExecute() {
            return this.parentEntity.getAttackTarget() != null;
        }

        public void startExecuting() {
            this.attackTimer = 0;
        }

        //public void resetTask() {
        //    this.parentEntity.setAttacking(false);
        //}

        public void tick() {
            LivingEntity livingentity = this.parentEntity.getAttackTarget();
            if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(livingentity)) {
                World world = this.parentEntity.world;
                ++this.attackTimer;

                if (this.attackTimer == 5) {
                    world.playSound(null, parentEntity.getPosX(), parentEntity.getPosY(), parentEntity.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
                    RottenBlisterberryEntity berry = new RottenBlisterberryEntity(world, parentEntity);
                    berry.func_234612_a_(parentEntity, parentEntity.rotationPitch, parentEntity.rotationYaw, 0.0F, 1.5F, 1.0F);
                    world.addEntity(berry);
                    this.attackTimer = -40;
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }

            //this.parentEntity.setAttacking(this.attackTimer > 10);
        }


    }

    static class MoveHelperController extends MovementController {
        private final BlisterbomberEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(BlisterbomberEntity blisterbomber) {
            super(blisterbomber);
            this.parentEntity = blisterbomber;
        }

        public void tick() {
            if (this.action == MovementController.Action.MOVE_TO) {
                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    Vector3d vec3d = new Vector3d(this.posX - this.parentEntity.getPosX(), this.posY - this.parentEntity.getPosY(), this.posZ - this.parentEntity.getPosZ());
                    double d0 = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.func_220673_a(vec3d, MathHelper.ceil(d0))) {
                        this.parentEntity.setMotion(this.parentEntity.getMotion().add(vec3d.scale(0.1D)));
                    } else {
                        this.action = MovementController.Action.WAIT;
                    }
                }

            }
        }

        private boolean func_220673_a(Vector3d p_220673_1_, int p_220673_2_) {
            AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

            for(int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.offset(p_220673_1_);
                if (!this.parentEntity.world.hasNoCollisions(this.parentEntity, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

}
