package quek.undergarden.client.event;

import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.client.UndergardenClient;

public class UGClientEvents {

	static void init() {
		NeoForge.EVENT_BUS.addListener(UGClientEvents::undergardenPortalFOV);
		NeoForge.EVENT_BUS.addListener(UGClientEvents::makeInfuserRecipesAccessible);
	}

	private static void undergardenPortalFOV(ComputeFovModifierEvent event) {
		if (UndergardenPortalVisuals.getPortalAnimTime() > 0.0F) {
			event.setNewFovModifier(event.getFovModifier() - UndergardenPortalVisuals.getPortalAnimTime());
		}
	}

	public static void makeInfuserRecipesAccessible(RecipesReceivedEvent event) {
		UndergardenClient.RECIPE_MAP = event.getRecipeMap();
	}
}