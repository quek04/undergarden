package quek.undergarden.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import quek.undergarden.Undergarden;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.client.gui.screen.inventory.InfuserScreen;
import quek.undergarden.compat.jei.category.InfusingJEIRecipeCategory;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGRecipeTypes;

@JeiPlugin
public class UGJEIPlugin implements IModPlugin {

	@Override
	public Identifier getPluginUid() {
		return Undergarden.prefix("jei");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new InfusingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(InfusingJEIRecipeCategory.RECIPE_TYPE, UndergardenClient.RECIPE_MAP.byType(UGRecipeTypes.INFUSING.get()).stream().map(RecipeHolder::value).toList());
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addCraftingStation(InfusingJEIRecipeCategory.RECIPE_TYPE, new ItemStack(UGBlocks.INFUSER));
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(InfuserScreen.class, 32, 17, 41, 30, InfusingJEIRecipeCategory.RECIPE_TYPE);
		registration.addRecipeClickArea(InfuserScreen.class, 103, 17, 41, 30, InfusingJEIRecipeCategory.RECIPE_TYPE);
	}
}
