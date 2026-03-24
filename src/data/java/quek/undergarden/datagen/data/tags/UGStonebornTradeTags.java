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
		super(output, UGRegistries.Keys.STONEBORN_TRADE, future, Undergarden.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(UGTags.StonebornTrades.VAGABOND_TRADES).add(
			UGStonebornTrades.VAGABOND_LIMAX,
			UGStonebornTrades.VAGABOND_ANTHEM,
			UGStonebornTrades.VAGABOND_MAMMOTH,
			UGStonebornTrades.VAGABOND_RELICT,
			UGStonebornTrades.VAGABOND_BLISTERBOMB,
			UGStonebornTrades.VAGABOND_SHARD_TORCH,
			UGStonebornTrades.VAGABOND_SEEDS,
			UGStonebornTrades.VAGABOND_DIAMOND,
			UGStonebornTrades.VAGABOND_IRON,
			UGStonebornTrades.VAGABOND_GOLD,
			UGStonebornTrades.VAGABOND_UTHERIUM
		);
	}
}
