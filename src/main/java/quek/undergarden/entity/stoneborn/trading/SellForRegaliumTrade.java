package quek.undergarden.entity.stoneborn.trading;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;

public class SellForRegaliumTrade implements VillagerTrades.ItemListing {

    private final ItemStack sell;
    private final int regaliumCount;
    private final int sellCount;
    private final int maxUses;

    public SellForRegaliumTrade(Item sell, int regaliumCount, int sellCount, int maxUses) {
        this(new ItemStack(sell), regaliumCount, sellCount, maxUses);
    }

    public SellForRegaliumTrade(ItemStack sell, int regaliumCount, int sellCount, int maxUses) {
        this.sell = sell;
        this.regaliumCount = regaliumCount;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, RandomSource random) {
        return new MerchantOffer(new ItemStack(this.sell.getItem(), this.sellCount), new ItemStack(UGItems.REGALIUM_CRYSTAL.get(), this.regaliumCount), this.maxUses, 0, 0);
    }
}