package quek.undergarden.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import quek.undergarden.registry.UGItems;

public record InfuserRecipeInput(ItemStack item, boolean isUtheriumFuel) implements RecipeInput {

	@Override
	public ItemStack getItem(int index) {
		return switch (index) {
			case 0 -> this.item;
			case 1 -> this.isUtheriumFuel ? new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()) : new ItemStack(UGItems.ROGDORIUM_CRYSTAL.get());
			default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
		};
	}

	@Override
	public int size() {
		return 2;
	}
}