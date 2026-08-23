package quek.undergarden.client.event;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterCustomEnvironmentEffectRendererEvent;
import net.neoforged.neoforge.common.NeoForge;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.client.OthersideSky;
import quek.undergarden.client.UndergardenClient;

public class UGClientEvents {

	static void init(IEventBus bus) {
		NeoForge.EVENT_BUS.addListener(UGClientEvents::undergardenPortalFOV);
		NeoForge.EVENT_BUS.addListener(UGClientEvents::makeInfuserRecipesAccessible);
		bus.addListener(UGClientEvents::othersideSkybox);
	}

	private static void undergardenPortalFOV(ComputeFovModifierEvent event) {
		if (UndergardenPortalVisuals.getPortalAnimTime() > 0.0F) {
			event.setNewFovModifier(event.getFovModifier() - UndergardenPortalVisuals.getPortalAnimTime());
		}
	}

	public static void makeInfuserRecipesAccessible(RecipesReceivedEvent event) {
		UndergardenClient.RECIPE_MAP = event.getRecipeMap();
	}

	public static void othersideSkybox(RegisterCustomEnvironmentEffectRendererEvent event) {
		event.registerSkyboxRenderer(UndergardenClient.OTHERSIDE_SKYBOX, new OthersideSky());
	}
}