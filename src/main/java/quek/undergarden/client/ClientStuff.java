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
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;

import java.awt.*;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT)
public class ClientStuff {

    private static final Minecraft CLIENT = Minecraft.getInstance();

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.getCutout();
        RenderType mipped = RenderType.getCutoutMipped();
        RenderType translucent = RenderType.getTranslucent();

        render(UndergardenBlocks.deepturf_block, mipped);
        render(UndergardenBlocks.tall_deepturf, cutout);
        render(UndergardenBlocks.shimmerweed, cutout);
        render(UndergardenBlocks.smogstem_sapling, cutout);
        render(UndergardenBlocks.wigglewood_sapling, cutout);
        render(UndergardenBlocks.indigo_mushroom, cutout);
        render(UndergardenBlocks.veil_mushroom, cutout);
        render(UndergardenBlocks.ink_mushroom, cutout);
        render(UndergardenBlocks.blood_mushroom, cutout);
        render(UndergardenBlocks.underbean_bush, cutout);
        render(UndergardenBlocks.smogstem_torch, cutout);
        render(UndergardenBlocks.smogstem_wall_torch, cutout);
        render(UndergardenBlocks.ditchbulb_plant, cutout);
        render(UndergardenBlocks.double_deepturf, cutout);
        render(UndergardenBlocks.double_shimmerweed, cutout);
        render(UndergardenBlocks.cloggrum_bars, cutout);
        render(UndergardenBlocks.glowing_kelp, cutout);
        render(UndergardenBlocks.glowing_kelp_plant, cutout);
        render(UndergardenBlocks.glowing_sea_grass, cutout);
        render(UndergardenBlocks.undergarden_portal, translucent);
        render(UndergardenBlocks.goo, translucent);
        render(UndergardenBlocks.smogstem_door, cutout);
        render(UndergardenBlocks.wigglewood_door, cutout);
        render(UndergardenBlocks.smogstem_trapdoor, cutout);
        render(UndergardenBlocks.wigglewood_trapdoor, cutout);
        render(UndergardenBlocks.ashen_tall_deepturf, cutout);
        render(UndergardenBlocks.blisterberry_bush, cutout);
        render(UndergardenBlocks.gloomgourd_stem, cutout);
        render(UndergardenBlocks.gloomgourd_stem_attached, cutout);
        render(UndergardenBlocks.shard_torch, cutout);
        render(UndergardenBlocks.shard_wall_torch, cutout);
        render(UndergardenBlocks.droopvine_top, cutout);
        render(UndergardenBlocks.droopvine, cutout);
    }

    public static void registerEntityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.SLINGSHOT_AMMO.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.GOO_BALL.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.ROTTEN_BLISTERBERRY.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.BLISTERBOMB.get(), entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.ROTLING.get(), RotlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.ROTWALKER.get(), RotwalkerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.ROTBEAST.get(), RotbeastRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.DWELLER.get(), DwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.ROTDWELLER.get(), RotDwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.GWIBLING.get(), GwiblingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.BRUTE.get(), BruteRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.SCINTLING.get(), ScintlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.BLISTERBOMBER.get(), BlisterbomberRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.GLOOMPER.get(), GloomperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.STONEBORN.get(), StonebornRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.MASTICATOR.get(), MasticatorRender::new);
    }

    public static void registerBlockColors() {
        BlockColors colors = Minecraft.getInstance().getBlockColors();

        colors.register((state, world, pos, tint) ->
                        world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : new Color(91, 117, 91).getRGB(),
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.tall_deepturf.get(),
                UndergardenBlocks.shimmerweed.get(),
                UndergardenBlocks.double_deepturf.get(),
                UndergardenBlocks.double_shimmerweed.get(),
                UndergardenBlocks.gloomgourd_stem.get(),
                UndergardenBlocks.gloomgourd_stem_attached.get()
        );
    }

    public static void registerItemColors() {
        BlockColors bColors = Minecraft.getInstance().getBlockColors();
        ItemColors iColors = Minecraft.getInstance().getItemColors();

        iColors.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, 0),
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.tall_deepturf.get(),
                UndergardenBlocks.shimmerweed.get(),
                UndergardenBlocks.double_shimmerweed.get(),
                UndergardenBlocks.double_deepturf.get()
        );

        iColors.register((stack, tint) -> {
                    if(tint == 0) {
                        return new Color(91, 117, 91).getRGB();
                    }
                    return -1;
                },

                UndergardenBlocks.shimmerweed.get(),
                UndergardenBlocks.double_shimmerweed.get()
        );

    }

}
