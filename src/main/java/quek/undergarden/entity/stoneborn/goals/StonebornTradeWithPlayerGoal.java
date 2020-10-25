package quek.undergarden.entity.stoneborn.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import quek.undergarden.entity.stoneborn.StonebornEntity;

import java.util.EnumSet;

public class StonebornTradeWithPlayerGoal extends Goal {

    private final StonebornEntity stoneborn;

    public StonebornTradeWithPlayerGoal(StonebornEntity stoneborn) {
        this.stoneborn = stoneborn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        if (!this.stoneborn.isAlive()) {
            return false;
        } else if (this.stoneborn.isInWater()) {
            return false;
        } else if (!this.stoneborn.isOnGround()) {
            return false;
        } else if (this.stoneborn.velocityChanged) {
            return false;
        } else {
            PlayerEntity playerentity = this.stoneborn.getCustomer();
            if (playerentity == null) {
                return false;
            } else if (this.stoneborn.getDistanceSq(playerentity) > 16.0D) {
                return false;
            } else {
                return playerentity.openContainer != null;
            }
        }
    }

    @Override
    public void startExecuting() {
        this.stoneborn.getNavigator().clearPath();
    }

    @Override
    public void resetTask() {
        this.stoneborn.setCustomer(null);
    }
}
