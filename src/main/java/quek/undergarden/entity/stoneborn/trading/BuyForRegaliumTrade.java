package quek.undergarden.entity.stoneborn.trading;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;

import javax.annotation.Nullable;
import java.util.Random;

public class BuyForRegaliumTrade implements VillagerTrades.ITrade {

    private final ItemStack regalium;
    private final ItemStack sell;
    private final int regaliumCount;
    private final int count;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public BuyForRegaliumTrade(Item regalium, Item item, int regaliumCount, int count, int maxUses, int experience) {
        this(new ItemStack(regalium), new ItemStack(item), regaliumCount, count, maxUses, experience);
    }

    public BuyForRegaliumTrade(ItemStack regalium, ItemStack stack, int regaliumCount, int count, int maxUses, int experience) {
        this(regalium, stack, regaliumCount, count, maxUses, experience, 0.05F);
    }

    public BuyForRegaliumTrade(ItemStack regalium, ItemStack stack, int regaliumCount, int count, int maxUses, int experience, float multiplier) {
        this.regalium = regalium;
        this.sell = stack;
        this.regaliumCount = regaliumCount;
        this.count = count;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = multiplier;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, Random random) {
        return new MerchantOffer(new ItemStack(this.regalium.getItem(), this.regaliumCount), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
    }
}
