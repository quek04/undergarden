package quek.undergarden.client.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import quek.undergarden.registry.UGBlocks;

import java.util.function.BooleanSupplier;

public class UndergardenReceivingLevelScreen extends ReceivingLevelScreen {

	public UndergardenReceivingLevelScreen(BooleanSupplier levelReceived, Reason reason) {
		super(levelReceived, reason);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		guiGraphics.blit(0, 0, -90, guiGraphics.guiWidth(), guiGraphics.guiHeight(), this.minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState()));
	}
}