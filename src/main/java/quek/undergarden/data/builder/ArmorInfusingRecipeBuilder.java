package quek.undergarden.data.builder;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import quek.undergarden.recipe.InfusingBookCategory;

import java.util.function.Function;

public class ArmorInfusingRecipeBuilder {
	private final Function<InfusingBookCategory, Recipe<?>> factory;

	public ArmorInfusingRecipeBuilder(Function<InfusingBookCategory, Recipe<?>> factory) {
		this.factory = factory;
	}

	public static ArmorInfusingRecipeBuilder armorInfusing(Function<InfusingBookCategory, Recipe<?>> factory) {
		return new ArmorInfusingRecipeBuilder(factory);
	}

	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		recipeOutput.accept(id, this.factory.apply(InfusingBookCategory.PURIFYING), null);
	}
}
