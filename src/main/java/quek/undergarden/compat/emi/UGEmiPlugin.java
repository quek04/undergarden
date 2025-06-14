package quek.undergarden.compat.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import quek.undergarden.Undergarden;
import quek.undergarden.compat.emi.recipe.UGEmiInfusingRecipe;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGRecipeTypes;

@EmiEntrypoint
public class UGEmiPlugin implements EmiPlugin {

	public static final EmiRecipeCategory INFUSING_CATEGORY = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "infusing"), EmiStack.of(UGBlocks.INFUSER));

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(INFUSING_CATEGORY);

		registry.addWorkstation(INFUSING_CATEGORY, EmiStack.of(UGBlocks.INFUSER));

		RecipeManager manager = registry.getRecipeManager();

		// Use vanilla's concept of your recipes and pass them to your EmiRecipe representation
		for (RecipeHolder<InfusingRecipe> recipe : manager.getAllRecipesFor(UGRecipeTypes.INFUSING.get())) {
			registry.addRecipe(new UGEmiInfusingRecipe(recipe));
		}
	}
}
