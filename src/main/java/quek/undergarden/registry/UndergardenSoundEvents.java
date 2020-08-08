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
    public static final SoundEvent OTHERSIDE_AMBIANCE = register("ambient.otherside_ambiance");

    public static final SoundEvent UNDERGARDEN_MUSIC = register("music.undergarden_music");

    public static final SoundEvent UNDERGARDEN_PORTAL_AMBIENT = register("ambient.undergarden_portal_ambient");

    public static final SoundEvent UNDERGARDEN_PORTAL_ACTIVATE = register("item.undergarden_portal_activate");

    public static final SoundEvent BLISTERBOMB_THROW = register("item.blisterbomb");

    public static final SoundEvent DWELLER_LIVING = register("entity.dweller_living");
    public static final SoundEvent DWELLER_HURT = register("entity.dweller_hurt");
    public static final SoundEvent DWELLER_DEATH = register("entity.dweller_death");

    public static final SoundEvent ROTWALKER_LIVING = register("entity.rotwalker_living");
    public static final SoundEvent ROTWALKER_HURT = register("entity.rotwalker_hurt");
    public static final SoundEvent ROTWALKER_DEATH = register("entity.rotwalker_death");

    public static final SoundEvent ROTBEAST_LIVING = register("entity.rotbeast_living");
    public static final SoundEvent ROTBEAST_HURT = register("entity.rotbeast_hurt");
    public static final SoundEvent ROTBEAST_DEATH = register("entity.rotbeast_death");

    public static final SoundEvent BRUTE_LIVING = register("entity.brute_living");
    public static final SoundEvent BRUTE_HURT = register("entity.brute_hurt");
    public static final SoundEvent BRUTE_DEATH = register("entity.brute_death");
    public static final SoundEvent BRUTE_ANGRY = register("entity.brute_angry");

    public static final SoundEvent GLOOMPER_LIVING = register("entity.gloomper_living");
    public static final SoundEvent GLOOMPER_HURT = register("entity.gloomper_hurt");
    public static final SoundEvent GLOOMPER_DEATH = register("entity.gloomper_death");

    public static final SoundEvent STONEBORN_STEP = register("entity.stoneborn_step");
    public static final SoundEvent STONEBORN_SPEAKING = register("entity.stoneborn_speaking");
    public static final SoundEvent STONEBORN_PLEASED = register("entity.stoneborn_pleased");
    public static final SoundEvent STONEBORN_AWE = register("entity.stoneborn_awe");
    public static final SoundEvent STONEBORN_CHUCKLE = register("entity.stoneborn_chuckle");
    public static final SoundEvent STONEBORN_HURT = register("entity.stoneborn_hurt");
    public static final SoundEvent STONEBORN_ANGRY = register("entity.stoneborn_angry");
    public static final SoundEvent STONEBORN_GROWL = register("entity.stoneborn_growl");
    public static final SoundEvent STONEBORN_CONFUSED = register("entity.stoneborn_confused");
    public static final SoundEvent STONEBORN_CHANT = register("entity.stoneborn_chant");
    public static final SoundEvent STONEBORN_DEATH = register("entity.stoneborn_death");

    private static SoundEvent register(String name) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(UndergardenMod.MODID, name));
        sound.setRegistryName(new ResourceLocation(UndergardenMod.MODID, name));
        return sound;
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                UNDERGARDEN_AMBIANCE,
                OTHERSIDE_AMBIANCE,
                UNDERGARDEN_MUSIC,
                UNDERGARDEN_PORTAL_AMBIENT,
                UNDERGARDEN_PORTAL_ACTIVATE,
                DWELLER_LIVING,
                DWELLER_HURT,
                DWELLER_DEATH,
                ROTWALKER_LIVING,
                ROTWALKER_HURT,
                ROTWALKER_DEATH,
                ROTBEAST_LIVING,
                ROTBEAST_HURT,
                ROTBEAST_DEATH,
                BRUTE_LIVING,
                BRUTE_HURT,
                BRUTE_DEATH,
                BRUTE_ANGRY,
                GLOOMPER_LIVING,
                GLOOMPER_HURT,
                GLOOMPER_DEATH,
                STONEBORN_STEP,
                STONEBORN_SPEAKING,
                STONEBORN_PLEASED,
                STONEBORN_AWE,
                STONEBORN_CHUCKLE,
                STONEBORN_HURT,
                STONEBORN_ANGRY,
                STONEBORN_GROWL,
                STONEBORN_CONFUSED,
                STONEBORN_CHANT,
                STONEBORN_DEATH
        );
    }

}
