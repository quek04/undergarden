package quek.undergarden.entity.stoneborn.goals;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornLookAtCustomerGoal extends LookAtPlayerGoal {

    private final StonebornEntity stoneborn;

    public StonebornLookAtCustomerGoal(StonebornEntity stoneborn) {
        super(stoneborn, Player.class, 8.0F);
        this.stoneborn = stoneborn;
    }

    @Override
    public boolean canUse() {
        if (this.stoneborn.hasCustomer()) {
            this.lookAt = this.stoneborn.getTradingPlayer();
            return true;
        } else {
            return false;
        }
    }
}