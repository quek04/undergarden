package quek.undergarden.entity.stoneborn.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import quek.undergarden.entity.stoneborn.StonebornEntity;

import java.util.EnumSet;

public class StonebornTradeWithPlayerGoal extends Goal {

    private final StonebornEntity stoneborn;

    public StonebornTradeWithPlayerGoal(StonebornEntity stoneborn) {
        this.stoneborn = stoneborn;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.stoneborn.isAlive()) {
            return false;
        } else if (this.stoneborn.isInWater()) {
            return false;
        } else if (!this.stoneborn.isOnGround()) {
            return false;
        } else if (this.stoneborn.hurtMarked) {
            return false;
        } else {
            PlayerEntity playerentity = this.stoneborn.getTradingPlayer();
            if (playerentity == null) {
                return false;
            } else if (this.stoneborn.distanceToSqr(playerentity) > 16.0D) {
                return false;
            } else {
                return playerentity.containerMenu != null;
            }
        }
    }

    @Override
    public void start() {
        this.stoneborn.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.stoneborn.setTradingPlayer(null);
    }
}
