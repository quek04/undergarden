package quek.undergarden.client;

import net.minecraft.client.Camera;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.client.model.*;
import quek.undergarden.client.render.blockentity.DepthrockBedRender;
import quek.undergarden.client.render.blockentity.GrongletRender;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.entity.UGBoat;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGFluids;

import java.awt.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergardenClient {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType mipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

        render(UGBlocks.DEEPTURF_BLOCK, mipped);
        render(UGBlocks.DEEPTURF, cutout);
        render(UGBlocks.SHIMMERWEED, cutout);
        render(UGBlocks.SMOGSTEM_SAPLING, cutout);
        render(UGBlocks.WIGGLEWOOD_SAPLING, cutout);
        render(UGBlocks.INDIGO_MUSHROOM, cutout);
        render(UGBlocks.VEIL_MUSHROOM, cutout);
        render(UGBlocks.INK_MUSHROOM, cutout);
        render(UGBlocks.BLOOD_MUSHROOM, cutout);
        render(UGBlocks.UNDERBEAN_BUSH, cutout);
        render(UGBlocks.DITCHBULB_PLANT, cutout);
        render(UGBlocks.TALL_DEEPTURF, cutout);
        render(UGBlocks.TALL_SHIMMERWEED, cutout);
        render(UGBlocks.CLOGGRUM_BARS, cutout);
        render(UGBlocks.GLITTERKELP, cutout);
        render(UGBlocks.GLITTERKELP_PLANT, cutout);
        render(UGBlocks.GOO, translucent);
        render(UGBlocks.SMOGSTEM_DOOR, cutout);
        render(UGBlocks.WIGGLEWOOD_DOOR, cutout);
        render(UGBlocks.SMOGSTEM_TRAPDOOR, cutout);
        render(UGBlocks.WIGGLEWOOD_TRAPDOOR, cutout);
        render(UGBlocks.ASHEN_DEEPTURF, cutout);
        render(UGBlocks.BLISTERBERRY_BUSH, cutout);
        render(UGBlocks.GLOOMGOURD_STEM, cutout);
        render(UGBlocks.GLOOMGOURD_STEM_ATTACHED, cutout);
        render(UGBlocks.SHARD_TORCH, cutout);
        render(UGBlocks.SHARD_WALL_TORCH, cutout);
        render(UGBlocks.DROOPVINE, cutout);
        render(UGBlocks.DROOPVINE_PLANT, cutout);
        render(UGBlocks.GRONGLE_SAPLING, cutout);
        render(UGBlocks.GRONGLE_DOOR, cutout);
        render(UGBlocks.GRONGLE_TRAPDOOR, cutout);
        render(UGBlocks.SEEPING_INK, cutout);
        render(UGBlocks.MUSHROOM_VEIL_PLANT, cutout);
        render(UGBlocks.MUSHROOM_VEIL, cutout);
        render(UGBlocks.POTTED_SHIMMERWEED, cutout);
        render(UGBlocks.POTTED_SMOGSTEM_SAPLING, cutout);
        render(UGBlocks.POTTED_WIGGLEWOOD_SAPLING, cutout);
        render(UGBlocks.POTTED_INDIGO_MUSHROOM, cutout);
        render(UGBlocks.POTTED_VEIL_MUSHROOM, cutout);
        render(UGBlocks.POTTED_INDIGO_MUSHROOM, cutout);
        render(UGBlocks.POTTED_INK_MUSHROOM, cutout);
        render(UGBlocks.POTTED_BLOOD_MUSHROOM, cutout);
        render(UGBlocks.POTTED_GRONGLE_SAPLING, cutout);
        render(UGBlocks.FROZEN_DEEPTURF, cutout);
        render(UGBlocks.SEDIMENT_GLASS, translucent);
        render(UGBlocks.SEDIMENT_GLASS_PANE, translucent);
        render(UGBlocks.HANGING_GRONGLE_LEAVES, cutout);
        render(UGBlocks.GOO_BLOCK, translucent);
        render(UGBlocks.CLOGGRUM_LANTERN, cutout);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(UGBlockEntities.DEPTHROCK_BED.get(), DepthrockBedRender::new);
        event.registerBlockEntityRenderer(UGBlockEntities.UNDERGARDEN_SIGN.get(), SignRenderer::new);
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
        event.registerEntityRenderer(UGEntityTypes.MASTICATOR.get(), MasticatorRender::new);
        event.registerEntityRenderer(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), ForgottenGuardianRender::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_HEAD, DepthrockBedRender::createHeadLayer);
        event.registerLayerDefinition(UGModelLayers.DEPTHROCK_BED_FOOT, DepthrockBedRender::createFootLayer);
        event.registerLayerDefinition(UGModelLayers.GRONGLET, GrongletRender::createBodyLayer);
        //
        for(UGBoat.Type boatType : UGBoat.Type.values()) {
            event.registerLayerDefinition(UGBoatRenderer.createBoatModelName(boatType), () -> BoatModel.createBodyModel(false));
            event.registerLayerDefinition(UGBoatRenderer.createChestBoatModelName(boatType), () -> BoatModel.createBodyModel(true));
        }
        //
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
        event.registerLayerDefinition(UGModelLayers.MASTICATOR, MasticatorModel::createBodyLayer);
        event.registerLayerDefinition(UGModelLayers.FORGOTTEN_GUARDIAN, ForgottenGuardianModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();

        colors.register((state, tintGetter, pos, tint) ->
                        tintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(tintGetter, pos) : new Color(91, 117, 91).getRGB(),
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

        colors.register((state, world, pos, tint) ->
                        new Color(54, 45, 66).getRGB(),
                UGBlocks.GLOOMGOURD_STEM.get(),
                UGBlocks.GLOOMGOURD_STEM_ATTACHED.get()
        );
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        BlockColors bColors = event.getBlockColors();
        ItemColors iColors = event.getItemColors();

        iColors.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, 0),
                UGBlocks.DEEPTURF_BLOCK.get(),
                UGBlocks.DEEPTURF.get(),
                UGBlocks.SHIMMERWEED.get(),
                UGBlocks.TALL_SHIMMERWEED.get(),
                UGBlocks.TALL_DEEPTURF.get()
        );

        iColors.register((stack, tint) -> {
                    if(tint == 0) {
                        return new Color(91, 117, 91).getRGB();
                    }
                    return -1;
                },

                UGBlocks.SHIMMERWEED.get(),
                UGBlocks.TALL_SHIMMERWEED.get()
        );
    }

    @Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusEvents {
        @SubscribeEvent
        public static void renderVirulentFogColor(EntityViewRenderEvent.FogColors event) {
            Camera camera = event.getCamera();
            FluidState fluidState = camera.getBlockAtCamera().getFluidState();

            if(fluidState.getType() == UGFluids.VIRULENT_MIX_FLOWING.get() || fluidState.getType() == UGFluids.VIRULENT_MIX_SOURCE.get()) {
                event.setRed(57 / 255F);
                event.setGreen(25 / 255F);
                event.setBlue(80 / 255F);
            }
        }

        @SubscribeEvent
        public static void renderVirulentFogDensity(EntityViewRenderEvent.RenderFogEvent event) {
            Camera camera = event.getCamera();
            FluidState fluidState = camera.getBlockAtCamera().getFluidState();

            if(fluidState.getType() == UGFluids.VIRULENT_MIX_FLOWING.get() || fluidState.getType() == UGFluids.VIRULENT_MIX_SOURCE.get()) {
                event.setFarPlaneDistance(15.0F);
                event.setCanceled(true);
            }
        }
    }
}