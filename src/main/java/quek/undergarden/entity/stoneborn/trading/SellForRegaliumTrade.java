package quek.undergarden.entity.stoneborn.trading;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

import javax.annotation.Nullable;
import java.util.Random;

public class SellForRegaliumTrade implements VillagerTrades.ItemListing {

    private final ItemStack sell;
    private final ItemStack regalium;
    private final int regaliumCount;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;


    public SellForRegaliumTrade(Item regalium, Item sell, int regaliumCount, int sellCount, int maxUses, int experience) {
        this(new ItemStack(regalium), new ItemStack(sell), regaliumCount, sellCount, maxUses, experience);
    }

    public SellForRegaliumTrade(ItemStack regalium, ItemStack sell, int regaliumCount, int sellCount, int maxUses, int experience) {
        this(regalium, sell, regaliumCount, sellCount, maxUses, experience, 0.05F);
    }

    public SellForRegaliumTrade(ItemStack regalium, ItemStack sell, int regaliumCount, int sellCount, int maxUses, int experience, float multiplier) {
        this.regalium = regalium;
        this.sell = sell;
        this.regaliumCount = regaliumCount;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = multiplier;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, Random random) {
        return new MerchantOffer(new ItemStack(this.sell.getItem(), this.sellCount), new ItemStack(this.regalium.getItem(), this.regaliumCount), this.maxUses, this.experience, this.multiplier);
    }
}
