package quek.undergarden.entity.monster.stoneborn.goals;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import quek.undergarden.entity.monster.stoneborn.AbstractStoneborn;

public class StonebornLookAtCustomerGoal extends LookAtPlayerGoal {

	private final AbstractStoneborn stoneborn;

	public StonebornLookAtCustomerGoal(AbstractStoneborn stoneborn) {
		super(stoneborn, Player.class, 8.0F);
		this.stoneborn = stoneborn;
	}

	@Override
	public boolean canUse() {
		if (this.stoneborn.isTrading()) {
			this.lookAt = this.stoneborn.getTradingPlayer();
			return true;
		} else {
			return false;
		}
	}
}