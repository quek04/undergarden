package quek.undergarden.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record InfuserInput(ItemStack item, InfusingRecipe.SlotType type) implements RecipeInput {
	@Override
	public ItemStack getItem(int index) {
		if (index != 0) {
			throw new IllegalArgumentException("No item for index " + index);
		} else {
			return this.item;
		}
	}

	@Override
	public int size() {
		return 1;
	}
}
