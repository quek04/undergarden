package quek.undergarden.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.UndergardenMod;


@Mod.EventBusSubscriber(modid = UndergardenMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergardenSoundEvents {

    public static final SoundEvent UNDERGARDEN_AMBIANCE = register("ambient.undergarden_ambiance");

    public static final SoundEvent UNDERGARDEN_MUSIC = register("music.undergarden_music");

    public static final SoundEvent UNDERGARDEN_PORTAL_AMBIENT = register("ambient.undergarden_portal_ambient");

    public static final SoundEvent DWELLER_LIVING = register("entity.dweller_living");
    public static final SoundEvent DWELLER_HURT = register("entity.dweller_hurt");
    public static final SoundEvent DWELLER_DEATH = register("entity.dweller_death");

    private static SoundEvent register(String name) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(UndergardenMod.MODID, name));
        sound.setRegistryName(new ResourceLocation(UndergardenMod.MODID, name));
        return sound;
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                UNDERGARDEN_AMBIANCE,
                UNDERGARDEN_MUSIC,
                UNDERGARDEN_PORTAL_AMBIENT,
                DWELLER_LIVING,
                DWELLER_HURT,
                DWELLER_DEATH
        );
    }

}
