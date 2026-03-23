package quek.undergarden;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTradeSet;

public class UGRegistries {

	public static final ResourceKey<Registry<StonebornTrade>> STONEBORN_TRADE = ResourceKey.createRegistryKey(Undergarden.prefix("stoneborn_trade"));
	public static final ResourceKey<Registry<StonebornTradeSet>> STONEBORN_TRADE_SET = ResourceKey.createRegistryKey(Undergarden.prefix("stoneborn_trade_set"));

}
