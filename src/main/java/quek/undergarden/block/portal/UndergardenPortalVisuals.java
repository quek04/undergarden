package quek.undergarden.block.portal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Portal;
import quek.undergarden.registry.UGBlocks;

public class UndergardenPortalVisuals {

	private static float portalAnimTime = 0;
	private static float prevPortalAnimTime = 0;

	public static float getPortalAnimTime() {
		return portalAnimTime;
	}

	public static float getPrevPortalAnimTime() {
		return prevPortalAnimTime;
	}

	public static void handlePortalVisuals(Player player) {
		if (player.level().isClientSide()) {
			float f = 0.0F;
			prevPortalAnimTime = portalAnimTime;
			Minecraft minecraft = Minecraft.getInstance();

			if (player.portalProcess != null && player.portalProcess.isSamePortal((Portal) UGBlocks.UNDERGARDEN_PORTAL.get()) && player.portalProcess.isInsidePortalThisTick()) {
				if (minecraft.screen != null && !minecraft.screen.isPauseScreen()) {
					if (minecraft.screen instanceof AbstractContainerScreen) {
						player.closeContainer();
					}
					minecraft.setScreen(null);
				}
				f = 0.0125F;
				player.portalProcess.setAsInsidePortalThisTick(false);
			} else if (portalAnimTime > 0.0F) {
				f = -0.05F;
			}
			portalAnimTime = Mth.clamp(portalAnimTime + f, 0.0F, 1.0F);
		}
	}
}
