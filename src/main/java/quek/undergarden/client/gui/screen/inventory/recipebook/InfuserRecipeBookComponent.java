package quek.undergarden.client.gui.screen.inventory.recipebook;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import quek.undergarden.Undergarden;
import quek.undergarden.inventory.InfuserMenu;
import quek.undergarden.recipe.display.CatalystSlotDisplay;
import quek.undergarden.recipe.display.InfusingRecipeDisplay;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGRecipeBookCategories;

import java.util.List;

public class InfuserRecipeBookComponent extends RecipeBookComponent<InfuserMenu> {

	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
		Undergarden.prefix("recipe_book/infuser_filter_enabled"),
		Undergarden.prefix("recipe_book/infuser_filter_disabled"),
		Undergarden.prefix("recipe_book/infuser_filter_enabled_highlighted"),
		Undergarden.prefix("recipe_book/infuser_filter_disabled_highlighted")
	);
	private static final Component FILTER_NAME = Component.translatable("gui.undergarden.recipebook.toggleRecipes.infusable");
	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
		new RecipeBookComponent.TabInfo(Items.COMPASS, UGRecipeBookCategories.INFUSER_SEARCH.get()),
		new RecipeBookComponent.TabInfo(UGItems.UTHERIUM_CRYSTAL.get(), UGRecipeBookCategories.INFUSER_CORRUPTING.get()),
		new RecipeBookComponent.TabInfo(UGItems.ROGDORIUM.get(), UGRecipeBookCategories.INFUSER_PURIFYING.get()),
		new RecipeBookComponent.TabInfo(UGBlocks.GRONGLET.asItem(), UGRecipeBookCategories.INFUSER_MISC.get())
	);

	public InfuserRecipeBookComponent(InfuserMenu menu) {
		super(menu, TABS);
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