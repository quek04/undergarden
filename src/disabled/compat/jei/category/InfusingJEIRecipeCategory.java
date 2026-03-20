package quek.undergarden.compat.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import quek.undergarden.Undergarden;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.recipe.InfuserConversionRecipe;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDataComponents;

import java.util.Arrays;

public class InfusingJEIRecipeCategory implements IRecipeCategory<InfusingRecipe> {

	private static final Identifier INFUSER_TEXTURE = Undergarden.prefix("textures/gui/container/infuser/infuser.png");
	public static final RecipeType<InfusingRecipe> RECIPE_TYPE = RecipeType.create(Undergarden.MODID, "infusing", InfuserConversionRecipe.class);

	private final IDrawable icon;
	private final Component localizedName;

	public InfusingJEIRecipeCategory(IGuiHelper guiHelper) {
		this.icon = guiHelper.createDrawableItemStack(new ItemStack(UGBlocks.INFUSER));
		this.localizedName = Component.translatable("gui.undergarden.jei.category.infuser");
	}

	@Override
	public RecipeType<InfusingRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public Component getTitle() {
		return this.localizedName;
	}

	@Override
	public int getWidth() {
		return 126;
	}

	@Override
	public int getHeight() {
		return 57;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, InfusingRecipe recipe, IFocusGroup focuses) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		if (level == null) {
			throw new NullPointerException("level must not be null.");
		}
		RegistryAccess registryAccess = level.registryAccess();
		NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();

		builder.addSlot(RecipeIngredientRole.INPUT, 55, 1).addIngredients(recipeIngredients.getFirst());

		var slot = recipe.getRecipeSlotType();
		builder.addSlot(RecipeIngredientRole.INPUT, (slot.getSlotIndex() - 1) * 108 + 1, 37).addIngredients(Ingredient.of(slot.getValidItems()));

		if (recipe.getResultItem(registryAccess).isEmpty()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 36).addItemStacks(Arrays.stream(recipeIngredients.getFirst().getItems()).map(stack -> {
				var copy = stack.copy();
				copy.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(56));
				return copy;
			}).toList());
		} else {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 36).addItemStack(recipe.getResultItem(registryAccess));
		}
	}

	@Override
	public void draw(InfusingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		guiGraphics.blit(INFUSER_TEXTURE, 0, 0, 25, 16, this.getWidth(), this.getHeight());

		this.drawExperience(recipe, guiGraphics);
		this.drawCookTime(recipe, guiGraphics);
	}

	protected void drawExperience(InfusingRecipe recipe, GuiGraphics guiGraphics) {
		float experience = recipe.experience();
		if (experience > 0) {
			Component experienceString = Component.translatable("gui.undergarden.jei.category.infusing.experience", experience);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(experienceString);
			guiGraphics.drawString(fontRenderer, experienceString, this.getWidth() - stringWidth, -1, 0xFF808080, false);
		}
	}

	protected void drawCookTime(InfusingRecipe recipe, GuiGraphics guiGraphics) {
		int infusingTime = recipe.infusingTime();
		if (infusingTime > 0) {
			int infusingTimeSeconds = infusingTime / 20;
			Component timeString = Component.translatable("gui.undergarden.jei.category.infusing.time.seconds", infusingTimeSeconds);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(timeString);
			guiGraphics.drawString(fontRenderer, timeString, (this.getWidth() - stringWidth) - 20, 45, 0xFF808080, false);
		}
	}
}
