package quek.undergarden.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import quek.undergarden.Undergarden;
import quek.undergarden.jei.category.InfusingRecipeCategory;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGRecipeTypes;

import java.util.Objects;

@JeiPlugin
public class UGJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "jei");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new InfusingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		registration.addRecipes(InfusingRecipeCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(UGRecipeTypes.INFUSING.get()).stream().map(RecipeHolder::value).toList());
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(UGBlocks.INFUSER), InfusingRecipeCategory.RECIPE_TYPE);
	}
}
