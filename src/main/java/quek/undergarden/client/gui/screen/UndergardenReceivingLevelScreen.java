package quek.undergarden.client.gui.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.multiplayer.LevelLoadTracker;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGBlocks;

public class UndergardenReceivingLevelScreen extends LevelLoadingScreen {

	private @Nullable TextureAtlasSprite cachedPortalSprite;

	public UndergardenReceivingLevelScreen(LevelLoadTracker levelReceived, Reason reason) {
		super(levelReceived, reason);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		graphics.blitSprite(RenderPipelines.GUI_OPAQUE_TEXTURED_BACKGROUND, this.getPortalSprite(), 0, 0, graphics.guiWidth(), graphics.guiHeight());
	}

	private TextureAtlasSprite getPortalSprite() {
		if (this.cachedPortalSprite != null) {
			return this.cachedPortalSprite;
		} else {
			this.cachedPortalSprite = this.minecraft
				.getModelManager()
				.getBlockStateModelSet()
				.getParticleMaterial(UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState())
				.sprite();
			return this.cachedPortalSprite;
		}
	}
}