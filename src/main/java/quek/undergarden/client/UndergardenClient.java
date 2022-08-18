package quek.undergarden.client;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.client.model.*;
import quek.undergarden.client.render.blockentity.DepthrockBedRender;
import quek.undergarden.client.render.blockentity.GrongletRender;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.entity.UGBoat;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGEntityTypes;

import java.awt.*;

@Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergardenClient {

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
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, tintGetter, pos, tint) ->
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

        event.register((state, world, pos, tint) ->
                        new Color(54, 45, 66).getRGB(),
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
                    if(tint == 0) {
                        return new Color(91, 117, 91).getRGB();
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
    }

    @Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusEvents {

    }
}