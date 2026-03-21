package quek.undergarden.client.gui.screen.inventory.recipebook;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import quek.undergarden.inventory.InfuserMenu;
import quek.undergarden.recipe.display.CatalystSlotDisplay;
import quek.undergarden.recipe.display.InfusingRecipeDisplay;

import java.util.List;

public class InfuserRecipeBookComponent extends RecipeBookComponent<InfuserMenu> {

	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted")
	);
	private static final Component FILTER_NAME = Component.translatable("gui.undergarden.recipebook.toggleRecipes.infusable");

	public InfuserRecipeBookComponent(InfuserMenu menu) {
		super(menu, List.of());
	}

	@Override
	protected WidgetSprites getFilterButtonTextures() {
		return FILTER_SPRITES;
	}

	@Override
	protected boolean isCraftingSlot(Slot slot) {
		return slot.index <= 3;
	}

	@Override
	protected void selectMatchingRecipes(RecipeCollection collection, StackedItemContents stackedContents) {
		collection.selectRecipes(stackedContents, display -> display instanceof InfusingRecipeDisplay);
	}

	@Override
	protected Component getRecipeFilterName() {
		return FILTER_NAME;
	}

	@Override
	protected void fillGhostRecipe(GhostSlots ghostSlots, RecipeDisplay recipe, ContextMap context) {
		ghostSlots.setResult(this.menu.getSlot(3), context, recipe.result());

		if (recipe instanceof InfusingRecipeDisplay infusingRecipe) {
			ghostSlots.setInput(this.menu.slots.getFirst(), context, infusingRecipe.input());

			if (infusingRecipe.catalyst() instanceof CatalystSlotDisplay catalyst) {
				Slot catalystSlot = this.menu.slots.get(catalyst.slotType().getSlotIndex());
				if (catalystSlot.getItem().isEmpty()) {
					ghostSlots.setInput(catalystSlot, context, catalyst);
				}
			}
		}
	}
}