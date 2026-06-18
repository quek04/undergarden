package quek.undergarden.datagen.helpers.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;
import quek.undergarden.recipe.InfuserConversionRecipe;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.recipe.InfusingRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class InfusingRecipeBuilder implements RecipeBuilder {
	private final InfusingBookCategory bookCategory;
	private final ItemStackTemplate result;
	private final Ingredient ingredient;
	private final float experience;
	private final int infusingTime;
	private final InfusingRecipe.SlotType type;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public InfusingRecipeBuilder(InfusingBookCategory bookCategory, ItemStackTemplate result, Ingredient ingredient, float experience, int infusingTime, InfusingRecipe.SlotType type) {
		this.bookCategory = bookCategory;
		this.result = result;
		this.ingredient = ingredient;
		this.experience = experience;
		this.infusingTime = infusingTime;
		this.type = type;
	}

	public static InfusingRecipeBuilder infusing(Ingredient ingredient, InfusingBookCategory bookCategory, ItemStackTemplate result, float experience, int infusingTime, InfusingRecipe.SlotType type) {
		return new InfusingRecipeBuilder(bookCategory, result, ingredient, experience, infusingTime, type);
	}

	@Override
	public InfusingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	@Override
	public InfusingRecipeBuilder group(@Nullable String groupName) {
		return this;
	}

	@Override
	public ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(this.result);
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> location) {
		this.ensureValid(location);
		Advancement.Builder builder = output.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(builder::addCriterion);
		InfuserConversionRecipe recipe = new InfuserConversionRecipe(new Recipe.CommonInfo(true), this.bookCategory, this.ingredient, this.result, this.infusingTime, this.experience, this.type);
		output.accept(location, recipe, builder.build(location.identifier().withPrefix("recipes/infusing/" + this.bookCategory.getSerializedName() + "/")));
	}

	private void ensureValid(ResourceKey<Recipe<?>> id) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}
}
