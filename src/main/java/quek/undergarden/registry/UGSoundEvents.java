package quek.undergarden.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;

public class UGSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, UGMod.MODID);

    public static final RegistryObject<SoundEvent> UNDERGARDEN_AMBIENCE = register("ambient.undergarden_ambience");
    public static final RegistryObject<SoundEvent> OTHERSIDE_AMBIENCE = register("ambient.otherside_ambience");
    public static final RegistryObject<SoundEvent> SPIRES_AMBIENCE = register("ambient.spires_ambience");

    public static final RegistryObject<SoundEvent> UNDERGARDEN_MUSIC = register("music.undergarden_music");
    public static final RegistryObject<SoundEvent> MAMMOTH_DISC = register("music.disc.mammoth");
    public static final RegistryObject<SoundEvent> LIMAX_MAXIMUS_DISC = register("music.disc.limax_maximus");

    public static final RegistryObject<SoundEvent> UNDERGARDEN_PORTAL_AMBIENT = register("ambient.undergarden_portal_ambient");
    public static final RegistryObject<SoundEvent> UNDERGARDEN_PORTAL_ACTIVATE = register("item.undergarden_portal_activate");

    public static final RegistryObject<SoundEvent> BLISTERBOMB_THROW = register("item.blisterbomb");

    public static final RegistryObject<SoundEvent> DWELLER_LIVING = register("entity.dweller_living");
    public static final RegistryObject<SoundEvent> DWELLER_HURT = register("entity.dweller_hurt");
    public static final RegistryObject<SoundEvent> DWELLER_DEATH = register("entity.dweller_death");

    public static final RegistryObject<SoundEvent> ROTWALKER_LIVING = register("entity.rotwalker_living");
    public static final RegistryObject<SoundEvent> ROTWALKER_HURT = register("entity.rotwalker_hurt");
    public static final RegistryObject<SoundEvent> ROTWALKER_DEATH = register("entity.rotwalker_death");

    public static final RegistryObject<SoundEvent> ROTBEAST_LIVING = register("entity.rotbeast_living");
    public static final RegistryObject<SoundEvent> ROTBEAST_HURT = register("entity.rotbeast_hurt");
    public static final RegistryObject<SoundEvent> ROTBEAST_DEATH = register("entity.rotbeast_death");

    public static final RegistryObject<SoundEvent> BRUTE_LIVING = register("entity.brute_living");
    public static final RegistryObject<SoundEvent> BRUTE_HURT = register("entity.brute_hurt");
    public static final RegistryObject<SoundEvent> BRUTE_DEATH = register("entity.brute_death");
    public static final RegistryObject<SoundEvent> BRUTE_ANGRY = register("entity.brute_angry");

    public static final RegistryObject<SoundEvent> GLOOMPER_LIVING = register("entity.gloomper_living");
    public static final RegistryObject<SoundEvent> GLOOMPER_HURT = register("entity.gloomper_hurt");
    public static final RegistryObject<SoundEvent> GLOOMPER_DEATH = register("entity.gloomper_death");

    public static final RegistryObject<SoundEvent> STONEBORN_STEP = register("entity.stoneborn_step");
    public static final RegistryObject<SoundEvent> STONEBORN_SPEAKING = register("entity.stoneborn_speaking");
    public static final RegistryObject<SoundEvent> STONEBORN_PLEASED = register("entity.stoneborn_pleased");
    public static final RegistryObject<SoundEvent> STONEBORN_AWE = register("entity.stoneborn_awe");
    public static final RegistryObject<SoundEvent> STONEBORN_CHUCKLE = register("entity.stoneborn_chuckle");
    public static final RegistryObject<SoundEvent> STONEBORN_HURT = register("entity.stoneborn_hurt");
    public static final RegistryObject<SoundEvent> STONEBORN_ANGRY = register("entity.stoneborn_angry");
    public static final RegistryObject<SoundEvent> STONEBORN_GROWL = register("entity.stoneborn_growl");
    public static final RegistryObject<SoundEvent> STONEBORN_CONFUSED = register("entity.stoneborn_confused");
    public static final RegistryObject<SoundEvent> STONEBORN_CHANT = register("entity.stoneborn_chant");
    public static final RegistryObject<SoundEvent> STONEBORN_DEATH = register("entity.stoneborn_death");

    public static final RegistryObject<SoundEvent> ROTLING_LIVING = register("entity.rotling_living");
    public static final RegistryObject<SoundEvent> ROTLING_HURT = register("entity.rotling_hurt");
    public static final RegistryObject<SoundEvent> ROTLING_DEATH = register("entity.rotling_death");

    public static final RegistryObject<SoundEvent> FORGOTTEN_GUARDIAN_LIVING = register("entity.forgotten_guardian_living");
    public static final RegistryObject<SoundEvent> FORGOTTEN_GUARDIAN_HURT = register("entity.forgotten_guardian_hurt");
    public static final RegistryObject<SoundEvent> FORGOTTEN_GUARDIAN_DEATH = register("entity.forgotten_guardian_death");
    public static final RegistryObject<SoundEvent> FORGOTTEN_GUARDIAN_ATTACK = register("entity.forgotten_guardian_attack");
    public static final RegistryObject<SoundEvent> FORGOTTEN_GUARDIAN_DEFLECT = register("entity.forgotten_guardian_deflect");
    public static final RegistryObject<SoundEvent> FORGOTTEN_GUARDIAN_STEP = register("entity.forgotten_guardian_step");

    public static final RegistryObject<SoundEvent> NARGOYLE_HURT = register("entity.nargoyle_hurt");
    public static final RegistryObject<SoundEvent> NARGOYLE_DEATH = register("entity.nargoyle_death");
    public static final RegistryObject<SoundEvent> NARGOYLE_ATTACK = register("entity.nargoyle_attack");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(UGMod.MODID, name)));
    }
}