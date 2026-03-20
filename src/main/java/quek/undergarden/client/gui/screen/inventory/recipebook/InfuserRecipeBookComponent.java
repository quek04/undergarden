package quek.undergarden.client.gui.screen.inventory.recipebook;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jspecify.annotations.Nullable;
import quek.undergarden.recipe.InfusingRecipe;

import java.util.Iterator;
import java.util.List;

public class InfuserRecipeBookComponent extends RecipeBookComponent {

	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted")
	);
	private static final Component FILTER_NAME = Component.translatable("gui.undergarden.recipebook.toggleRecipes.infusable");

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(FILTER_SPRITES);
	}

	@Override
	public void slotClicked(@Nullable Slot slot) {
		super.slotClicked(slot);
		if (slot != null && slot.index < this.menu.getSize()) {
			this.ghostRecipe.clear();
		}
	}

	@Override
	public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
		ItemStack itemstack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
		this.ghostRecipe.setRecipe(recipe);
		this.ghostRecipe.addIngredient(Ingredient.of(itemstack), slots.get(3).x, slots.get(3).y);
		NonNullList<Ingredient> ingredients = recipe.value().getIngredients();
		InfusingRecipe infusingRecipe = (InfusingRecipe) recipe.value();

		Slot slot = slots.get(infusingRecipe.getRecipeSlotType().getSlotIndex());
		if (slot.getItem().isEmpty()) {
			this.ghostRecipe.addIngredient(Ingredient.of(infusingRecipe.getRecipeSlotType().getValidItems()), slot.x, slot.y);
		}

		Iterator<Ingredient> iterator = ingredients.iterator();

		for (int i = 0; i < 3; i++) {
			if (!iterator.hasNext()) {
				return;
			}

			Ingredient ingredient = iterator.next();
			if (!ingredient.isEmpty()) {
				Slot slot1 = slots.get(i);
				this.ghostRecipe.addIngredient(ingredient, slot1.x, slot1.y);
			}
		}
	}

	@Override
	protected Component getRecipeFilterName() {
		return FILTER_NAME;
	}
}