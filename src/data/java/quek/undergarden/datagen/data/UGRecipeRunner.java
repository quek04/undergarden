package quek.undergarden.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class UGRecipeRunner extends RecipeProvider.Runner {

	public UGRecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
		return new UGRecipes(output, provider);
	}

	@Override
	public String getName() {
		return "Undergarden Recipes";
	}
}
