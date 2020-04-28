package quek.undergarden.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.List;

public class SkizSwarmerEntity extends FlyingEntity implements IMob {

    public SkizSwarmerEntity(EntityType<? extends FlyingEntity> type, World worldIn) {
        super(type, worldIn);
        this.experienceValue = 1;
        this.moveController = new FlyingMovementController(this, 20, true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new SkizSwarmerEntity.AttackPlayerGoal());
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return true;
    }



    class AttackPlayerGoal extends Goal {
        private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
        private int tickDelay = 20;

        private AttackPlayerGoal() {
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (this.tickDelay > 0) {
                --this.tickDelay;
                return false;
            } else {
                this.tickDelay = 60;
                List<PlayerEntity> list = SkizSwarmerEntity.this.world.getTargettablePlayersWithinAABB(this.field_220842_b, SkizSwarmerEntity.this, SkizSwarmerEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
                if (!list.isEmpty()) {
                    list.sort((p_203140_0_, p_203140_1_) -> {
                        return p_203140_0_.getPosY() > p_203140_1_.getPosY() ? -1 : 1;
                    });

                    for(PlayerEntity playerentity : list) {
                        if (SkizSwarmerEntity.this.canAttack(playerentity, EntityPredicate.DEFAULT)) {
                            SkizSwarmerEntity.this.setAttackTarget(playerentity);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = SkizSwarmerEntity.this.getAttackTarget();
            return livingentity != null ? SkizSwarmerEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
        }
    }
}
