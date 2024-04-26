package quek.undergarden.entity.stoneborn.trading;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.Optional;

public class BuyForRegaliumAndItemTrade implements VillagerTrades.ItemListing {

	private final ItemStack buy;
	private final ItemStack sell;
	private final int regaliumCount;
	private final int buyCount;
	private final int sellCount;
	private final int maxUses;

	public BuyForRegaliumAndItemTrade(Item buy, Item item, int regaliumCount, int buyCount, int sellCount, int maxUses) {
		this(new ItemStack(buy), new ItemStack(item), regaliumCount, buyCount, sellCount, maxUses);
	}

	public BuyForRegaliumAndItemTrade(ItemStack buy, ItemStack stack, int regaliumCount, int buyCount, int sellCount, int maxUses) {
		this.buy = buy;
		this.sell = stack;
		this.regaliumCount = regaliumCount;
		this.buyCount = buyCount;
		this.sellCount = sellCount;
		this.maxUses = maxUses;
	}

	@Nullable
	@Override
	public MerchantOffer getOffer(Entity entity, RandomSource random) {
		return new MerchantOffer(new ItemCost(UGItems.REGALIUM_CRYSTAL.get(), this.regaliumCount), Optional.of(new ItemCost(this.buy.getItem(), this.buyCount)), new ItemStack(this.sell.getItem(), this.sellCount), this.maxUses, 0, 0);
	}
}