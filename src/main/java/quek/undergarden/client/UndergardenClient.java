package quek.undergarden.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;
import quek.undergarden.Undergarden;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.capability.IUndergardenPortal;
import quek.undergarden.client.model.*;
import quek.undergarden.client.render.blockentity.DepthrockBedRender;
import quek.undergarden.client.render.blockentity.GrongletRender;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.entity.UGBoat;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.registry.*;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergardenClient {

	private static final ResourceLocation VIRULENCE_HEARTS = new ResourceLocation(Undergarden.MODID, "textures/gui/virulence_hearts.png");
	private static final ResourceLocation BRITTLENESS_ARMOR = new ResourceLocation(Undergarden.MODID, "textures/gui/brittleness_armor.png");
	private static final ResourceLocation VORTEX_LOCATION = new ResourceLocation(Undergarden.MODID, "textures/environment/otherside_vortex.png");

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(UGBlockEntities.DEPTHROCK_BED.get(), DepthrockBedRender::new);
		event.registerBlockEntityRenderer(UGBlockEntities.UNDERGARDEN_SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(UGBlockEntities.UNDERGARDEN_HANGING_SIGN.get(), HangingSignRenderer::new);
		event.registerBlockEntityRenderer(UGBlockEntities.GRONGLET.get(), GrongletRender::new);
		//
		event.registerEntityRenderer(UGEntityTypes.BOAT.get(), (context) -> new UGBoatRenderer(context, false));
		event.registerEntityRenderer(UGEntityTypes.CHEST_BOAT.get(), (context) -> new UGBoatRenderer(context, true));
		event.registerEntityRenderer(UGEntityTypes.BOOMGOURD.get(), BoomgourdRender::new);
		//
		event.registerEntityRenderer(UGEntityTypes.DEPTHROCK_PEBBLE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GOO_BALL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.BLISTERBOMB.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GRONGLET.get(), GrongletEntityRender::new);
		event.registerEntityRenderer(UGEntityTypes.MINION_PROJECTILE.get(), ThrownItemRenderer::new);
		//
		event.registerEntityRenderer(UGEntityTypes.MINION.get(), MinionRender::new);
		event.registerEntityRenderer(UGEntityTypes.ROTLING.get(), RotlingRender::new);
		event.registerEntityRenderer(UGEntityTypes.ROTWALKER.get(), RotwalkerRender::new);
		event.registerEntityRenderer(UGEntityTypes.ROTBEAST.get(), RotbeastRender::new);
		event.registerEntityRenderer(UGEntityTypes.DWELLER.get(), DwellerRender::new);
		event.registerEntityRenderer(UGEntityTypes.GWIBLING.get(), GwiblingRender::new);
		event.registerEntityRenderer(UGEntityTypes.BRUTE.get(), BruteRender::new);
		event.registerEntityRenderer(UGEntityTypes.SCINTLING.get(), ScintlingRender::new);
		event.registerEntityRenderer(UGEntityTypes.GLOOMPER.get(), GloomperRender::new);
		event.registerEntityRenderer(UGEntityTypes.STONEBORN.get(), StonebornRender::new);
		event.registerEntityRenderer(UGEntityTypes.NARGOYLE.get(), NargoyleRender::new);
		event.registerEntityRenderer(UGEntityTypes.MUNCHER.get(), MuncherRender::new);
		event.registerEntityRenderer(UGEntityTypes.SPLOOGIE.get(), SploogieRender::new);
		event.registerEntityRenderer(UGEntityTypes.GWIB.get(), GwibRender::new);
		event.registerEntityRenderer(UGEntityTypes.MOG.get(), MogRender::new);
		event.registerEntityRenderer(UGEntityTypes.SMOG_MOG.get(), SmogMogRender::new);
		event.registerEntityRenderer(UGEntityTypes.FORGOTTEN.get(), ForgottenRender::new);
		//event.registerEntityRenderer(UGEntityTypes.MASTICATOR.get(), MasticatorRender::new);
		event.registerEntityRenderer(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), ForgottenGuardianRender::new);
	}

	@SubscribeEvent
	public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_HEAD, DepthrockBedRender::createHeadLayer);
		event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_FOOT, DepthrockBedRender::createFootLayer);
		event.registerLayerDefinition(UGModelLayers.GRONGLET, GrongletRender::createBodyLayer);
		for (UGBoat.Type boatType : UGBoat.Type.values()) {
			event.registerLayerDefinition(UGBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(UGBoatRenderer.createChestBoatModelName(boatType), ChestBoatModel::createBodyModel);
		}
		event.registerLayerDefinition(UGModelLayers.MINION, MinionModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTLING, RotlingModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTWALKER, RotwalkerModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTBEAST, RotbeastModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DWELLER, () -> DwellerModel.createBodyLayer(0.0F));
		event.registerLayerDefinition(UGModelLayers.DWELLER_SADDLE, () -> DwellerModel.createBodyLayer(0.5F));
		event.registerLayerDefinition(UGModelLayers.GWIBLING, GwiblingModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.BRUTE, BruteModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.SCINTLING, ScintlingModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.GLOOMPER, GloomperModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.STONEBORN, StonebornModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.NARGOYLE, NargoyleModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.MUNCHER, MuncherModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.SPLOOGIE, SploogieModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.GWIB, GwibModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.MOG, MogModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.SMOG_MOG, SmogMogModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN, ForgottenModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_INNER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.1F), 0.0F), 64, 32));
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.2F), 0.0F), 64, 32));
		//event.registerLayerDefinition(UGModelLayers.MASTICATOR, MasticatorModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_GUARDIAN, ForgottenGuardianModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, tintGetter, pos, tint) ->
						tintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(tintGetter, pos) : FastColor.ARGB32.color(0, 91, 117, 91),
				UGBlocks.DEEPTURF_BLOCK.get(),
				UGBlocks.DEEPTURF.get(),
				UGBlocks.SHIMMERWEED.get(),
				UGBlocks.TALL_DEEPTURF.get(),
				UGBlocks.TALL_SHIMMERWEED.get(),
				UGBlocks.GLOOMGOURD_STEM.get(),
				UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(),
				UGBlocks.POTTED_SHIMMERWEED.get(),
				UGBlocks.DROOPVINE.get(),
				UGBlocks.DROOPVINE_PLANT.get()
		);

		event.register((state, world, pos, tint) -> FastColor.ARGB32.color(0, 54, 45, 66),
				UGBlocks.GLOOMGOURD_STEM.get(),
				UGBlocks.GLOOMGOURD_STEM_ATTACHED.get()
		);
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		BlockColors bColors = event.getBlockColors();

		event.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, 0),
				UGBlocks.DEEPTURF_BLOCK.get(),
				UGBlocks.DEEPTURF.get(),
				UGBlocks.SHIMMERWEED.get(),
				UGBlocks.TALL_SHIMMERWEED.get(),
				UGBlocks.TALL_DEEPTURF.get()
		);

		event.register((stack, tint) -> {
					if (tint == 0) {
						return FastColor.ARGB32.color(0, 91, 117, 91);
					}
					return -1;
				},

				UGBlocks.SHIMMERWEED.get(),
				UGBlocks.TALL_SHIMMERWEED.get()
		);
	}

	@SubscribeEvent
	public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
		event.register(UGDimensions.UNDERGARDEN_LEVEL.location(), new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NONE, false, true) {
			@Override
			public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
				return fogColor;
			}

			@Override
			public boolean isFoggyAt(int x, int y) {
				return false;
			}
		});
		event.register(UGDimensions.OTHERSIDE_LEVEL.location(), new DimensionSpecialEffects(192.0F, false, DimensionSpecialEffects.SkyType.NONE, true, false) {
			@Override
			public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
				return fogColor;
			}

			@Override
			public boolean isFoggyAt(int x, int y) {
				return false;
			}

			@Override
			public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
				LevelRenderer levelRenderer = Minecraft.getInstance().levelRenderer;
				setupFog.run();

				Vec3 vec3 = level.getSkyColor(camera.getPosition(), partialTick);
				float f = (float) vec3.x;
				float f1 = (float) vec3.y;
				float f2 = (float) vec3.z;
				FogRenderer.levelFogColor();
				BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
				RenderSystem.depthMask(false);
				RenderSystem.setShaderColor(f, f1, f2, 1.0F);
				ShaderInstance shaderinstance = RenderSystem.getShader();
				levelRenderer.skyBuffer.bind();
				levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
				VertexBuffer.unbind();
				RenderSystem.enableBlend();
				/*float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);
				if (afloat != null) {
					RenderSystem.setShader(GameRenderer::getPositionColorShader);
					RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
					poseStack.pushPose();
					poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
					float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
					poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
					poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
					float f4 = afloat[0];
					float f5 = afloat[1];
					float f6 = afloat[2];
					Matrix4f matrix4f = poseStack.last().pose();
					bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
					bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();

					for (int j = 0; j <= 16; ++j) {
						float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
						float f8 = Mth.sin(f7);
						float f9 = Mth.cos(f7);
						bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
					}

					BufferUploader.drawWithShader(bufferbuilder.end());
					poseStack.popPose();
				}*/

				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				poseStack.pushPose();
				float f11 = 1.0F - level.getRainLevel(partialTick);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
				poseStack.mulPose(Axis.XN.rotationDegrees(75.0F));
				//poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));

				Matrix4f matrix4f1 = poseStack.last().pose();
				float size = 50.0F;
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderTexture(0, VORTEX_LOCATION);
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder.vertex(matrix4f1, -size, 100.0F, -size).uv(0.0F, 0.0F).endVertex();
				bufferbuilder.vertex(matrix4f1, size, 100.0F, -size).uv(1.0F, 0.0F).endVertex();
				bufferbuilder.vertex(matrix4f1, size, 100.0F, size).uv(1.0F, 1.0F).endVertex();
				bufferbuilder.vertex(matrix4f1, -size, 100.0F, size).uv(0.0F, 1.0F).endVertex();
				BufferUploader.drawWithShader(bufferbuilder.end());
				/*size = 20.0F;
				RenderSystem.setShaderTexture(0, MOON_LOCATION);
				int k = level.getMoonPhase();
				int l = k % 4;
				int i1 = k / 4 % 2;
				float f13 = (float) (l + 0) / 4.0F;
				float f14 = (float) (i1 + 0) / 2.0F;
				float f15 = (float) (l + 1) / 4.0F;
				float f16 = (float) (i1 + 1) / 2.0F;
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder.vertex(matrix4f1, -size, -100.0F, size).uv(f15, f16).endVertex();
				bufferbuilder.vertex(matrix4f1, size, -100.0F, size).uv(f13, f16).endVertex();
				bufferbuilder.vertex(matrix4f1, size, -100.0F, -size).uv(f13, f14).endVertex();
				bufferbuilder.vertex(matrix4f1, -size, -100.0F, -size).uv(f15, f14).endVertex();
				BufferUploader.drawWithShader(bufferbuilder.end());*/

				/*float f10 = level.getStarBrightness(partialTick) * f11;
				if (f10 > 0.0F) {
					RenderSystem.setShaderColor(f10, f10, f10, f10);
					FogRenderer.setupNoFog();
					levelRenderer.starBuffer.bind();
					levelRenderer.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
					VertexBuffer.unbind();
					setupFog.run();
				}*/

				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.disableBlend();
				RenderSystem.defaultBlendFunc();
				poseStack.popPose();
				RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
				double d0 = camera.getEntity().getEyePosition(partialTick).y/* - level.getLevelData().getHorizonHeight(level)*/;
				if (d0 < 0.0D) {
					poseStack.pushPose();
					poseStack.translate(0.0F, 12.0F, 0.0F);
					levelRenderer.darkBuffer.bind();
					levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
					VertexBuffer.unbind();
					poseStack.popPose();
				}

				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.depthMask(true);
                return false;
            }
		});
	}

	@SubscribeEvent
	public static void registerOverlays(RegisterGuiOverlaysEvent event) {
		event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), "virulence_hearts", (gui, guiGraphics, partialTicks, width, height) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.hasEffect(UGEffects.VIRULENCE.get()) && gui.shouldDrawSurvivalElements()) {
				renderVirulenceHearts(width, height, guiGraphics, gui, player);
			}
		});
		event.registerAbove(VanillaGuiOverlay.ARMOR_LEVEL.id(), "brittleness_armor", (gui, guiGraphics, partialTicks, width, height) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.hasEffect(UGEffects.BRITTLENESS.get()) && gui.shouldDrawSurvivalElements()) {
				renderBrittlenessArmor(width, height, guiGraphics, gui, player);
			}
		});
		//render XP bar since we cancel the jump bar
		//vanilla hardcodes the XP bar to not render when riding a jumping vehicle sadly
		event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "dweller_xp_bar", (gui, guiGraphics, partialTicks, width, height) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.getVehicle() instanceof Dweller dweller && dweller.canJump() && minecraft.gameMode.hasExperience()) {
				gui.renderExperienceBar(guiGraphics, width / 2 - 91);
			}
		});
		event.registerAboveAll("undergarden_portal_overlay", (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
			Minecraft minecraft = Minecraft.getInstance();
			Window window = minecraft.getWindow();
			LocalPlayer player = minecraft.player;

			if (player != null) {
				player.getCapability(UndergardenCapabilities.UNDERGARDEN_PORTAL_CAPABILITY).ifPresent(consumer -> renderPortalOverlay(guiGraphics, minecraft, window, consumer, partialTick));
			}
		});
	}

	@Mod.EventBusSubscriber(modid = Undergarden.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeBusEvents {

		@SubscribeEvent
		public static void undergardenFog(ViewportEvent.RenderFog event) {
			if (UndergardenConfig.Client.toggle_undergarden_fog.get()) {
				LocalPlayer player = Minecraft.getInstance().player;
				if (player != null && player.level().dimension() == UGDimensions.UNDERGARDEN_LEVEL && event.getCamera().getFluidInCamera() == FogType.NONE && event.getType() == FogType.NONE) {
                    if (player.level().getBiome(player.getOnPos()).is(UGBiomes.DEPTHS)) {
                        event.setNearPlaneDistance(-30.0F);
                        event.setFarPlaneDistance(50.0F);
                        event.setFogShape(FogShape.SPHERE);
                        event.setCanceled(true);
                    } else {
                        event.setNearPlaneDistance(-30.0F);
                        event.setFarPlaneDistance(225.0F);
                        event.setFogShape(FogShape.SPHERE);
                        event.setCanceled(true);
                    }
				}
			}
		}

		@SubscribeEvent
		public static void dontRenderJumpBarForDweller(RenderGuiOverlayEvent.Pre event) {
			if (event.getOverlay().id() == VanillaGuiOverlay.JUMP_BAR.id()) {
				if (Minecraft.getInstance().player.getVehicle() instanceof Dweller) {
					event.setCanceled(true);
				}
			}
		}
	}

	private static void renderBrittlenessArmor(int width, int height, GuiGraphics graphics, ForgeGui gui, Player player) {
		int x = width / 2 - 91;
		int y = height - 49;

		int level = player.getArmorValue();
		for (int i = 1; level > 0 && i < 20; i += 2) {
			if (i < level) {
				graphics.blit(BRITTLENESS_ARMOR, x, y, 34, 9, 9, 9);
			} else if (i == level) {
				graphics.blit(BRITTLENESS_ARMOR, x, y, 25, 9, 9, 9);
			} else {
				graphics.blit(BRITTLENESS_ARMOR, x, y, 16, 9, 9, 9);
			}
			x += 8;
		}
	}

	private static void renderVirulenceHearts(int width, int height, GuiGraphics graphics, ForgeGui gui, Player player) {
		int health = Mth.ceil(player.getHealth());
		boolean highlight = gui.healthBlinkTime > (long) gui.getGuiTicks() && (gui.healthBlinkTime - (long) gui.getGuiTicks()) / 3L % 2L == 1L;

		if (health < gui.lastHealth && player.invulnerableTime > 0) {
			gui.lastHealthTime = Util.getMillis();
			gui.healthBlinkTime = gui.getGuiTicks() + 20;
		} else if (health > gui.lastHealth && player.invulnerableTime > 0) {
			gui.lastHealthTime = Util.getMillis();
			gui.healthBlinkTime = gui.getGuiTicks() + 10;
		}

		if (Util.getMillis() - gui.lastHealthTime > 1000L) {
			gui.lastHealth = health;
			gui.displayHealth = health;
			gui.lastHealthTime = Util.getMillis();
		}

		gui.lastHealth = health;
		int healthLast = gui.displayHealth;

		AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
		float healthMax = Math.max((float) attrMaxHealth.getValue(), Math.max(healthLast, health));
		int absorb = Mth.ceil(player.getAbsorptionAmount());

		int healthRows = Mth.ceil((healthMax + absorb) / 2.0F / 10.0F);
		int rowHeight = Math.max(10 - (healthRows - 2), 3);

		//do NOT cast this to long
		gui.random.setSeed(gui.getGuiTicks() * 312871);

		int x = width / 2 - 91;
		int y = height - 39;

		int regen = -1;
		if (player.hasEffect(MobEffects.REGENERATION)) {
			regen = gui.getGuiTicks() % Mth.ceil(healthMax + 5.0F);
		}

		renderHearts(graphics, gui, player, x, y, rowHeight, regen, healthMax, health, healthLast, absorb, highlight);
	}

	private static void renderHearts(GuiGraphics graphics, ForgeGui gui, Player player, int x, int y, int height, int regen, float healthMax, int health, int healthLast, int absorb, boolean highlight) {
		Gui.HeartType heartType = Gui.HeartType.forPlayer(player);
		int hardcoreOffset = 9 * (player.level().getLevelData().isHardcore() ? 5 : 0);
		int healthAmount = Mth.ceil((double) healthMax / 2.0D);
		int absorptionAmount = Mth.ceil((double) absorb / 2.0D);
		int l = healthAmount * 2;

		for (int i1 = healthAmount + absorptionAmount - 1; i1 >= 0; --i1) {
			int j1 = i1 / 10;
			int k1 = i1 % 10;
			int newX = x + k1 * 8;
			int newY = y - j1 * height;
			if (health + absorb <= 4) {
				newY += gui.random.nextInt(2);
			}

			if (i1 < healthAmount && i1 == regen) {
				newY -= 2;
			}

			renderHeart(graphics, Gui.HeartType.CONTAINER, newX, newY, hardcoreOffset, highlight, false);
			int j2 = i1 * 2;
			boolean flag = i1 >= healthAmount;
			if (flag) {
				int k2 = j2 - l;
				if (k2 < absorb) {
					boolean flag1 = k2 + 1 == absorb;
					renderHeart(graphics, heartType == Gui.HeartType.WITHERED ? heartType : Gui.HeartType.ABSORBING, newX, newY, hardcoreOffset, false, flag1);
				}
			}

			if (highlight && j2 < healthLast) {
				boolean flag2 = j2 + 1 == healthLast;
				renderHeart(graphics, heartType, newX, newY, hardcoreOffset, true, flag2);
			}

			if (j2 < health) {
				boolean flag3 = j2 + 1 == health;
				renderHeart(graphics, heartType, newX, newY, hardcoreOffset, false, flag3);
			}
		}
	}

	private static void renderHeart(GuiGraphics graphics, Gui.HeartType type, int x, int y, int offset, boolean blinking, boolean halfHeart) {
		graphics.blit(VIRULENCE_HEARTS, x, y, type.getX(halfHeart, blinking), offset, 9, 9);
	}

	private static void renderPortalOverlay(GuiGraphics guiGraphics, Minecraft minecraft, Window window, IUndergardenPortal portal, float partialTicks) {
		PoseStack poseStack = guiGraphics.pose();
		float alpha = portal.getPrevPortalAnimTime() + (portal.getPortalAnimTime() - portal.getPrevPortalAnimTime()) * partialTicks;
		if (alpha > 0.0F) {
			if (alpha < 1.0F) {
				alpha = alpha * alpha;
				alpha = alpha * alpha;
				alpha = alpha * 0.8F + 0.2F;
			}

			poseStack.pushPose();
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			guiGraphics.setColor(1.0F, 1.0F, 1.0F, alpha);
			TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState());
			guiGraphics.blit(0, 0, -90, window.getGuiScaledWidth(), window.getGuiScaledHeight(), textureatlassprite);
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();
		}
	}

	public static void playPortalSound(Minecraft minecraft, Player player) {
		minecraft.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL.get(), player.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
	}
}