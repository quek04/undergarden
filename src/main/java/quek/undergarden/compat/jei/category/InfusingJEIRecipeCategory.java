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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class InfusingJEIRecipeCategory implements IRecipeCategory<InfusingRecipe> {

	private static final ResourceLocation INFUSER_TEXTURE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gui/container/infuser/infuser.png");
	private static final ResourceLocation SLOT_BLOCKED = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gui/container/infuser/slot_blocked.png");
	public static final RecipeType<InfusingRecipe> RECIPE_TYPE = RecipeType.create(Undergarden.MODID, "infusing", InfusingRecipe.class);

	private final IDrawable background;
	private final IDrawable icon;
	private final Component localizedName;

	public InfusingJEIRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(INFUSER_TEXTURE, 25, 16, 126, 57);
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
	public IDrawable getBackground() {
		return this.background;
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

		if (recipe.isUtheriumFuel()) {
			builder.addSlot(RecipeIngredientRole.INPUT, 1, 37).addItemStack(new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()));
		} else {
			builder.addSlot(RecipeIngredientRole.INPUT, 109, 37).addItemStack(new ItemStack(UGItems.ROGDORIUM_CRYSTAL.get()));
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 36).addItemStack(recipe.getResultItem(registryAccess));
	}

	@Override
	public void draw(InfusingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		if (recipe.isUtheriumFuel()) {
			guiGraphics.blitSprite(SLOT_BLOCKED, 16, 16, 0, 0, 109, 37, 16, 16);
		}
		if (!recipe.isUtheriumFuel()) {
			guiGraphics.blitSprite(SLOT_BLOCKED, 16, 16, 0, 0, 1, 37, 16, 16);
		}

		drawExperience(recipe, guiGraphics);
		drawCookTime(recipe, guiGraphics);
	}

	protected void drawExperience(InfusingRecipe recipe, GuiGraphics guiGraphics) {
		float experience = recipe.getExperience();
		if (experience > 0) {
			Component experienceString = Component.translatable("gui.undergarden.jei.category.infusing.experience", experience);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(experienceString);
			guiGraphics.drawString(fontRenderer, experienceString, getWidth() - stringWidth, -1, 0xFF808080, false);
		}
	}

	protected void drawCookTime(InfusingRecipe recipe, GuiGraphics guiGraphics) {
		int infusingTime = recipe.getInfusingTime();
		if (infusingTime > 0) {
			int infusingTimeSeconds = infusingTime / 20;
			Component timeString = Component.translatable("gui.undergarden.jei.category.infusing.time.seconds", infusingTimeSeconds);
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int stringWidth = fontRenderer.width(timeString);
			guiGraphics.drawString(fontRenderer, timeString, (getWidth() - stringWidth) - 20, 45, 0xFF808080, false);
		}
	}
}
