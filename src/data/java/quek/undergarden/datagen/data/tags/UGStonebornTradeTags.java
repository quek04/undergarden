package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;
import quek.undergarden.registry.UGTags;
import quek.undergarden.registry.custom.UGStonebornTrades;

import java.util.concurrent.CompletableFuture;

public class UGStonebornTradeTags extends KeyTagProvider<StonebornTrade> {

	public UGStonebornTradeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, UGRegistries.STONEBORN_TRADE, future, Undergarden.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(UGTags.StonebornTrades.VEGABOND_TRADES).add(
			UGStonebornTrades.VEGABOND_LIMAX,
			UGStonebornTrades.VEGABOND_ANTHEM,
			UGStonebornTrades.VEGABOND_MAMMOTH,
			UGStonebornTrades.VEGABOND_RELICT,
			UGStonebornTrades.VEGABOND_BLISTERBOMB,
			UGStonebornTrades.VEGABOND_SHARD_TORCH,
			UGStonebornTrades.VEGABOND_SEEDS,
			UGStonebornTrades.VEGABOND_DIAMOND,
			UGStonebornTrades.VEGABOND_IRON,
			UGStonebornTrades.VEGABOND_GOLD,
			UGStonebornTrades.VEGABOND_UTHERIUM
		);
	}
}
