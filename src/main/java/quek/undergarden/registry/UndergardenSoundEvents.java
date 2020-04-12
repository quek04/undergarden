package quek.undergarden.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.UndergardenMod;


@Mod.EventBusSubscriber(modid = UndergardenMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergardenSoundEvents {

    public static final SoundEvent UNDERGARDEN_MUSIC = makeSoundEvent("music.undergarden_music");

    public static final SoundEvent DWELLER_LIVING = makeSoundEvent("dweller_living");
    public static final SoundEvent DWELLER_HURT = makeSoundEvent("dweller_hurt");
    public static final SoundEvent DWELLER_DEATH = makeSoundEvent("dweller_death");

    private static SoundEvent makeSoundEvent(String name) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(UndergardenMod.MODID, name));
        sound.setRegistryName(new ResourceLocation(UndergardenMod.MODID, name));
        return sound;
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                UNDERGARDEN_MUSIC,
                DWELLER_LIVING,
                DWELLER_HURT,
                DWELLER_DEATH
        );
    }

}
