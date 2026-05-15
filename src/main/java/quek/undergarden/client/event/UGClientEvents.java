package quek.undergarden.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.material.FogType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.NeoForge;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.registry.*;

public class UGClientEvents {

	static void init() {
		//NeoForge.EVENT_BUS.addListener(UGClientEvents::undergardenFog);
		NeoForge.EVENT_BUS.addListener(UGClientEvents::undergardenPortalFOV);
	}

	private static void undergardenFog(ViewportEvent.RenderFog event) {
		if (UndergardenConfig.Client.toggle_undergarden_fog.get()) {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player != null && player.level().dimension() == UGDimensions.UNDERGARDEN_LEVEL && event.getCamera().getFluidInCamera() == FogType.NONE && event.getType() == FogType.NONE && !player.isEyeInFluid(UGTags.Fluids.VIRULENT)) {
				if (player.level().getBiome(player.getOnPos()).is(UGTags.Biomes.IS_DEPTHS_BIOME)) {
					event.setNearPlaneDistance(-30.0F);
					event.setFarPlaneDistance(100.0F);
				} else {
					event.setNearPlaneDistance(-30.0F);
					event.setFarPlaneDistance(225.0F);
				}
			}
		}
	}

	private static void undergardenPortalFOV(ComputeFovModifierEvent event) {
		if (UndergardenPortalVisuals.getPortalAnimTime() > 0.0F) {
			event.setNewFovModifier(event.getFovModifier() - UndergardenPortalVisuals.getPortalAnimTime());
		}
	}
}