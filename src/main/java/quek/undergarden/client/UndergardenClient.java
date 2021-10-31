package quek.undergarden.client;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.client.model.*;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.entity.UGBoatEntity;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGFluids;

import java.awt.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT)
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
        render(UGBlocks.GLOWING_KELP, cutout);
        render(UGBlocks.GLOWING_KELP_PLANT, cutout);
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
        render(UGBlocks.DROOPVINE_TOP, cutout);
        render(UGBlocks.DROOPVINE, cutout);
        render(UGBlocks.GRONGLE_SAPLING, cutout);
        render(UGBlocks.GRONGLE_DOOR, cutout);
        render(UGBlocks.GRONGLE_TRAPDOOR, cutout);
        render(UGBlocks.VIRULENT_MIX, translucent);
        render(UGBlocks.SEEPING_INK, cutout);
        render(UGBlocks.MUSHROOM_VEIL, cutout);
        render(UGBlocks.MUSHROOM_VEIL_TOP, cutout);
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
        render(UGBlocks.HANGING_GRONGLE_LEAVES_TOP, cutout);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(UGEntityTypes.BOAT.get(), UGBoatRenderer::new);
        //
        event.registerEntityRenderer(UGEntityTypes.SLINGSHOT_AMMO.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(UGEntityTypes.GOO_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(UGEntityTypes.BLISTERBOMB.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(UGEntityTypes.MINION_PROJECTILE.get(), ThrownItemRenderer::new);
        //
        event.registerEntityRenderer(UGEntityTypes.MINION.get(), MinionRender::new);
        event.registerEntityRenderer(UGEntityTypes.ROTLING.get(), RotlingRender::new);
        event.registerEntityRenderer(UGEntityTypes.ROTWALKER.get(), RotwalkerRender::new);
        event.registerEntityRenderer(UGEntityTypes.ROTBEAST.get(), RotbeastRender::new);
        event.registerEntityRenderer(UGEntityTypes.DWELLER.get(), DwellerRender::new);
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
        for(UGBoatEntity.Type boatType : UGBoatEntity.Type.values()) {
            event.registerLayerDefinition(UGBoatRenderer.boatLayer(boatType), BoatModel::createBodyModel);
        }
        event.registerLayerDefinition(MinionModel.LAYER_LOCATION, MinionModel::createBodyLayer);
        event.registerLayerDefinition(RotlingModel.LAYER_LOCATION, RotlingModel::createBodyLayer);
        event.registerLayerDefinition(RotwalkerModel.LAYER_LOCATION, RotwalkerModel::createBodyLayer);
        event.registerLayerDefinition(RotbeastModel.LAYER_LOCATION, RotbeastModel::createBodyLayer);
        event.registerLayerDefinition(DwellerModel.LAYER_LOCATION, () -> DwellerModel.createBodyLayer(0.0F));
        event.registerLayerDefinition(DwellerModel.SADDLE_LAYER_LOCATION, () -> DwellerModel.createBodyLayer(0.5F));
        event.registerLayerDefinition(BruteModel.LAYER_LOCATION, BruteModel::createBodyLayer);
        event.registerLayerDefinition(ScintlingModel.LAYER_LOCATION, ScintlingModel::createBodyLayer);
        event.registerLayerDefinition(GloomperModel.LAYER_LOCATION, GloomperModel::createBodyLayer);
        event.registerLayerDefinition(StonebornModel.LAYER_LOCATION, StonebornModel::createBodyLayer);
        event.registerLayerDefinition(NargoyleModel.LAYER_LOCATION, NargoyleModel::createBodyLayer);
        event.registerLayerDefinition(MuncherModel.LAYER_LOCATION, MuncherModel::createBodyLayer);
        event.registerLayerDefinition(SploogieModel.LAYER_LOCATION, SploogieModel::createBodyLayer);
        event.registerLayerDefinition(GwibModel.LAYER_LOCATION, GwibModel::createBodyLayer);
        event.registerLayerDefinition(MogModel.LAYER_LOCATION, MogModel::createBodyLayer);
        event.registerLayerDefinition(MasticatorModel.LAYER_LOCATION, MasticatorModel::createBodyLayer);
        event.registerLayerDefinition(ForgottenGuardianModel.LAYER_LOCATION, ForgottenGuardianModel::createBodyLayer);
    }

    public static void registerBlockColors() {
        BlockColors colors = Minecraft.getInstance().getBlockColors();

        colors.register((state, world, pos, tint) ->
                        world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : new Color(91, 117, 91).getRGB(),
                UGBlocks.DEEPTURF_BLOCK.get(),
                UGBlocks.DEEPTURF.get(),
                UGBlocks.SHIMMERWEED.get(),
                UGBlocks.TALL_DEEPTURF.get(),
                UGBlocks.TALL_SHIMMERWEED.get(),
                UGBlocks.GLOOMGOURD_STEM.get(),
                UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(),
                UGBlocks.POTTED_SHIMMERWEED.get()
        );

        colors.register((state, world, pos, tint) ->
                        new Color(54, 45, 66).getRGB(),
                UGBlocks.GLOOMGOURD_STEM.get(),
                UGBlocks.GLOOMGOURD_STEM_ATTACHED.get()
        );
    }

    public static void registerItemColors() {
        BlockColors bColors = Minecraft.getInstance().getBlockColors();
        ItemColors iColors = Minecraft.getInstance().getItemColors();

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

    @SubscribeEvent
    public static void renderVirulentFogColor(EntityViewRenderEvent.FogColors event) {
        Camera info = Minecraft.getInstance().gameRenderer.getMainCamera();
        FluidState fluidState = info.getBlockAtCamera().getFluidState();

        if(fluidState.getType() == UGFluids.VIRULENT_MIX_FLOWING.get() || fluidState.getType() == UGFluids.VIRULENT_MIX_SOURCE.get()) {
            event.setRed(57 / 255F);
            event.setGreen(25 / 255F);
            event.setBlue(80 / 255F);
        }
    }

    @SubscribeEvent
    public static void renderVirulentFogDensity(EntityViewRenderEvent.FogDensity event) {
        Camera info = Minecraft.getInstance().gameRenderer.getMainCamera();
        FluidState fluidState = info.getBlockAtCamera().getFluidState();

        if(fluidState.getType() == UGFluids.VIRULENT_MIX_FLOWING.get() || fluidState.getType() == UGFluids.VIRULENT_MIX_SOURCE.get()) {
            event.setDensity(1.5F);
            event.setCanceled(true);
        }
    }
}