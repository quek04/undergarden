package quek.undergarden.client.gui.screen.inventory;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import quek.undergarden.Undergarden;
import quek.undergarden.client.gui.screen.inventory.recipebook.InfuserRecipeBookComponent;
import quek.undergarden.inventory.InfuserMenu;

public class InfuserScreen extends AbstractRecipeBookScreen<InfuserMenu> {

	private static final Identifier INFUSER_TEXTURE = Undergarden.prefix("textures/gui/container/infuser/infuser.png");
	private static final Identifier SLOT_BLOCKED = Undergarden.prefix("container/infuser/slot_blocked");
	private static final Identifier PROGRESS_BAR_VERTICAL = Undergarden.prefix("container/infuser/progress_bar_vertical");
	private static final Identifier PROGRESS_BAR_RIGHT = Undergarden.prefix("container/infuser/progress_bar_right");
	private static final Identifier PROGRESS_BAR_LEFT = Undergarden.prefix("container/infuser/progress_bar_left");

	public InfuserScreen(InfuserMenu menu, Inventory playerInventory, Component title) {
		super(menu, new InfuserRecipeBookComponent(menu), playerInventory, title);
	}

	@Override
	protected void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 5, this.height / 2 - 49);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		super.extractBackground(graphics, mouseX, mouseY, a);
		int leftPos = this.leftPos;
		int topPos = this.topPos;

		graphics.blit(RenderPipelines.GUI_TEXTURED, INFUSER_TEXTURE, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		int verticalProgress = Mth.ceil(this.menu.getInfusingProgress() * 46.0F);
		int horizontalProgress = Mth.ceil((this.menu.getInfusingProgress() - 0.5F) * 38.0F) * 2;

		if (this.menu.isUtheriumFuelFull()) {
			graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_BLOCKED, 16, 16, 0, 0, leftPos + 134, topPos + 53, 16, 16);

			if (this.menu.getInfusingProgressInt() <= this.menu.getInfusingTotalTimeInt() / 2) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_BAR_VERTICAL, 3, 23, 0, 23 - verticalProgress, leftPos + 32, topPos + 23 + 23 - verticalProgress, 3, verticalProgress);
			} else {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_BAR_VERTICAL, 3, 23, 0, 0, leftPos + 32, topPos + 23, 3, 23);
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_BAR_RIGHT, 38, 15, 0,  0, leftPos + 35, topPos + 17, horizontalProgress, 15);
			}
		}
		if (this.menu.isRogdoriumFuelFull()) {
			graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_BLOCKED, 16, 16, 0, 0, leftPos + 26, topPos + 53, 16, 16);

			if (this.menu.getInfusingProgressInt() <= this.menu.getInfusingTotalTimeInt() / 2) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_BAR_VERTICAL, 3, 23, 0, 23 - verticalProgress, leftPos + 141, topPos + 23 + 23 - verticalProgress, 3, verticalProgress);
			} else {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_BAR_VERTICAL, 3, 23, 0, 0, leftPos + 141, topPos + 23, 3, 23);
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_BAR_LEFT, 38, 15, 38 - horizontalProgress,  0, leftPos + 103 + 38 - horizontalProgress, topPos + 17, horizontalProgress, 15);
			}
		}
	}
}
