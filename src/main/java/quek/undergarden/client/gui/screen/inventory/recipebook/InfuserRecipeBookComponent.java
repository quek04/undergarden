package quek.undergarden.client.gui.screen.inventory.recipebook;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class InfuserRecipeBookComponent extends RecipeBookComponent {

	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted")
	);
	private static final Component FILTER_NAME = Component.translatable("gui.undergarden.recipebook.toggleRecipes.infusable");
	@Nullable
	private Ingredient utheriumFuels;
	@Nullable
	private Ingredient rogdoriumFuels;

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
		this.ghostRecipe.addIngredient(Ingredient.of(itemstack), slots.get(2).x, slots.get(2).y);
		NonNullList<Ingredient> nonnulllist = recipe.value().getIngredients();

		Slot utheriumSlot = slots.get(1);
		if (utheriumSlot.getItem().isEmpty()) {
			if (this.utheriumFuels == null) {
				this.utheriumFuels = Ingredient.of(new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()));
			}

			this.ghostRecipe.addIngredient(this.utheriumFuels, utheriumSlot.x, utheriumSlot.y);
		}

		Slot rogdoriumSlot = slots.get(2);
		if (rogdoriumSlot.getItem().isEmpty()) {
			if (this.rogdoriumFuels == null) {
				this.rogdoriumFuels = Ingredient.of(new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()));
			}

			this.ghostRecipe.addIngredient(this.rogdoriumFuels, rogdoriumSlot.x, rogdoriumSlot.y);
		}

		Iterator<Ingredient> iterator = nonnulllist.iterator();

		for (int i = 0; i < 2; i++) {
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
