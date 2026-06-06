package quek.undergarden.client.event;

import com.google.common.reflect.TypeToken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.BuiltInBlockModels;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import org.joml.Vector4f;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.block.DepthrockBedBlock;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.client.gui.screen.UndergardenReceivingLevelScreen;
import quek.undergarden.client.gui.screen.inventory.InfuserScreen;
import quek.undergarden.client.model.*;
import quek.undergarden.client.model.item.CloggrumBucketModel;
import quek.undergarden.client.model.item.PullingSlingshotModel;
import quek.undergarden.client.particle.*;
import quek.undergarden.client.render.blockentity.DepthrockBedRenderer;
import quek.undergarden.client.render.blockentity.DepthrockPotRenderer;
import quek.undergarden.client.render.blockentity.GrongletRender;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.client.render.item.*;
import quek.undergarden.client.render.layer.UthericInfectionLayer;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.registry.*;

import java.util.List;

public class UGClientRegistrationEvents {

	public static void init(IEventBus bus) {
		bus.addListener(UGClientRegistrationEvents::clientSetup);
		bus.addListener(UGClientRegistrationEvents::registerEntityRenderers);
		bus.addListener(UGClientRegistrationEvents::registerEntityLayerDefinitions);
		bus.addListener(EntityRenderersEvent.AddLayers.class, UGClientRegistrationEvents::addEntityLayers);
		bus.addListener(UGClientRegistrationEvents::registerParticleFactories);
		bus.addListener(UGClientRegistrationEvents::registerMenuScreens);
		bus.addListener(UGClientRegistrationEvents::registerBlockColors);
		bus.addListener(UGClientRegistrationEvents::registerClientExtensions);
		bus.addListener(UGClientRegistrationEvents::registerDimensionTransitionScreens);
		bus.addListener(UGClientRegistrationEvents::registerItemDecorations);
		bus.addListener(UGClientRegistrationEvents::registerCustomRenderData);
		bus.addListener(UGClientRegistrationEvents::registerFluidModels);
		bus.addListener(UGClientRegistrationEvents::registerSpecialBlockModels);
		bus.addListener(UGClientRegistrationEvents::registerSpecialItemModels);
		bus.addListener(UGClientRegistrationEvents::registerSpecialModels);
		bus.addListener(UGClientRegistrationEvents::registerInfuserSearchCategory);

		UGClientEvents.init();
		UGOverlayEvents.init(bus);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(UGWoodStuff.SMOGSTEM_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.GRONGLE_WOOD_TYPE);
			Sheets.addWoodType(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE);
		});
	}

	//Entities
	private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(UGBlockEntities.DEPTHROCK_BED.get(), DepthrockBedRenderer::new);
		event.registerBlockEntityRenderer(UGBlockEntities.DEPTHROCK_POT.get(), DepthrockPotRenderer::new);
		event.registerBlockEntityRenderer(UGBlockEntities.GRONGLET.get(), GrongletRender::new);

		event.registerEntityRenderer(UGEntityTypes.BOOMGOURD.get(), BoomgourdRenderer::new);

		event.registerEntityRenderer(UGEntityTypes.BLISTERBOMB.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.SLINGSHOT_PROJECTILE.get(), SlingshotProjectileRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.JAVELIN.get(), ThrownJavelinRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.MINION_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.ROTBELCHER_PROJECTILE.get(), NoopRenderer::new);

		event.registerEntityRenderer(UGEntityTypes.WIGGLEWOOD_BOAT.get(), context -> new BoatRenderer(context, UGModelLayers.WIGGLEWOOD_BOAT));
		event.registerEntityRenderer(UGEntityTypes.WIGGLEWOOD_CHEST_BOAT.get(), context -> new BoatRenderer(context, UGModelLayers.WIGGLEWOOD_CHEST_BOAT));
		event.registerEntityRenderer(UGEntityTypes.SMOGSTEM_BOAT.get(), context ->  new BoatRenderer(context, UGModelLayers.SMOGSTEM_BOAT));
		event.registerEntityRenderer(UGEntityTypes.SMOGSTEM_CHEST_BOAT.get(), context -> new BoatRenderer(context, UGModelLayers.SMOGSTEM_CHEST_BOAT));
		event.registerEntityRenderer(UGEntityTypes.GRONGLE_BOAT.get(), context -> new BoatRenderer(context, UGModelLayers.GRONGLE_BOAT));
		event.registerEntityRenderer(UGEntityTypes.GRONGLE_CHEST_BOAT.get(), context ->  new BoatRenderer(context, UGModelLayers.GRONGLE_CHEST_BOAT));
		event.registerEntityRenderer(UGEntityTypes.ANCIENT_ROOT_BOAT.get(), context -> new BoatRenderer(context, UGModelLayers.ANCIENT_ROOT_BOAT));
		event.registerEntityRenderer(UGEntityTypes.ANCIENT_ROOT_CHEST_BOAT.get(), context ->  new BoatRenderer(context, UGModelLayers.ANCIENT_ROOT_CHEST_BOAT));

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
		event.registerEntityRenderer(UGEntityTypes.MYSTERIOUS_POT.get(), MysteriousPotRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), ForgottenGuardianRenderer::new);
		event.registerEntityRenderer(UGEntityTypes.UNDERGAR.get(), UndergarRenderer::new);
	}

	private static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_HEAD, DepthrockBedRenderer::createHeadLayer);
		event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_FOOT, DepthrockBedRenderer::createFootLayer);
		event.registerLayerDefinition(UGModelLayers.GRONGLET, GrongletModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.MINION, MinionModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTLING, RotlingModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTWALKER, RotwalkerModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTBEAST, RotbeastModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DWELLER, () -> DwellerModel.create(0.0F));
		event.registerLayerDefinition(UGModelLayers.DWELLER_BABY, DwellerModel::createBaby);
		event.registerLayerDefinition(UGModelLayers.DWELLER_SADDLE, () -> DwellerModel.create(0.5F));
		event.registerLayerDefinition(UGModelLayers.GREATER_DWELLER, GreaterDwellerModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.GWIBLING, GwiblingModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.BRUTE, BruteModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.SCINTLING, ScintlingModel::create);
		event.registerLayerDefinition(UGModelLayers.SCINTLING_BABY, ScintlingModel::createBaby);
		event.registerLayerDefinition(UGModelLayers.GLOOMPER, GloomperModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.STONEBORN, StonebornModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.NARGOYLE, NargoyleModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.MUNCHER, MuncherModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.SPLOOGIE, SploogieModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.GWIB, GwibModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.MOG, MogModel::create);
		event.registerLayerDefinition(UGModelLayers.MOG_BABY, MogModel::createBaby);
		event.registerLayerDefinition(UGModelLayers.SMOG_MOG, SmogMogModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN, ForgottenModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DENIZEN, DenizenModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DENIZEN_2, DenizenModel::createTallBodyLayer);
		event.registerLayerDefinition(UGModelLayers.DENIZEN_MASK, () -> LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(1.0F), 0.0F), 64, 32));
		event.registerLayerDefinition(UGModelLayers.FORGOTTEN_GUARDIAN, ForgottenGuardianModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.ROTBELCHER, RotbelcherModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.LIVING_POT, MysteriousPotModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.POT, PotModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.CLOGGRUM_SHIELD, CloggrumShieldModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.JAVELIN, JavelinModel::createBodyLayer);
		event.registerLayerDefinition(UGModelLayers.UNDERGAR, UndergarModel::createBodyLayer);
		LayerDefinition boatModel = BoatModel.createBoatModel();
		LayerDefinition chestBoatModel = BoatModel.createChestBoatModel();
		event.registerLayerDefinition(UGModelLayers.WIGGLEWOOD_BOAT, () -> boatModel);
		event.registerLayerDefinition(UGModelLayers.WIGGLEWOOD_CHEST_BOAT, () -> chestBoatModel);
		event.registerLayerDefinition(UGModelLayers.SMOGSTEM_BOAT, () -> boatModel);
		event.registerLayerDefinition(UGModelLayers.SMOGSTEM_CHEST_BOAT, () -> chestBoatModel);
		event.registerLayerDefinition(UGModelLayers.GRONGLE_BOAT, () -> boatModel);
		event.registerLayerDefinition(UGModelLayers.GRONGLE_CHEST_BOAT, () -> chestBoatModel);
		event.registerLayerDefinition(UGModelLayers.ANCIENT_ROOT_BOAT, () -> boatModel);
		event.registerLayerDefinition(UGModelLayers.ANCIENT_ROOT_CHEST_BOAT, () -> chestBoatModel);

		ArmorModelSet<MeshDefinition> humanoidArmor = HumanoidModel.createArmorMeshSet(LayerDefinitions.INNER_ARMOR_DEFORMATION, LayerDefinitions.OUTER_ARMOR_DEFORMATION);
		registerArmorModelSet(event, UGModelLayers.FORGOTTEN_ARMOR, humanoidArmor);
	}

	private static void registerArmorModelSet(EntityRenderersEvent.RegisterLayerDefinitions event, ArmorModelSet<ModelLayerLocation> set, ArmorModelSet<MeshDefinition> target) {
		event.registerLayerDefinition(set.head(), () -> LayerDefinition.create(target.head(), 64, 32));
		event.registerLayerDefinition(set.chest(), () -> LayerDefinition.create(target.chest(), 64, 32));
		event.registerLayerDefinition(set.legs(), () -> LayerDefinition.create(target.legs(), 64, 32));
		event.registerLayerDefinition(set.feet(), () -> LayerDefinition.create(target.feet(), 64, 32));
	}

	@SuppressWarnings("unchecked")
	private static <T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>> void addEntityLayers(EntityRenderersEvent.AddLayers event) {
		for (EntityType<?> entity : event.getEntityTypes()) {
			var renderer = event.getRenderer(entity);
			if (renderer instanceof LivingEntityRenderer<?, ?, ?> livingEntityRenderer) {
				addInfectionLayer((LivingEntityRenderer<T, S, M>) livingEntityRenderer);
			}
		}

		event.getSkins().forEach(renderer -> {
			LivingEntityRenderer<AbstractClientPlayer, AvatarRenderState, PlayerModel> skin = event.getPlayerRenderer(renderer);
			if (skin != null) {
				addInfectionLayer(skin);
			}
		});
	}

	private static <T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>> void addInfectionLayer(LivingEntityRenderer<T, S, M> renderer) {
		renderer.addLayer(new UthericInfectionLayer<>(renderer));
	}

	private static void registerCustomRenderData(RegisterRenderStateModifiersEvent event) {
		event.registerEntityModifier(new TypeToken<LivingEntityRenderer<?, ?, ?>>() {
		}, (living, state) -> state.setRenderData(UndergardenClient.UTHERIUM_INFECTION, living.getData(UGAttachments.UNDERGARDEN_DATA).uthericInfection()));
		event.registerEntityModifier(new TypeToken<LivingEntityRenderer<?, ?, ?>>() {
		}, (living, state) -> state.setRenderData(UndergardenClient.CHILLY, living.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(UGEffects.CHILLY_MODIFIER)));
	}

	//Particles
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
		event.registerSpriteSet(UGParticleTypes.TOTEM_BEAM.get(), TotemBeamParticle.Provider::new);
		event.registerSpriteSet(UGParticleTypes.ROGDORIUM_WISP.get(), RogdoriumWispParticle.Provider::new);

		event.registerSpriteSet(UGParticleTypes.DRIPPING_BLOOD.get(), UGDripParticles.BloodHangProvider::new);
		event.registerSpriteSet(UGParticleTypes.FALLING_BLOOD.get(), UGDripParticles.BloodFallProvider::new);
		event.registerSpriteSet(UGParticleTypes.LANDING_BLOOD.get(), UGDripParticles.BloodLandProvider::new);
		event.registerSpriteSet(UGParticleTypes.DRIPPING_INK.get(), UGDripParticles.InkHangProvider::new);
		event.registerSpriteSet(UGParticleTypes.FALLING_INK.get(), UGDripParticles.InkFallProvider::new);
		event.registerSpriteSet(UGParticleTypes.LANDING_INK.get(), UGDripParticles.InkLandProvider::new);
		event.registerSpriteSet(UGParticleTypes.FALLING_GOO.get(), UGDripParticles.GooFallProvider::new);
		event.registerSpriteSet(UGParticleTypes.LANDING_GOO.get(), UGDripParticles.GooLandProvider::new);
		event.registerSpriteSet(UGParticleTypes.DRIPPING_VIRULENT.get(), UGDripParticles.DripstoneVirulentHangProvider::new);
		event.registerSpriteSet(UGParticleTypes.FALLING_VIRULENT.get(), UGDripParticles.DripstoneVirulentFallProvider::new);
		event.registerSpriteSet(UGParticleTypes.LANDING_VIRULENT.get(), UGDripParticles.DripstoneVirulentLandProvider::new);
	}

	//items
	private static void registerSpecialItemModels(RegisterItemModelsEvent event) {
		event.register(Undergarden.prefix("cloggrum_bucket"), CloggrumBucketModel.Unbaked.MAP_CODEC);
		event.register(Undergarden.prefix("pulling_slingshot"), PullingSlingshotModel.Unbaked.MAP_CODEC);
	}

	private static void registerSpecialModels(RegisterSpecialModelRendererEvent event) {
		event.register(Undergarden.prefix("gronglet"), GrongletSpecialRenderer.Unbaked.MAP_CODEC);
		event.register(Undergarden.prefix("depthrock_bed"), DepthrockBedSpecialRenderer.Unbaked.MAP_CODEC);
		event.register(Undergarden.prefix("depthrock_pot"), DepthrockPotSpecialRenderer.Unbaked.MAP_CODEC);
		event.register(Undergarden.prefix("cloggrum_shield"), CloggrumShieldSpecialRenderer.Unbaked.MAP_CODEC);
		event.register(Undergarden.prefix("javelin"), JavelinSpecialRenderer.Unbaked.MAP_CODEC);
	}

	private static void registerItemDecorations(RegisterItemDecorationsEvent event) {
		BuiltInRegistries.ITEM.forEach(item -> event.register(item, ((guiGraphics, font, stack, xOffset, yOffset) -> {
			int infusionAmount = stack.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionAmount();
			int infusionMax = stack.getOrDefault(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.DEFAULT).infusionMax();
			if (infusionAmount > 0) {
				int barWidth = Math.round(infusionAmount * 13.0F / infusionMax);
				int x = xOffset + 2;
				int y = yOffset + (stack.isBarVisible() ? 11 : 13);
				guiGraphics.fill(RenderPipelines.GUI, x, y, x + 13, y + 2, -16777216);
				guiGraphics.fill(RenderPipelines.GUI, x, y, x + (infusionAmount == infusionMax ? 13 : barWidth), y + 1, 8236977 | 0xFF000000);
				return true;
			}
			return false;
		})));
	}

	private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {

			@Override
			public Identifier getRenderOverlayTexture(Minecraft mc) {
				return Undergarden.prefix("block/virulent_mix_flow");
			}

			@Override
			public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				fluidFogColor.set(57 / 255F, 25 / 255F, 80 / 255F, 1.0F);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				fogData.environmentalStart = 0.0F;
				fogData.environmentalEnd = 3.0F;
				fogData.skyEnd = fogData.environmentalEnd;
				fogData.cloudEnd = fogData.environmentalEnd;
			}
		}, UGFluids.VIRULENT_MIX_TYPE.get());

		event.registerItem(new IClientItemExtensions() {
			@Override
			public HumanoidModel.ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack itemStack) {
				return HumanoidModel.ArmPose.valueOf("UNDERGARDEN_BATTLEAXE_ARM_POSE");
			}

			@Override
			public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
				return true;
			}
		}, UGItems.CLOGGRUM_BATTLEAXE, UGItems.FORGOTTEN_BATTLEAXE);
	}

	//fluids
	private static void registerFluidModels(RegisterFluidModelsEvent event) {
		FluidModel.Unbaked virulentModel = new FluidModel.Unbaked(
			new Material(Undergarden.prefix("block/virulent_mix_still")),
			new Material(Undergarden.prefix("block/virulent_mix_flow")),
			null, null);
		event.register(virulentModel, UGFluids.VIRULENT_MIX_SOURCE.get());
		event.register(virulentModel, UGFluids.VIRULENT_MIX_FLOWING.get());
	}

	//blocks
	private static void registerSpecialBlockModels(RegisterBlockModelsEvent event) {
		event.register(BuiltInBlockModels.special(new GrongletSpecialRenderer.Unbaked(Undergarden.prefix("textures/entity/gronglet/gronglet.png"))), UGBlocks.GRONGLET.get());
		event.register(BuiltInBlockModels.special(new GrongletSpecialRenderer.Unbaked(Undergarden.prefix("textures/entity/gronglet/utheric_gronglet.png"))), UGBlocks.UTHERIC_GRONGLET.get());
		event.register(BuiltInBlockModels.special(new GrongletSpecialRenderer.Unbaked(Undergarden.prefix("textures/entity/gronglet/rogdoric_gronglet.png"))), UGBlocks.ROGDORIC_GRONGLET.get());
		event.register(BuiltInBlockModels.special(new DepthrockPotSpecialRenderer.Unbaked()), UGBlocks.DEPTHROCK_POT.get());
		event.register(BuiltInBlockModels.specialModelWithPropertyDispatch(DepthrockBedBlock.FACING, DepthrockBedBlock.PART, (facing, part) -> BuiltInBlockModels.special(new DepthrockBedSpecialRenderer.Unbaked(part), BedRenderer.modelTransform(facing))), UGBlocks.DEPTHROCK_BED.get());
	}

	private static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
		event.register(List.of(new BlockTintSource() {
				@Override
				public int color(BlockState state) {
					return UndergardenClient.DEFAULT_TINT_COLOR;
				}

				@Override
				public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
					return BiomeColors.getAverageGrassColor(level, pos);
				}
			}),
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

		event.register(List.of(BlockTintSources.constant(UndergardenClient.GLOOMGOURD_STEM_TINT)), UGBlocks.GLOOMGOURD_STEM.get(), UGBlocks.GLOOMGOURD_STEM_ATTACHED.get());
	}

	//Misc.
	private static void registerDimensionTransitionScreens(RegisterDimensionTransitionScreenEvent event) {
		event.registerIncomingEffect(UGDimensions.UNDERGARDEN_LEVEL, UndergardenReceivingLevelScreen::new);
		event.registerOutgoingEffect(UGDimensions.UNDERGARDEN_LEVEL, UndergardenReceivingLevelScreen::new);
	}

	private static void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(UGMenuTypes.INFUSER.get(), InfuserScreen::new);
	}

	private static void registerInfuserSearchCategory(RegisterRecipeBookSearchCategoriesEvent event) {
		event.register(UGRecipeBookCategories.INFUSER_SEARCH.get(), UGRecipeBookCategories.INFUSER_CORRUPTING.get(), UGRecipeBookCategories.INFUSER_PURIFYING.get(), UGRecipeBookCategories.INFUSER_MISC.get());
	}
}