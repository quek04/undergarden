package quek.undergarden.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.registry.UGItems;

public class UGItem extends Item {

	public UGItem() {
		super(new Properties()
		);
	}

	public UGItem(Rarity rarity) {
		super(new Properties()
				.rarity(rarity)
		);
	}

	public UGItem(FoodProperties food) {
		super(new Properties()
				.food(food));
	}

	@Override
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> type) {
		return stack.is(UGItems.TWISTYTWIG.get()) ? 100 : super.getBurnTime(stack, type);
	}
}