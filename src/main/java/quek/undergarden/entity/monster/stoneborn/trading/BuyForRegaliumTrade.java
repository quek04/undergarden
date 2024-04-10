package quek.undergarden.entity.monster.stoneborn.trading;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;

public class BuyForRegaliumTrade implements VillagerTrades.ItemListing {

	private final ItemStack sell;
	private final int regaliumCount;
	private final int count;
	private final int maxUses;

	public BuyForRegaliumTrade(Item item, int regaliumCount, int count, int maxUses) {
		this(new ItemStack(item), regaliumCount, count, maxUses);
	}

	public BuyForRegaliumTrade(ItemStack stack, int regaliumCount, int count, int maxUses) {
		this.sell = stack;
		this.regaliumCount = regaliumCount;
		this.count = count;
		this.maxUses = maxUses;
	}

	@Nullable
	@Override
	public MerchantOffer getOffer(Entity entity, RandomSource random) {
		return new MerchantOffer(new ItemStack(UGItems.REGALIUM_CRYSTAL.get(), this.regaliumCount), new ItemStack(this.sell.getItem(), this.count), this.maxUses, 0, 0);
	}
}