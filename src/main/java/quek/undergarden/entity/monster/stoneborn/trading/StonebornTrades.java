package quek.undergarden.entity.monster.stoneborn.trading;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class StonebornTrades {

	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> VAGABOND_TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1,
			new VillagerTrades.ItemListing[]{
					new BuyForRegaliumTrade(UGItems.LIMAX_MAXIMUS_DISC.get(), 32, 1, 1),
					new BuyForRegaliumTrade(UGItems.GLOOMPER_ANTHEM_DISC.get(), 32, 1, 1),
					new SellForRegaliumTrade(UGItems.MAMMOTH_DISC.get(), 32, 1, 1),
					new SellForRegaliumTrade(UGItems.RELICT_DISC.get(), 32, 1, 1),
					new BuyForRegaliumTrade(UGItems.BLISTERBOMB.get(), 16, 8, 24),
					new BuyForRegaliumTrade(UGBlocks.SHARD_TORCH.get().asItem(), 1, 4, 10),
					new BuyForRegaliumTrade(UGItems.GLOOMGOURD_SEEDS.get(), 1, 6, 6),
					//new BuyForRegaliumAndItemTrade(UGItems.CLOGGRUM_AXE.get(), UGItems.CLOGGRUM_BATTLEAXE.get(), 64, 1, 1, 1),
					new SellForRegaliumTrade(Items.DIAMOND, 12, 1, 5),
					new SellForRegaliumTrade(Items.IRON_INGOT, 4, 1, 5),
					new SellForRegaliumTrade(Items.GOLD_INGOT, 2, 1, 5),
					new SellForRegaliumTrade(UGBlocks.UTHERIUM_BLOCK.get().asItem(), 48, 1, 2)
			}
	));
}