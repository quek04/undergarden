package quek.undergarden.compat.emi.recipe;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import quek.undergarden.Undergarden;
import quek.undergarden.compat.emi.UGEmiPlugin;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGTags;

public class UGEmiInfusingRecipe extends BasicEmiRecipe {

	private static final ResourceLocation INFUSER_TEXTURE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gui/container/infuser/infuser.png");
	private static final ResourceLocation SLOT_BLOCKED = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gui/sprites/container/infuser/slot_blocked.png");

	private final InfusingRecipe recipe;

	public UGEmiInfusingRecipe(RecipeHolder<InfusingRecipe> recipe) {
		super(UGEmiPlugin.INFUSING_CATEGORY, recipe.id(), 126, 57);
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		if (level == null) {
			throw new NullPointerException("level must not be null.");
		}
		RegistryAccess registryAccess = level.registryAccess();
		NonNullList<Ingredient> recipeIngredients = recipe.value().getIngredients();
		this.recipe = recipe.value();
		this.inputs.add(EmiIngredient.of(recipeIngredients.getFirst()));
		if (this.recipe.isUtheriumFuel()) {
			this.inputs.add(EmiIngredient.of(UGTags.Items.INFUSER_UTHERIUM_FUELS));
		} else {
			this.inputs.add(EmiIngredient.of(UGTags.Items.INFUSER_ROGDORIUM_FUELS));
		}
		this.outputs.add(EmiStack.of(recipe.value().getResultItem(registryAccess)));
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		widgets.addTexture(INFUSER_TEXTURE, 0, 0, 126, 57, 25, 16);

		widgets.addSlot(inputs.getFirst(), 54, 0).drawBack(false);

		if (this.recipe.isUtheriumFuel()) {
			widgets.addSlot(EmiIngredient.of(UGTags.Items.INFUSER_UTHERIUM_FUELS), 0, 36).drawBack(false);
			widgets.addTexture(SLOT_BLOCKED, 108, 36, 16,16, 16, 16);
		} else {
			widgets.addSlot(EmiIngredient.of(UGTags.Items.INFUSER_ROGDORIUM_FUELS), 108, 36).drawBack(false);
			widgets.addTexture(SLOT_BLOCKED, 0, 36, 16,16, 16, 16);
		}

		widgets.addSlot(outputs.getFirst(), 50, 31).drawBack(false).large(true).recipeContext(this);

		drawExperience(widgets);
		drawCookTime(widgets);
	}

	protected void drawExperience(WidgetHolder widgets) {
		float experience = recipe.getExperience();
		if (experience > 0) {
			Component experienceString = Component.translatable("gui.undergarden.jei.category.infusing.experience", experience);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(experienceString);
			widgets.addText(experienceString, getDisplayWidth() - stringWidth, -1, 0xFF808080, false);
		}
	}

	protected void drawCookTime(WidgetHolder widgets) {
		int infusingTime = recipe.getInfusingTime();
		if (infusingTime > 0) {
			int infusingTimeSeconds = infusingTime / 20;
			Component timeString = Component.translatable("gui.undergarden.jei.category.infusing.time.seconds", infusingTimeSeconds);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(timeString);
			widgets.addText(timeString, (getDisplayWidth() - stringWidth) - 20, 45, 0xFF808080, false);
		}
	}
}