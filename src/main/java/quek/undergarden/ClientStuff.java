package quek.undergarden;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.client.render.*;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenSoundEvents;
import quek.undergarden.world.UndergardenDimension;

import java.util.Arrays;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = UndergardenMod.MODID, value = Dist.CLIENT)
public class ClientStuff {

    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static ISound playingMusic;

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {

        RenderType cutout = RenderType.getCutout();
        RenderType translucent = RenderType.getTranslucent();

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
    }

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.slingshot_ammo, entity -> new SpriteRenderer<>(entity, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.goo_ball, entity -> new SpriteRenderer<>(entity, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotwalker, RotwalkerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotbeast, RotbeastRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.dweller, DwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotdweller, RotDwellerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.gwibling, GwiblingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.skiz_swarmer, SkizSwarmerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.brute, BruteRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.scintling, ScintlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.masticator, MasticatorRender::new);
    }

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

}
