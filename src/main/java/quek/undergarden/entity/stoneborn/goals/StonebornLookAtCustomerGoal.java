package quek.undergarden.entity.stoneborn.goals;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornLookAtCustomerGoal extends LookAtGoal {

    private final StonebornEntity stoneborn;

    public StonebornLookAtCustomerGoal(StonebornEntity stoneborn) {
        super(stoneborn, PlayerEntity.class, 8.0F);
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
