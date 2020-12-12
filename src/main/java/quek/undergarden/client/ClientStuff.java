package quek.undergarden.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGFluids;

import java.awt.*;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT)
public class ClientStuff {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.getCutout();
        RenderType mipped = RenderType.getCutoutMipped();
        RenderType translucent = RenderType.getTranslucent();

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
        render(UGBlocks.UNDERGARDEN_PORTAL, translucent);
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
        render(UGBlocks.GRONGLET, cutout);
        render(UGBlocks.GRONGLE_DOOR, cutout);
        render(UGBlocks.GRONGLE_TRAPDOOR, cutout);
        render(UGBlocks.VIRULENT_MIX, translucent);
        render(UGBlocks.SEEPING_INK, cutout);
        render(UGBlocks.MUSHROOM_VEIL, cutout);
        render(UGBlocks.MUSHROOM_VEIL_TOP, cutout);

        RenderTypeLookup.setRenderLayer(UGFluids.VIRULENT_MIX_SOURCE.get(), translucent);
        RenderTypeLookup.setRenderLayer(UGFluids.VIRULENT_MIX_FLOWING.get(), translucent);
    }

    public static void registerEntityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.SLINGSHOT_AMMO.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.GOO_BALL.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.BLISTERBOMB.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.BOAT.get(), UGBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.ROTLING.get(), RotlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.ROTWALKER.get(), RotwalkerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.ROTBEAST.get(), RotbeastRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.DWELLER.get(), DwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.ROTDWELLER.get(), RotDwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.GWIBLING.get(), GwiblingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.BRUTE.get(), BruteRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.SCINTLING.get(), ScintlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.GLOOMPER.get(), GloomperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.STONEBORN.get(), StonebornRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.MASTICATOR.get(), MasticatorRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.NARGOYLE.get(), NargoyleRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), ForgottenGuardianRender::new);
    }

    public static void registerBlockColors() {
        BlockColors colors = Minecraft.getInstance().getBlockColors();

        colors.register((state, world, pos, tint) ->
                        world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : new Color(91, 117, 91).getRGB(),
                UGBlocks.DEEPTURF_BLOCK.get(),
                UGBlocks.DEEPTURF.get(),
                UGBlocks.SHIMMERWEED.get(),
                UGBlocks.TALL_DEEPTURF.get(),
                UGBlocks.TALL_SHIMMERWEED.get(),
                UGBlocks.GLOOMGOURD_STEM.get(),
                UGBlocks.GLOOMGOURD_STEM_ATTACHED.get()
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

        iColors.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, 0),
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
}
