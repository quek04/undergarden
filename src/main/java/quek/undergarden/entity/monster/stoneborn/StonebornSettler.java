package quek.undergarden.entity.monster.stoneborn;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;

public class StonebornSettler extends AbstractStoneborn {

	public StonebornSettler(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void rewardTradeXp(MerchantOffer offer) {

	}

	@Override
	protected void updateTrades() {

	}
}