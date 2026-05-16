package quek.undergarden.client.event;

import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.NeoForge;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.registry.*;

public class UGClientEvents {

	static void init() {
		NeoForge.EVENT_BUS.addListener(UGClientEvents::undergardenPortalFOV);
	}

	private static void undergardenPortalFOV(ComputeFovModifierEvent event) {
		if (UndergardenPortalVisuals.getPortalAnimTime() > 0.0F) {
			event.setNewFovModifier(event.getFovModifier() - UndergardenPortalVisuals.getPortalAnimTime());
		}
	}
}