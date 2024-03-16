package quek.undergarden.attachment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Player;
import quek.undergarden.client.UndergardenClient;

public class UndergardenPortalAttachment {

	public boolean isInsidePortal = false;
	public int portalTimer = 0;
	public float portalAnimTime = 0;
	public float prevPortalAnimTime = 0;

	public void setInPortal(boolean inPortal) {
		this.isInsidePortal = inPortal;
	}

	public boolean isInsidePortal() {
		return this.isInsidePortal;
	}

	public void setPortalTimer(int timer) {
		this.portalTimer = timer;
	}

	public int getPortalTimer() {
		return this.portalTimer;
	}

	public float getPortalAnimTime() {
		return this.portalAnimTime;
	}

	public float getPrevPortalAnimTime() {
		return this.prevPortalAnimTime;
	}

	public void handleUndergardenPortal(Player player) {
		if (player.level().isClientSide()) {
			this.prevPortalAnimTime = this.portalAnimTime;
			Minecraft minecraft = Minecraft.getInstance();
			if (this.isInsidePortal) {
				if (minecraft.screen != null && !minecraft.screen.isPauseScreen()) {
					if (minecraft.screen instanceof AbstractContainerScreen) {
						player.closeContainer();
					}
					minecraft.setScreen(null);
				}

				if (this.getPortalAnimTime() == 0.0F) {
					UndergardenClient.playPortalSound(minecraft, player);
				}
			}
		}

		if (this.isInsidePortal()) {
			++this.portalTimer;
			if (player.level().isClientSide()) {
				this.portalAnimTime += 0.0125F;
				if (this.getPortalAnimTime() > 1.0F) {
					this.portalAnimTime = 1.0F;
				}
			}
			this.isInsidePortal = false;
		} else {
			if (player.level().isClientSide()) {
				if (this.getPortalAnimTime() > 0.0F) {
					this.portalAnimTime -= 0.05F;
				}

				if (this.getPortalAnimTime() < 0.0F) {
					this.portalAnimTime = 0.0F;
				}
			}
			if (this.getPortalTimer() > 0) {
				this.portalTimer -= 4;
			}
		}
	}
}
