package quek.undergarden.entity.stoneborn.trading;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

import javax.annotation.Nullable;
import java.util.Random;

public class BuyForRegaliumAndItemTrade implements VillagerTrades.ItemListing {

    private final ItemStack regalium;
    private final ItemStack buy;
    private final ItemStack sell;
    private final int regaliumCount;
    private final int buyCount;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public BuyForRegaliumAndItemTrade(Item regalium, Item buy, Item item, int regaliumCount, int buyCount, int sellCount, int maxUses, int experience) {
        this(new ItemStack(regalium), new ItemStack(buy), new ItemStack(item), regaliumCount, buyCount, sellCount, maxUses, experience);
    }

    public BuyForRegaliumAndItemTrade(ItemStack regalium, ItemStack buy, ItemStack stack, int regaliumCount, int buyCount, int sellCount, int maxUses, int experience) {
        this(regalium, buy, stack, regaliumCount, buyCount, sellCount, maxUses, experience, 0.05F);
    }

    public BuyForRegaliumAndItemTrade(ItemStack regalium, ItemStack buy, ItemStack stack, int regaliumCount, int buyCount, int sellCount, int maxUses, int experience, float multiplier) {
        this.regalium = regalium;
        this.buy = buy;
        this.sell = stack;
        this.regaliumCount = regaliumCount;
        this.buyCount = buyCount;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = multiplier;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, Random random) {
        return new MerchantOffer(new ItemStack(this.regalium.getItem(), this.regaliumCount), new ItemStack(this.buy.getItem(), this.buyCount), new ItemStack(this.sell.getItem(), this.sellCount), this.maxUses, this.experience, this.multiplier);
    }
}
