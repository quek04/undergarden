package quek.undergarden.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.PlayerHeartTypeEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import quek.undergarden.Undergarden;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.client.UGRenderTypes;
import quek.undergarden.event.UthericInfectionEvents;
import quek.undergarden.registry.UGAttachments;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEffects;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UGOverlayEvents {

	private static final Identifier BRITTLENESS_ARMOR_EMPTY = Undergarden.prefix("brittleness_armor/empty");
	private static final Identifier BRITTLENESS_ARMOR_HALF = Undergarden.prefix("brittleness_armor/half");
	private static final Identifier BRITTLENESS_ARMOR_FULL = Undergarden.prefix("brittleness_armor/full");

	private static final Identifier UTHERIC_INFECTION_EMPTY = Undergarden.prefix("utheric_infection/empty");
	private static final Identifier UTHERIC_INFECTION_HALF = Undergarden.prefix("utheric_infection/half");
	private static final Identifier UTHERIC_INFECTION_FULL = Undergarden.prefix("utheric_infection/full");
	private static final Identifier UTHERIC_INFECTION_FULL_LETHAL = Undergarden.prefix("utheric_infection/full_lethal");

	public static final Identifier UTHERIC_INFECTION_OVERLAY = Undergarden.prefix("textures/misc/utheric_infection_overlay.png");
	private static final DecimalFormat UTHERIC_FORMAT = Util.make(new DecimalFormat("#.##"), fmt -> fmt.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));

	static void init(IEventBus bus) {
		bus.addListener(UGOverlayEvents::registerOverlays);
		NeoForge.EVENT_BUS.addListener(UGOverlayEvents::renderVirulentHearts);
	}

	private static void renderVirulentHearts(PlayerHeartTypeEvent event) {
		if (event.getEntity().hasEffect(UGEffects.VIRULENCE)) {
			event.setType(Gui.HeartType.valueOf("UNDERGARDEN_VIRULENT"));
		}
	}

	private static void registerOverlays(RegisterGuiLayersEvent event) {
		event.registerAbove(VanillaGuiLayers.ARMOR_LEVEL, Undergarden.prefix("brittleness_armor"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.hasEffect(UGEffects.BRITTLENESS) && minecraft.gameMode.canHurtPlayer()) {
				renderBrittlenessArmor(guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics, player);
			}
		});
		event.registerAboveAll(Undergarden.prefix("undergarden_portal_overlay"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;

			if (player != null) {
				renderPortalOverlay(guiGraphics, minecraft, deltaTracker.getGameTimeDeltaPartialTick(true));
			}
		});
		event.registerAboveAll(Undergarden.prefix("utheric_infection_bar"), (gui, partialTick) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (!minecraft.options.hideGui && player != null && player.getData(UGAttachments.UNDERGARDEN_DATA).uthericInfection() > 0.0F && minecraft.gameMode.canHurtPlayer()) {
				renderUthericInfectionBar(gui.guiWidth(), gui.guiHeight(), gui, minecraft.gui, player);
			}
		});
		event.registerAbove(VanillaGuiLayers.CAMERA_OVERLAYS, Undergarden.prefix("utheric_infection_vignette"), ((guiGraphics, deltaTracker) -> {
			if (UndergardenConfig.Client.toggle_utheric_infection_overlay.get()) {
				Minecraft minecraft = Minecraft.getInstance();
				LocalPlayer player = minecraft.player;
				if (minecraft.options.getCameraType().isFirstPerson() && !minecraft.options.hideGui && player != null) {
					double vignetteBrightness = (player.getData(UGAttachments.UNDERGARDEN_DATA).uthericInfection() / UthericInfectionEvents.MAX_INFECTION) / 2;
					vignetteBrightness = Mth.clamp(vignetteBrightness, 0.0F, 1.0F);
					guiGraphics.blit(UGRenderTypes.GUI_INFECTION, UTHERIC_INFECTION_OVERLAY, 0, 0, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight(), ARGB.white((float) vignetteBrightness));
				}
			}
		}));
	}

	private static void renderBrittlenessArmor(int width, int height, GuiGraphicsExtractor graphics, Player player) {
		int x = width / 2 - 91;
		int y = height - 49;

		int level = player.getArmorValue();
		for (int i = 1; level > 0 && i < 20; i += 2) {
			if (i < level) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BRITTLENESS_ARMOR_FULL, x, y, 9, 9);
			} else if (i == level) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BRITTLENESS_ARMOR_HALF, x, y, 9, 9);
			} else {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BRITTLENESS_ARMOR_EMPTY, x, y, 9, 9);
			}
			x += 8;
		}
	}

	private static void renderPortalOverlay(GuiGraphicsExtractor graphics, Minecraft minecraft, float partialTicks) {
		float alpha = Mth.lerp(partialTicks, UndergardenPortalVisuals.getPrevPortalAnimTime(), UndergardenPortalVisuals.getPortalAnimTime());
		if (alpha > 0.0F) {
			if (alpha < 1.0F) {
				alpha *= alpha;
				alpha *= alpha;
				alpha = alpha * 0.8F + 0.2F;
			}

			int color = ARGB.white(alpha);
			TextureAtlasSprite slot = minecraft.getModelManager().getBlockStateModelSet().getParticleMaterial(UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState()).sprite();
			graphics.blitSprite(RenderPipelines.GUI_TEXTURED, slot, 0, 0, graphics.guiWidth(), graphics.guiHeight(), color);
		}
	}

	private static void renderUthericInfectionBar(int width, int height, GuiGraphicsExtractor graphics, Gui gui, Player player) {
		int left = width / 2 + 91;
		int top = height - gui.rightHeight;
		gui.rightHeight += 10;

		int infectionLevel = Mth.ceil(player.getData(UGAttachments.UNDERGARDEN_DATA).uthericInfection());
		if (UndergardenConfig.Client.toggle_utheric_infection_number_display.get()) {
			graphics.text(Minecraft.getInstance().font, UTHERIC_FORMAT.format(player.getData(UGAttachments.UNDERGARDEN_DATA).uthericInfection()), left, top, 10500660);
		}
		for (int i = 0; i < 10; i++) {
			int idx = i * 2 + 1;
			int x = left - i * 8 - 9;
			int y = top;
			if (infectionLevel >= 16) {
				y += gui.random.nextInt(2);
			}
			if (idx < infectionLevel) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, infectionLevel >= 20 ? UTHERIC_INFECTION_FULL_LETHAL : UTHERIC_INFECTION_FULL, x, y, 9, 9);
			} else if (idx == infectionLevel) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, UTHERIC_INFECTION_HALF, x, y, 9, 9);
			} else {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, UTHERIC_INFECTION_EMPTY, x, y, 9, 9);
			}
		}
	}
}
