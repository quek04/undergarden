package quek.undergarden.registry.custom;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class UGStonebornTrades {

	public static final ResourceKey<StonebornTrade> VEGABOND_LIMAX = resourceKey("vegabond/limax_maximus_disc");
	public static final ResourceKey<StonebornTrade> VEGABOND_ANTHEM = resourceKey("vegabond/gloomper_anthem_disc");
	public static final ResourceKey<StonebornTrade> VEGABOND_MAMMOTH = resourceKey("vegabond/mammoth_disc");
	public static final ResourceKey<StonebornTrade> VEGABOND_RELICT = resourceKey("vegabond/relict_disc");
	public static final ResourceKey<StonebornTrade> VEGABOND_BLISTERBOMB = resourceKey("vegabond/blisterbomb");
	public static final ResourceKey<StonebornTrade> VEGABOND_SHARD_TORCH = resourceKey("vegabond/shard_torch");
	public static final ResourceKey<StonebornTrade> VEGABOND_SEEDS = resourceKey("vegabond/gloomgourd_seeds");
	public static final ResourceKey<StonebornTrade> VEGABOND_DIAMOND = resourceKey("vegabond/diamond");
	public static final ResourceKey<StonebornTrade> VEGABOND_IRON = resourceKey("vegabond/iron");
	public static final ResourceKey<StonebornTrade> VEGABOND_GOLD = resourceKey("vegabond/gold");
	public static final ResourceKey<StonebornTrade> VEGABOND_UTHERIUM = resourceKey("vegabond/utherium");

	public static ResourceKey<StonebornTrade> resourceKey(String path) {
		return ResourceKey.create(UGRegistries.STONEBORN_TRADE, Undergarden.prefix(path));
	}

	public static void bootstrap(BootstrapContext<StonebornTrade> context) {
		context.register(VEGABOND_LIMAX, new StonebornTrade(new TradeCost(UGItems.REGALIUM_CRYSTAL, 32), new ItemStackTemplate(UGItems.LIMAX_MAXIMUS_DISC), 1, 0));
		context.register(VEGABOND_ANTHEM, new StonebornTrade(new TradeCost(UGItems.REGALIUM_CRYSTAL, 32), new ItemStackTemplate(UGItems.GLOOMPER_ANTHEM_DISC), 1, 0));
		context.register(VEGABOND_MAMMOTH, new StonebornTrade(new TradeCost(UGItems.MAMMOTH_DISC, 1), new ItemStackTemplate(UGItems.REGALIUM_CRYSTAL, 32), 1, 0));
		context.register(VEGABOND_RELICT, new StonebornTrade(new TradeCost(UGItems.RELICT_DISC, 1), new ItemStackTemplate(UGItems.REGALIUM_CRYSTAL, 32), 1, 0));
		context.register(VEGABOND_BLISTERBOMB, new StonebornTrade(new TradeCost(UGItems.REGALIUM_CRYSTAL, 16), new ItemStackTemplate(UGItems.BLISTERBOMB, 8), 24, 0));
		context.register(VEGABOND_SHARD_TORCH, new StonebornTrade(new TradeCost(UGItems.REGALIUM_CRYSTAL, 1), new ItemStackTemplate(UGItems.SHARD_TORCH, 4), 10, 0));
		context.register(VEGABOND_SEEDS, new StonebornTrade(new TradeCost(UGItems.REGALIUM_CRYSTAL, 1), new ItemStackTemplate(UGItems.GLOOMGOURD_SEEDS, 6), 6, 0));
		context.register(VEGABOND_DIAMOND, new StonebornTrade(new TradeCost(Items.DIAMOND, 1), new ItemStackTemplate(UGItems.REGALIUM_CRYSTAL, 12), 5, 0));
		context.register(VEGABOND_IRON, new StonebornTrade(new TradeCost(Items.IRON_INGOT, 1), new ItemStackTemplate(UGItems.REGALIUM_CRYSTAL, 4), 5, 0));
		context.register(VEGABOND_GOLD, new StonebornTrade(new TradeCost(Items.GOLD_INGOT, 1), new ItemStackTemplate(UGItems.REGALIUM_CRYSTAL, 2), 5, 0));
		context.register(VEGABOND_UTHERIUM, new StonebornTrade(new TradeCost(UGBlocks.UTHERIUM_BLOCK, 1), new ItemStackTemplate(UGItems.REGALIUM_CRYSTAL, 48), 1, 0));
	}
}
