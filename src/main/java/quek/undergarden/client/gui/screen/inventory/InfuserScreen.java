package quek.undergarden.client.gui.screen.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import quek.undergarden.Undergarden;
import quek.undergarden.client.gui.screen.inventory.recipebook.InfuserRecipeBookComponent;
import quek.undergarden.inventory.InfuserMenu;

public class InfuserScreen extends AbstractContainerScreen<InfuserMenu> implements RecipeUpdateListener {

	private static final ResourceLocation INFUSER_TEXTURE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gui/container/infuser/infuser.png");
	private static final ResourceLocation SLOT_BLOCKED = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "container/infuser/slot_blocked");
	private static final ResourceLocation PROGRESS_BAR_VERTICAL = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "container/infuser/progress_bar_vertical");
	private static final ResourceLocation PROGRESS_BAR_RIGHT = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "container/infuser/progress_bar_right");
	private static final ResourceLocation PROGRESS_BAR_LEFT = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "container/infuser/progress_bar_left");

	public final InfuserRecipeBookComponent recipeBookComponent;
	private boolean widthTooNarrow;

	public InfuserScreen(InfuserMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.recipeBookComponent = new InfuserRecipeBookComponent();
	}

	@Override
	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, button -> {
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			button.setPosition(this.leftPos + 5, this.height / 2 - 49);
		}));
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
		} else {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.renderGhostRecipe(guiGraphics, this.leftPos, this.topPos, true, partialTick);
		}

		this.renderTooltip(guiGraphics, mouseX, mouseY);
		this.recipeBookComponent.renderTooltip(guiGraphics, this.leftPos, this.topPos, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int leftPos = this.leftPos;
		int topPos = this.topPos;

		guiGraphics.blit(INFUSER_TEXTURE, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

		int verticalProgress = Mth.ceil(this.menu.getInfusingProgress() * 46.0F);
		int horizontalProgress = Mth.ceil((this.menu.getInfusingProgress() - 0.5F) * 38.0F) * 2;

		if (this.menu.isUtheriumFuelFull()) {
			guiGraphics.blitSprite(SLOT_BLOCKED, 16, 16, 0, 0, leftPos + 134, topPos + 53, 16, 16);

			if (this.menu.getInfusingProgressInt() <= this.menu.getInfusingTotalTimeInt() / 2) {
				guiGraphics.blitSprite(PROGRESS_BAR_VERTICAL, 3, 23, 0, 23 - verticalProgress, leftPos + 32, topPos + 23 + 23 - verticalProgress, 3, verticalProgress);
			} else {
				guiGraphics.blitSprite(PROGRESS_BAR_VERTICAL, 3, 23, 0, 0, leftPos + 32, topPos + 23, 3, 23);
				guiGraphics.blitSprite(PROGRESS_BAR_RIGHT, 38, 15, 0,  0, leftPos + 35, topPos + 17, horizontalProgress, 15);
			}
		}
		if (this.menu.isRogdoriumFuelFull()) {
			guiGraphics.blitSprite(SLOT_BLOCKED, 16, 16, 0, 0, leftPos + 26, topPos + 53, 16, 16);

			if (this.menu.getInfusingProgressInt() <= this.menu.getInfusingTotalTimeInt() / 2) {
				guiGraphics.blitSprite(PROGRESS_BAR_VERTICAL, 3, 23, 0, 23 - verticalProgress, leftPos + 141, topPos + 23 + 23 - verticalProgress, 3, verticalProgress);
			} else {
				guiGraphics.blitSprite(PROGRESS_BAR_VERTICAL, 3, 23, 0, 0, leftPos + 141, topPos + 23, 3, 23);
				guiGraphics.blitSprite(PROGRESS_BAR_LEFT, 38, 15, 38 - horizontalProgress,  0, leftPos + 103 + 38 - horizontalProgress, topPos + 17, horizontalProgress, 15);
			}
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, button)) {
			return true;
		} else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
		super.slotClicked(slot, slotId, mouseButton, type);
		this.recipeBookComponent.slotClicked(slot);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		return this.recipeBookComponent.keyPressed(keyCode, scanCode, modifiers) || super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
		boolean flag = mouseX < (double)guiLeft
			|| mouseY < (double)guiTop
			|| mouseX >= (double)(guiLeft + this.imageWidth)
			|| mouseY >= (double)(guiTop + this.imageHeight);
		return this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, mouseButton) && flag;
	}

	@Override
	public boolean charTyped(char codePoint, int modifiers) {
		return this.recipeBookComponent.charTyped(codePoint, modifiers) || super.charTyped(codePoint, modifiers);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}
