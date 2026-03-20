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
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import quek.undergarden.Undergarden;
import quek.undergarden.compat.emi.UGEmiPlugin;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGDataComponents;

import java.util.Arrays;

public class UGEmiInfusingRecipe extends BasicEmiRecipe {

	private static final Identifier INFUSER_TEXTURE = Undergarden.prefix("textures/gui/container/infuser/infuser.png");

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
		this.inputs.add(EmiIngredient.of(Arrays.stream(recipeIngredients.getFirst().getItems()).map(EmiStack::of).toList()));
		this.inputs.add(EmiIngredient.of(this.recipe.getRecipeSlotType().getValidItems()));
		this.outputs.add(EmiStack.of(recipe.value().getResultItem(registryAccess)));
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		widgets.addTexture(INFUSER_TEXTURE, 0, 0, 126, 57, 25, 16);

		widgets.addSlot(this.inputs.getFirst(), 54, 0).drawBack(false);

		var slot = this.recipe.getRecipeSlotType();
		widgets.addSlot(EmiIngredient.of(slot.getValidItems()), (slot.getSlotIndex() - 1) * 108, 36).drawBack(false);

		if (this.outputs.getFirst().isEmpty()) {
			widgets.addSlot(EmiIngredient.of(this.inputs.getFirst().getEmiStacks().stream().map(stack -> {
				var copy = stack.getItemStack().copy();
				copy.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(56));
				return EmiStack.of(copy);
			}).toList()), 50, 31).drawBack(false).large(true).recipeContext(this);
		} else {
			widgets.addSlot(this.outputs.getFirst(), 50, 31).drawBack(false).large(true).recipeContext(this);
		}
		this.drawExperience(widgets);
		this.drawCookTime(widgets);
	}

	protected void drawExperience(WidgetHolder widgets) {
		float experience = this.recipe.experience();
		if (experience > 0) {
			Component experienceString = Component.translatable("gui.undergarden.jei.category.infusing.experience", experience);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(experienceString);
			widgets.addText(experienceString, getDisplayWidth() - stringWidth, -1, 0xFF808080, false);
		}
	}

	protected void drawCookTime(WidgetHolder widgets) {
		int infusingTime = this.recipe.infusingTime();
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