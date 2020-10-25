package quek.undergarden.entity.stoneborn.trading;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import quek.undergarden.registry.UndergardenItems;

public class StonebornTrades {

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> stoneborn_trades = get(ImmutableMap.of(1,
            new VillagerTrades.ITrade[]{
                    new BuyForRegaliumTrade(UndergardenItems.regalium_nugget.get(), UndergardenItems.blisterbomb.get(), 16, 8, 24, 5),
                    new BuyForRegaliumTrade(UndergardenItems.regalium_ingot.get(), UndergardenItems.shard_torch.get(), 1, 4, 10, 5),
                    new BuyForRegaliumTrade(UndergardenItems.regalium_nugget.get(), UndergardenItems.gloomgourd_seeds.get(), 3, 6, 100, 1),
                    new SellForRegaliumTrade(UndergardenItems.regalium_ingot.get(), Items.DIAMOND, 4, 1, Integer.MAX_VALUE, 20),
                    new SellForRegaliumTrade(UndergardenItems.regalium_nugget.get(), Items.IRON_INGOT, 8, 1, Integer.MAX_VALUE, 10),
                    new SellForRegaliumTrade(UndergardenItems.regalium_nugget.get(), Items.GOLD_INGOT, 4, 1, Integer.MAX_VALUE, 5),
            }
    ));

    private static Int2ObjectMap<VillagerTrades.ITrade[]> get(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }
}
