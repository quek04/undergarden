package quek.undergarden.registry;

import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipePropertySet;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.InfusingRecipe;

import java.util.Optional;

public class UGRecipePropertySets {

	public static final ResourceKey<RecipePropertySet> INFUSING = ResourceKey.create(RecipePropertySet.TYPE_KEY, Undergarden.prefix("infusing"));

	public static void registerPropertySets() {
		ImmutableMap.Builder<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> propertySets = ImmutableMap.<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor>builder()
			.put(UGRecipePropertySets.INFUSING, (recipe) -> recipe instanceof InfusingRecipe infusingRecipe ? Optional.of(infusingRecipe.input()) : Optional.empty())
			.putAll(RecipeManager.RECIPE_PROPERTY_SETS);

		RecipeManager.RECIPE_PROPERTY_SETS = propertySets.build();
	}
}
