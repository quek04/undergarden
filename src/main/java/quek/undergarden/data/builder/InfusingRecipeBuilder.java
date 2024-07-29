package quek.undergarden.data.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.recipe.InfusingRecipe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class InfusingRecipeBuilder implements RecipeBuilder {
	private final RecipeCategory category;
	private final InfusingBookCategory bookCategory;
	private final ItemStack result;
	private final Ingredient ingredient;
	private final float experience;
	private final int infusingTime;
	private final boolean utheriumFuel;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	@Nullable
	private String group;

	public InfusingRecipeBuilder(RecipeCategory category, InfusingBookCategory bookCategory, ItemStack result, Ingredient ingredient, float experience, int infusingTime, boolean utheriumFuel) {
		this.category = category;
		this.bookCategory = bookCategory;
		this.result = result;
		this.ingredient = ingredient;
		this.experience = experience;
		this.infusingTime = infusingTime;
		this.utheriumFuel = utheriumFuel;
	}

	public static InfusingRecipeBuilder infusing(Ingredient ingredient, RecipeCategory category, InfusingBookCategory bookCategory, ItemStack result, float experience, int infusingTime, boolean utheriumFuel) {
		return new InfusingRecipeBuilder(category, bookCategory, result, ingredient, experience, infusingTime, utheriumFuel);
	}

	@Override
	public InfusingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	@Override
	public InfusingRecipeBuilder group(@Nullable String groupName) {
		this.group = groupName;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result.getItem();
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		this.ensureValid(id);
		Advancement.Builder builder= recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(builder::addCriterion);
		InfusingRecipe recipe = new InfusingRecipe(Objects.requireNonNullElse(this.group, ""), this.bookCategory, this.ingredient, this.result, this.experience, this.infusingTime, this.utheriumFuel);
		recipeOutput.accept(id, recipe, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}
}
