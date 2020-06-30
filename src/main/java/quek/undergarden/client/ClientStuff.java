package quek.undergarden.client;

import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.audio.OthersideAmbienceSound;
import quek.undergarden.client.audio.UndergardenAmbienceSound;
import quek.undergarden.client.render.entity.*;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSoundEvents;
import quek.undergarden.world.OthersideDimension;
import quek.undergarden.world.UndergardenDimension;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = UndergardenMod.MODID, value = Dist.CLIENT)
public class ClientStuff {

    private static final Minecraft CLIENT = Minecraft.getInstance();

    private static ISound playingMusic;
    private static final ISound UNDERGARDEN_AMBIENCE = new UndergardenAmbienceSound();
    private static final ISound OTHERSIDE_AMBIENCE = new OthersideAmbienceSound();

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
        //render(UndergardenBlocks.droopweed, cutout);
    }

    public static void registerEntityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.slingshot_ammo, entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.goo_ball, entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotten_blisterberry, entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.blisterbomb, entity -> new SpriteRenderer<>(entity, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotwalker, RotwalkerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotbeast, RotbeastRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.dweller, DwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotdweller, RotDwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.gwibling, GwiblingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.skiz_swarmer, SkizSwarmerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.brute, BruteRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.scintling, ScintlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.blisterbomber, BlisterbomberRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.gloomper, GloomperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.masticator, MasticatorRender::new);
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
                //UndergardenBlocks.shimmerweed.get(),
                //UndergardenBlocks.double_shimmerweed.get(),
                UndergardenBlocks.double_deepturf.get()
        );
    }

    /* TODO: MAKE COLORS WORK WITH THIS, APPARENTLY WHAT IS ABOVE IS THE INCORRECT WAY. BUT WHAT'S BELOW DOESNT DO JACK SHIT?
    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        colors.register((state, light, pos, tint) ->
                light != null && pos != null ? BiomeColors.getGrassColor(light, state.get(ShearableDoublePlantBlock.PLANT_HALF) == DoubleBlockHalf.UPPER ? pos.down() : pos) : -1,
                UndergardenBlocks.double_deepturf.get(),
                UndergardenBlocks.double_shimmerweed.get()
        );
        colors.register((state, light, pos, tint) ->
                light != null && pos != null ? BiomeColors.getGrassColor(light, pos) : new Color(91, 117, 91).getRGB(),
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.tall_deepturf.get(),
                UndergardenBlocks.shimmerweed.get()
        );
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();
        itemColors.register((stack, tint) -> new Color(91, 117, 91).getRGB(),
                UndergardenBlocks.double_deepturf.get(),
                UndergardenBlocks.double_shimmerweed.get()
        );
        itemColors.register((stack, tint) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(blockstate, null, null, tint);
        },
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.tall_deepturf.get(),
                UndergardenBlocks.shimmerweed.get()
        );
    }
    */

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (!CLIENT.isGamePaused()) {
            ClientPlayerEntity player = CLIENT.player;
            if (player == null) {
                return;
            }

            if (event.phase == TickEvent.Phase.END) {
                if (playingMusic != null && !CLIENT.getSoundHandler().isPlaying(playingMusic)) {
                    playingMusic = null;
                }
            }

            if(UndergardenDimension.isTheUndergarden(player.world)) {
                doAmbience(UNDERGARDEN_AMBIENCE);
            }
            if(OthersideDimension.isTheOtherside(player.world)) {
                doAmbience(OTHERSIDE_AMBIENCE);
            }
        }
    }

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        if (!isMusicSound()) {
            return;
        }

        if (CLIENT.player != null && UndergardenDimension.isTheUndergarden(CLIENT.player.world)) {
            SoundEvent sound = UndergardenSoundEvents.UNDERGARDEN_MUSIC;
            if (sound == null || playingMusic != null) {
                event.setResultSound(null);
                return;
            }

            playingMusic = SimpleSound.music(sound);

            event.setResultSound(playingMusic);
        }
    }

    private static boolean isMusicSound() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return Arrays.stream(stackTrace).anyMatch(e -> e.getClassName().equals(MusicTicker.class.getName()));
    }

    private static void doAmbience(ISound sound) {
        SoundHandler soundHandler = CLIENT.getSoundHandler();
        if (!soundHandler.isPlaying(sound)) {
            try {
                soundHandler.stop(sound);
                soundHandler.play(sound);
            } catch (IllegalArgumentException ignored) {
            }
        }
    }
}
