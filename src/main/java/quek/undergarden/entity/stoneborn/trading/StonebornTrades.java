package quek.undergarden.entity.stoneborn.trading;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class StonebornTrades {

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> stoneborn_trades = get(ImmutableMap.of(1,
            new VillagerTrades.ITrade[]{
                    new BuyForRegaliumTrade(UGItems.regalium_nugget.get(), UGItems.blisterbomb.get(), 16, 8, 24, 5),
                    new BuyForRegaliumTrade(UGItems.regalium_ingot.get(), UGItems.shard_torch.get(), 1, 4, 10, 5),
                    new BuyForRegaliumTrade(UGItems.regalium_nugget.get(), UGItems.gloomgourd_seeds.get(), 3, 6, 100, 1),
                    new BuyForRegaliumAndItemTrade(UGBlocks.regalium_block.get().asItem(), UGItems.cloggrum_axe.get(), UGItems.cloggrum_battleaxe.get(), 10, 1, 1, 1, 50),
                    new SellForRegaliumTrade(UGItems.regalium_ingot.get(), Items.DIAMOND, 4, 1, Integer.MAX_VALUE, 20),
                    new SellForRegaliumTrade(UGItems.regalium_nugget.get(), Items.IRON_INGOT, 8, 1, Integer.MAX_VALUE, 10),
                    new SellForRegaliumTrade(UGItems.regalium_nugget.get(), Items.GOLD_INGOT, 4, 1, Integer.MAX_VALUE, 5),
                    new SellForRegaliumTrade(UGBlocks.regalium_block.get().asItem(), UGBlocks.utherium_block.get().asItem(), 3, 1, Integer.MAX_VALUE, 50)
            }
    ));

    private static Int2ObjectMap<VillagerTrades.ITrade[]> get(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }
}
