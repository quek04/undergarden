package quek.undergarden.recipe;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jspecify.annotations.Nullable;

public abstract class SimpleInfusingRecipe implements InfusingRecipe {

	protected final Recipe.CommonInfo commonInfo;
	private final InfusingBookCategory category;
	private final Ingredient input;
	private final int infusingTime;
	private final float experience;

	private @Nullable PlacementInfo placementInfo;

	protected SimpleInfusingRecipe(Recipe.CommonInfo commonInfo, InfusingBookCategory category, Ingredient input, int infusingTime, float experience) {
		this.commonInfo = commonInfo;
		this.category = category;
		this.input = input;
		this.infusingTime = infusingTime;
		this.experience = experience;
	}

	@Override
	public Ingredient input() {
		return this.input;
	}

	@Override
	public int infusingTime() {
		return this.infusingTime;
	}

	@Override
	public float experience() {
		return this.experience;
	}

	@Override
	public InfusingBookCategory category() {
		return this.category;
	}

	@Override
	public abstract RecipeSerializer<? extends SimpleInfusingRecipe> getSerializer();

	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.create(this.input());
		}

		return this.placementInfo;
	}

	@Override
	public String group() {
		return "";
	}

	@Override
	public boolean showNotification() {
		return this.commonInfo.showNotification();
	}
}
