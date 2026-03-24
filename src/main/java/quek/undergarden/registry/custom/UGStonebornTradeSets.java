package quek.undergarden.registry.custom;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTradeSet;
import quek.undergarden.registry.UGTags;

import java.util.Optional;

public class UGStonebornTradeSets {

	public static final ResourceKey<StonebornTradeSet> VAGABOND = resourceKey("vagabond");

	public static ResourceKey<StonebornTradeSet> resourceKey(String path) {
		return ResourceKey.create(UGRegistries.Keys.STONEBORN_TRADE_SET, Undergarden.prefix(path));
	}

	public static void bootstrap(BootstrapContext<StonebornTradeSet> context) {
		var lookup = context.lookup(UGRegistries.Keys.STONEBORN_TRADE);
		context.register(VAGABOND, new StonebornTradeSet(lookup.getOrThrow(UGTags.StonebornTrades.VAGABOND_TRADES), ConstantValue.exactly(4.0F), false, Optional.empty()));
	}
}
