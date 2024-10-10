package quek.undergarden.event;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import quek.undergarden.Undergarden;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.block.portal.UndergardenPortalVisuals;
import quek.undergarden.client.gui.screen.UndergardenReceivingLevelScreen;
import quek.undergarden.client.gui.screen.inventory.InfuserScreen;
import quek.undergarden.client.model.*;
import quek.undergarden.client.particle.*;
import quek.undergarden.client.render.blockentity.DepthrockBedRender;
import quek.undergarden.client.render.blockentity.GrongletRender;
import quek.undergarden.client.render.blockentity.UndergardenBEWLR;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.client.render.layer.DenizenMaskLayer;
import quek.undergarden.client.render.layer.UthericInfectionLayer;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.recipe.InfusingBookCategory;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.*;

import java.util.List;
import java.util.Objects;

public class UndergardenClientEvents {

	private static final ResourceLocation BRITTLENESS_ARMOR_EMPTY = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "brittleness_armor/empty");
	private static final ResourceLocation BRITTLENESS_ARMOR_HALF = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "brittleness_armor/half");
	private static final ResourceLocation BRITTLENESS_ARMOR_FULL = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "brittleness_armor/full");

	private static final ResourceLocation UTHERIC_INFECTION_EMPTY = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "utheric_infection/empty");
	private static final ResourceLocation UTHERIC_INFECTION_HALF = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "utheric_infection/half");
	private static final ResourceLocation UTHERIC_INFECTION_FULL = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "utheric_infection/full");
	private static final ResourceLocation UTHERIC_INFECTION_FULL_LETHAL = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "utheric_infection/full_lethal");

	public static void initClientEvents(IEventBus bus) {
		bus.addListener(UndergardenClientEvents::clientSetup);
		bus.addListener(UndergardenClientEvents::registerEntityRenderers);
		bus.addListener(UndergardenClientEvents::registerEntityLayerDefinitions);
		bus.addListener(UndergardenClientEvents::addEntityLayers);
		bus.addListener(UndergardenClientEvents::registerParticleFactories);
		bus.addListener(UndergardenClientEvents::registerMenuScreens);
		bus.addListener(UndergardenClientEvents::registerRecipeBookCategories);
		bus.addListener(UndergardenClientEvents::registerBlockColors);
		bus.addListener(UndergardenClientEvents::registerItemColors);
		bus.addListener(UndergardenClientEvents::registerOverlays);
		bus.addListener(UndergardenClientEvents::registerDimensionSpecialEffects);
		bus.addListener(UndergardenClientEvents::registerClientExtensions);
		bus.addListener(UndergardenClientEvents::registerDimensionTransitionScreens);
		bus.addListener(UndergardenClientEvents::registerItemDecorations);

		NeoForge.EVENT_BUS.addListener(UndergardenClientEvents::undergardenFog);
		NeoForge.EVENT_BUS.addListener(UndergardenClientEvents::dontRenderJumpBarForDweller);
		NeoForge.EVENT_BUS.addListener(UndergardenClientEvents::undergardenPortalFOV);
		NeoForge.EVENT_BUS.addListener(UndergardenClientEvents::renderVirulentHearts);
		NeoForge.EVENT_BUS.addListener(UndergardenClientEvents::addTooltips);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(UGWoodStuff.SMOGSTEM_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.GRONGLE_WOOD_TYPE);

			ItemProperties.register(UGItems.SLINGSHOT.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "pull"), (stack, level, entity, seed) -> {
				if (entity == null) {
					return 0.0F;
				} else {
					return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
				}
			});
			ItemProperties.register(UGItems.SLINGSHOT.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "rotten_blisterberry"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGItems.ROTTEN_BLISTERBERRY.get()) ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "goo_ball"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGItems.GOO_BALL.get()) ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "gronglet"), (stack, level, entity, seed) -> entity != null && entity.getProjectile(stack).is(UGBlocks.GRONGLET.get().asItem()) ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "self_sling"), (stack, level, entity, seed) -> entity != null && stack.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(UGEnchantments.SELF_SLING)) > 0 ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SLINGSHOT.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.CLOGGRUM_SHIELD.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
			ItemProperties.register(UGItems.SPEAR.get(), ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "throwing"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
		});
	}

	private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(UGBlockEntities.DEPTHROCK_BED.get(), DepthrockBedRender::new);
		event.registerBlockEntityRenderer(UGBlockEntities.GRONGLET.get(), GrongletRender::new);
		//
		event.registerEntityRenderer(UGEntityTypes.BOOMGOURD.get(), BoomgourdRenderer::new);
		//
		event.registerEntityRenderer(UGEntityTypes.DEPTHROCK_PEBBLE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GOO_BALL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.BLISTERBOMB.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GRONGLET.get(), GrongletEntityRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.SPEAR.get(), ThrownSpearRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.MINION_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTBELCHER_PROJECTILE.get(), NoopRenderer::new);
		//
		event.registerEntityRenderer(UGEntityTypes.MINION.get(), MinionRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTLING.get(), RotlingRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTWALKER.get(), RotwalkerRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTBEAST.get(), RotbeastRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTBELCHER.get(), RotbelcherRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.DWELLER.get(), DwellerRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GREATER_DWELLER.get(), GreaterDwellerRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GWIBLING.get(), GwiblingRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.BRUTE.get(), BruteRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.SCINTLING.get(), ScintlingRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GLOOMPER.get(), GloomperRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.STONEBORN.get(), StonebornRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.NARGOYLE.get(), NargoyleRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.MUNCHER.get(), MuncherRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.SPLOOGIE.get(), SploogieRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.GWIB.get(), GwibRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.MOG.get(), MogRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.SMOG_MOG.get(), SmogMogRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.FORGOTTEN.get(), ForgottenRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.DENIZEN.get(), DenizenRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), ForgottenGuardianRenderer::new);
	}

	private static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_HEAD, DepthrockBedRender::createHeadLayer);
		event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_FOOT, DepthrockBedRender::createFootLayer);
		event.registerLayerDefinition(UGModelLayers.GRONGLET, GrongletRender::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.MINION, MinionModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTLING, RotlingModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTWALKER, RotwalkerModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTBEAST, RotbeastModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DWELLER, () -> DwellerModel.createBodyLayer(0.0F));
		event.registerLayerDefinition(UGModelLayers.DWELLER_SADDLE, () -> DwellerModel.createBodyLayer(0.5F));
		event.registerLayerDefinition(UGModelLayers.GREATER_DWELLER, GreaterDwellerModel::createBodyLayer);
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
		event.registerLayerDefinition(UGModelLayers.DENIZEN, DenizenModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DENIZEN_2, Denizen2Model::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DENIZEN_MASK, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(1.0F), 0.0F), 64, 32));
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_INNER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.1F), 0.0F), 64, 32));
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.2F), 0.0F), 64, 32));
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_GUARDIAN, ForgottenGuardianModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTBELCHER, RotbelcherModel::createBodyLayer);
	}

	private static void addEntityLayers(EntityRenderersEvent.AddLayers event) {
		EntityRendererProvider.Context context = event.getContext();
		for (EntityType<?> entity : event.getEntityTypes()) {
			var renderer = event.getRenderer(entity);
			if (renderer instanceof LivingEntityRenderer<?,?> livingEntityRenderer) {
				addInfectionLayer(livingEntityRenderer);
			}
			if (renderer instanceof HumanoidMobRenderer<?,?> humanoidMobRenderer) {
				addMaskLayer(humanoidMobRenderer, context);
			}
		}

		event.getSkins().forEach(renderer -> {
			LivingEntityRenderer<Player, HumanoidModel<Player>> skin = event.getSkin(renderer);
			addInfectionLayer(Objects.requireNonNull(skin));
			addMaskLayer(Objects.requireNonNull(skin), context);
		});
	}

	private static <T extends LivingEntity, M extends EntityModel<T>> void addInfectionLayer(LivingEntityRenderer<T, M> renderer) {
		renderer.addLayer(new UthericInfectionLayer<>(renderer));
	}

	private static <T extends LivingEntity, M extends HumanoidModel<T>> void addMaskLayer(LivingEntityRenderer<T, M> renderer, EntityRendererProvider.Context context) {
		renderer.addLayer(new DenizenMaskLayer<>(renderer, new HumanoidModel<>(context.bakeLayer(UGModelLayers.DENIZEN_MASK))));
	}

	private static void registerParticleFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(UGParticleTypes.SHARD.get(), ShardParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.SHARD_BEAM.get(), ShardParticle.BeamProvider::new);
		event.registerSpriteSet(UGParticleTypes.GRONGLE_SPORE.get(), GrongleSporeParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.UNDERGARDEN_PORTAL.get(), UndergardenPortalParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.GLOOMPER_FART.get(), SmokeParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.SHIMMER.get(), ShimmerParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.SMOG.get(), SmogParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.UTHERIUM_CRIT.get(), UtheriumCritParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.SNOWFLAKE.get(), SnowflakeParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.ROGDORIUM_SPARKLE.get(), ShimmerParticle.Provider::new);

		event.registerSprite(UGParticleTypes.DRIPPING_BLOOD.get(), UGDripParticles::createBloodHangParticle);
		event.registerSprite(UGParticleTypes.FALLING_BLOOD.get(), UGDripParticles::createBloodFallParticle);
		event.registerSprite(UGParticleTypes.LANDING_BLOOD.get(), UGDripParticles::createBloodLandParticle);
		event.registerSprite(UGParticleTypes.DRIPPING_INK.get(), UGDripParticles::createInkHangParticle);
		event.registerSprite(UGParticleTypes.FALLING_INK.get(), UGDripParticles::createInkFallParticle);
		event.registerSprite(UGParticleTypes.LANDING_INK.get(), UGDripParticles::createInkLandParticle);
		event.registerSprite(UGParticleTypes.FALLING_GOO.get(), UGDripParticles::createGooFallParticle);
		event.registerSprite(UGParticleTypes.LANDING_GOO.get(), UGDripParticles::createGooLandParticle);
		event.registerSprite(UGParticleTypes.DRIPPING_VIRULENT.get(), UGDripParticles::createDripstoneVirulentHangParticle);
		event.registerSprite(UGParticleTypes.FALLING_VIRULENT.get(), UGDripParticles::createDripstoneVirulentFallParticle);
		event.registerSprite(UGParticleTypes.LANDING_VIRULENT.get(), UGDripParticles::createVirulentLandParticle);
	}

	private static void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(UGMenuTypes.INFUSER.get(), InfuserScreen::new);
	}

	private static void registerRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
		event.registerBookCategories(UGRecipeBookTypes.INFUSER, ImmutableList.of(UGRecipeBookCategories.INFUSER_SEARCH, UGRecipeBookCategories.INFUSER_PURIFYING, UGRecipeBookCategories.INFUSER_CORRUPTING, UGRecipeBookCategories.INFUSER_MISC));
		event.registerAggregateCategory(UGRecipeBookCategories.INFUSER_SEARCH, ImmutableList.of(UGRecipeBookCategories.INFUSER_PURIFYING, UGRecipeBookCategories.INFUSER_CORRUPTING, UGRecipeBookCategories.INFUSER_MISC));
		event.registerRecipeCategoryFinder(UGRecipeTypes.INFUSING.get(), recipe -> {
			if (recipe.value() instanceof InfusingRecipe infusingRecipe) {
				if (infusingRecipe.getCategory() == InfusingBookCategory.PURIFYING) {
					return UGRecipeBookCategories.INFUSER_PURIFYING;
				} else if (infusingRecipe.getCategory() == InfusingBookCategory.CORRUPTING) {
					return UGRecipeBookCategories.INFUSER_CORRUPTING;
				}
			}
			return UGRecipeBookCategories.INFUSER_MISC;
		});
	}

	private static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, tintGetter, pos, tint) ->
						tintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(tintGetter, pos) : FastColor.ARGB32.color(255, 91, 117, 91),
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

		event.register((state, world, pos, tint) -> FastColor.ARGB32.color(255, 54, 45, 66),
				UGBlocks.GLOOMGOURD_STEM.get(),
				UGBlocks.GLOOMGOURD_STEM_ATTACHED.get()
		);
	}

	private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
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
						return FastColor.ARGB32.color(255, 91, 117, 91);
					}
					return -1;
				},

				UGBlocks.SHIMMERWEED.get(),
				UGBlocks.TALL_SHIMMERWEED.get()
		);
	}

	private static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
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
	}

	private static void renderVirulentHearts(PlayerHeartTypeEvent event) {
		if (event.getEntity().hasEffect(UGEffects.VIRULENCE)) {
			event.setType(Gui.HeartType.valueOf("UNDERGARDEN_VIRULENT"));
		}
	}

	private static void registerOverlays(RegisterGuiLayersEvent event) {
		event.registerAbove(VanillaGuiLayers.ARMOR_LEVEL, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "brittleness_armor"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.hasEffect(UGEffects.BRITTLENESS) && minecraft.gameMode.canHurtPlayer()) {
				renderBrittlenessArmor(guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics, player);
			}
		});
		//render XP bar since we cancel the jump bar
		//vanilla hardcodes the XP bar to not render when riding a jumping vehicle sadly
		event.registerAbove(VanillaGuiLayers.EXPERIENCE_BAR, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "dweller_xp_bar"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.getVehicle() instanceof Dweller dweller && dweller.canJump() && minecraft.gameMode.hasExperience()) {
				minecraft.gui.renderExperienceBar(guiGraphics, guiGraphics.guiWidth() / 2 - 91);
			}
		});
		event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "undergarden_portal_overlay"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			Window window = minecraft.getWindow();
			LocalPlayer player = minecraft.player;

			if (player != null) {
				renderPortalOverlay(guiGraphics, minecraft, window, deltaTracker.getGameTimeDeltaPartialTick(true));
			}
		});
		event.registerAbove(VanillaGuiLayers.CAMERA_OVERLAYS, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "carved_gloomgourd_overlay"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			ResourceLocation overlay = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gloomgourd_overlay.png");
			LocalPlayer player = minecraft.player;
			if (player != null && player.getInventory().getArmor(3).is(UGBlocks.CARVED_GLOOMGOURD.asItem())) {
				minecraft.gui.renderTextureOverlay(guiGraphics, overlay, 1.0F);
			}
		});
		event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "utheric_infection_bar"), (gui, partialTick) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && player.getData(UGAttachments.UTHERIC_INFECTION.get()) > 0.0F && minecraft.gameMode.canHurtPlayer()) {
				renderUthericInfectionBar(gui.guiWidth(), gui.guiHeight(), gui, minecraft.gui, player);
			}
		});
	}

	private static void undergardenFog(ViewportEvent.RenderFog event) {
		if (UndergardenConfig.Client.toggle_undergarden_fog.get()) {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player != null && player.level().dimension() == UGDimensions.UNDERGARDEN_LEVEL && event.getCamera().getFluidInCamera() == FogType.NONE && event.getType() == FogType.NONE && !player.isEyeInFluidType(UGFluids.VIRULENT_MIX_TYPE.get())) {
				if (player.level().getBiome(player.getOnPos()).is(UGTags.Biomes.IS_DEPTHS_BIOME)) {
					event.setNearPlaneDistance(-30.0F);
					event.setFarPlaneDistance(100.0F);
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

	private static void dontRenderJumpBarForDweller(RenderGuiLayerEvent.Pre event) {
		if (event.getName() == VanillaGuiLayers.JUMP_METER) {
			if (Minecraft.getInstance().player.getVehicle() instanceof Dweller) {
				event.setCanceled(true);
			}
		}
	}

	private static void undergardenPortalFOV(ComputeFovModifierEvent event) {
		if (UndergardenPortalVisuals.getPortalAnimTime() > 0.0F) {
			event.setNewFovModifier(event.getFovModifier() - UndergardenPortalVisuals.getPortalAnimTime());
		}
	}

	private static void renderUthericInfectionBar(int width, int height, GuiGraphics graphics, Gui gui, Player player) {
		int left = width / 2 + 91;
		int top = height - gui.rightHeight;
		gui.rightHeight += 10;

		int infectionLevel = Mth.ceil(player.getData(UGAttachments.UTHERIC_INFECTION));
		graphics.drawString(Minecraft.getInstance().font, player.getData(UGAttachments.UTHERIC_INFECTION).toString(), left, top, 10500660);
		for (int i = 0; i < 10; i++) {
			int idx = i * 2 + 1;
			int x = left - i * 8 - 9;
			int y = top;
			if (infectionLevel >= 16) {
				y += gui.random.nextInt(2);
			}
            if (idx < infectionLevel) {
				graphics.blitSprite(infectionLevel >= 20 ? UTHERIC_INFECTION_FULL_LETHAL : UTHERIC_INFECTION_FULL, x, y, 9, 9);
			} else if (idx == infectionLevel) {
				graphics.blitSprite(UTHERIC_INFECTION_HALF, x, y, 9, 9);
			} else {
				graphics.blitSprite(UTHERIC_INFECTION_EMPTY, x, y, 9, 9);
			}
		}
	}


	private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new UndergardenBEWLR();
			}
		}, UGBlocks.DEPTHROCK_BED.asItem(), UGBlocks.GRONGLET.asItem());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			@Override
			public ResourceLocation getStillTexture() {
				return ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "fluid/virulent_mix_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "fluid/virulent_mix_flow");
			}

			@Override
			public ResourceLocation getOverlayTexture() {
				return ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "fluid/virulent_mix_flow");
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTicks, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return new Vector3f(57 / 255F, 25 / 255F, 80 / 255F);
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTicks, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0.0F);
				RenderSystem.setShaderFogEnd(3.0F);
			}
		}, UGFluids.VIRULENT_MIX_TYPE.get());
	}

	private static void registerDimensionTransitionScreens(RegisterDimensionTransitionScreenEvent event) {
		event.registerIncomingEffect(UGDimensions.UNDERGARDEN_LEVEL, UndergardenReceivingLevelScreen::new);
		event.registerOutgoingEffect(UGDimensions.UNDERGARDEN_LEVEL, UndergardenReceivingLevelScreen::new);
	}

	private static void registerItemDecorations(RegisterItemDecorationsEvent event) {
		BuiltInRegistries.ITEM.stream().filter(item -> item instanceof ArmorItem).forEach(item -> event.register(item, ((guiGraphics, font, stack, xOffset, yOffset) -> {
			int infusionAmount = stack.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
			int infusionMax = stack.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionMax();
			if (infusionAmount > 0) {
				int barWidth = Math.round(infusionAmount * 13.0F / infusionMax);
				int x = xOffset + 2;
				int y = yOffset + (stack.isBarVisible() ? 11 : 13);
				guiGraphics.fill(RenderType.guiOverlay(), x, y, x + 13, y + 2, -16777216);
				guiGraphics.fill(RenderType.guiOverlay(), x, y, x + (infusionAmount == infusionMax ? 13 : barWidth), y + 1, 8236977 | 0xFF000000);
				return true;
			}
			return false;
		})));
	}

	private static void addTooltips(ItemTooltipEvent event) {
		TooltipFlag flags = event.getFlags();
		List<Component> tooltip = event.getToolTip();
		ItemStack stack = event.getItemStack();
		if (flags.isAdvanced()) {
			if (stack.getItem().components().has(UGDataComponents.ROGDORIUM_INFUSION.get())) {
				int infusionAmount = stack.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
				int infusionMax = stack.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionMax();
				if (infusionAmount > 0) {
					tooltip.add(1, Component.translatable("item.undergarden.rogdorium_infusion").append(": " + infusionAmount + "/" + infusionMax).withStyle(ChatFormatting.AQUA));
				}
			}
		}
	}

	private static void renderBrittlenessArmor(int width, int height, GuiGraphics graphics, Player player) {
		int x = width / 2 - 91;
		int y = height - 49;

		int level = player.getArmorValue();
		for (int i = 1; level > 0 && i < 20; i += 2) {
			if (i < level) {
				graphics.blitSprite(BRITTLENESS_ARMOR_FULL, x, y, 9, 9);
			} else if (i == level) {
				graphics.blitSprite(BRITTLENESS_ARMOR_HALF, x, y, 9, 9);
			} else {
				graphics.blitSprite(BRITTLENESS_ARMOR_EMPTY, x, y, 9, 9);
			}
			x += 8;
		}
	}

	private static void renderPortalOverlay(GuiGraphics graphics, Minecraft minecraft, Window window, float partialTicks) {
		float alpha = Mth.lerp(partialTicks, UndergardenPortalVisuals.getPrevPortalAnimTime(), UndergardenPortalVisuals.getPortalAnimTime());
		if (alpha > 0.0F) {
			if (alpha < 1.0F) {
				alpha = alpha * alpha;
				alpha = alpha * alpha;
				alpha = alpha * 0.8F + 0.2F;
			}

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			graphics.setColor(1.0F, 1.0F, 1.0F, alpha);
			TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState());
			graphics.blit(0, 0, -90, window.getGuiScaledWidth(), window.getGuiScaledHeight(), textureatlassprite);
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}