package quek.undergarden.datagen.helpers.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.recipe.ItemInfusingRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemInfusingRecipeBuilder implements RecipeBuilder {
	private final InfusingBookCategory bookCategory;
	private final Ingredient ingredient;
	private final float experience;
	private final int infusingTime;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public ItemInfusingRecipeBuilder(InfusingBookCategory bookCategory, Ingredient ingredient, float experience, int infusingTime) {
		this.bookCategory = bookCategory;
		this.ingredient = ingredient;
		this.experience = experience;
		this.infusingTime = infusingTime;
	}

	public static ItemInfusingRecipeBuilder infusing(Ingredient ingredient, InfusingBookCategory bookCategory, float experience, int infusingTime) {
		return new ItemInfusingRecipeBuilder(bookCategory, ingredient, experience, infusingTime);
	}

	@Override
	public ItemInfusingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	@Override
	public RecipeBuilder group(@Nullable String groupName) {
		return this;
	}

	@Override
	public ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(this.ingredient.getValues().get(0).value().getDefaultInstance());
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> location) {
		Advancement.Builder builder = output.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(builder::addCriterion);
		ItemInfusingRecipe recipe = new ItemInfusingRecipe(RecipeBuilder.createCraftingCommonInfo(false), this.bookCategory, this.ingredient, this.infusingTime, this.experience);
		output.accept(location, recipe, builder.build(location.identifier().withPrefix("recipes/infusing/" + this.bookCategory.getSerializedName() + "/")));
	}
}
