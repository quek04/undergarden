package quek.undergarden.entity.stoneborn.trading;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class StonebornTrades {

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> STONEBORN_TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1,
            new VillagerTrades.ItemListing[] {
                    new BuyForRegaliumTrade(UGItems.REGALIUM_CRYSTAL.get(), UGItems.LIMAX_MAXIMUS_DISC.get(), 32, 1, 1, 25),
                    new BuyForRegaliumTrade(UGItems.REGALIUM_CRYSTAL.get(), UGItems.GLOOMPER_ANTHEM_DISC.get(), 32, 1, 1, 25),
                    new SellForRegaliumTrade(UGItems.REGALIUM_CRYSTAL.get(), UGItems.MAMMOTH_DISC.get(), 32, 1, 1, 25),
                    new SellForRegaliumTrade(UGItems.REGALIUM_CRYSTAL.get(), UGItems.RELICT_DISC.get(), 32, 1, 1, 25),
                    new BuyForRegaliumTrade(UGItems.REGALIUM_NUGGET.get(), UGItems.BLISTERBOMB.get(), 16, 8, 24, 5),
                    new BuyForRegaliumTrade(UGItems.REGALIUM_CRYSTAL.get(), UGBlocks.SHARD_TORCH.get().asItem(), 1, 4, 10, 5),
                    new BuyForRegaliumTrade(UGItems.REGALIUM_NUGGET.get(), UGItems.GLOOMGOURD_SEEDS.get(), 3, 6, 100, 1),
                    new BuyForRegaliumAndItemTrade(UGBlocks.REGALIUM_BLOCK.get().asItem(), UGItems.CLOGGRUM_AXE.get(), UGItems.CLOGGRUM_BATTLEAXE.get(), 10, 1, 1, 1, 50),
                    new SellForRegaliumTrade(UGItems.REGALIUM_CRYSTAL.get(), Items.DIAMOND, 4, 1, Integer.MAX_VALUE, 20),
                    new SellForRegaliumTrade(UGItems.REGALIUM_NUGGET.get(), Items.IRON_INGOT, 8, 1, Integer.MAX_VALUE, 10),
                    new SellForRegaliumTrade(UGItems.REGALIUM_NUGGET.get(), Items.GOLD_INGOT, 4, 1, Integer.MAX_VALUE, 5),
                    new SellForRegaliumTrade(UGBlocks.REGALIUM_BLOCK.get().asItem(), UGBlocks.UTHERIUM_BLOCK.get().asItem(), 3, 1, Integer.MAX_VALUE, 50)
            }
    ));
}