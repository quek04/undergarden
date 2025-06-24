package quek.undergarden.data.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.recipe.InfuserConversionRecipe;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.recipe.InfusingRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class InfusingRecipeBuilder implements RecipeBuilder {
	private final InfusingBookCategory bookCategory;
	private final ItemStack result;
	private final Ingredient ingredient;
	private final float experience;
	private final int infusingTime;
	private final InfusingRecipe.SlotType type;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public InfusingRecipeBuilder(InfusingBookCategory bookCategory, ItemStack result, Ingredient ingredient, float experience, int infusingTime, InfusingRecipe.SlotType type) {
		this.bookCategory = bookCategory;
		this.result = result;
		this.ingredient = ingredient;
		this.experience = experience;
		this.infusingTime = infusingTime;
		this.type = type;
	}

	public static InfusingRecipeBuilder infusing(Ingredient ingredient, InfusingBookCategory bookCategory, ItemStack result, float experience, int infusingTime, InfusingRecipe.SlotType type) {
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
	public Item getResult() {
		return this.result.getItem();
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		this.ensureValid(id);
		Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(builder::addCriterion);
		InfuserConversionRecipe recipe = new InfuserConversionRecipe(this.bookCategory, this.ingredient, this.result, this.infusingTime, this.experience, this.type);
		recipeOutput.accept(id, recipe, builder.build(id.withPrefix("recipes/infusing/" + this.bookCategory.getSerializedName() + "/")));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}
}
