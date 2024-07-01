package quek.undergarden.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import quek.undergarden.registry.UGAttachments;

public class UndergardenClient {

	public static void doClientPortalStuff(Player player) {
		Minecraft minecraft = Minecraft.getInstance();
		if (player.getData(UGAttachments.UNDERGARDEN_PORTAL.get()).isInsidePortal) {
			if (minecraft.screen != null && !minecraft.screen.isPauseScreen()) {
				if (minecraft.screen instanceof AbstractContainerScreen) {
					player.closeContainer();
				}
				minecraft.setScreen(null);
			}

			/*if (player.getData(UGAttachments.UNDERGARDEN_PORTAL.get()).getPortalAnimTime() == 0.0F) {
				minecraft.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL.get(), player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
			}*/
		}
	}

	public static RegistryAccess registryAccess() {
		return Minecraft.getInstance().level.registryAccess();
	}
}
