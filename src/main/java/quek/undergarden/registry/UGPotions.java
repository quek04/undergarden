package quek.undergarden.registry;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

public class UGPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Undergarden.MODID);

    public static final RegistryObject<Potion> BRITTLENESS = POTIONS.register("brittleness", () -> new Potion("brittleness", new EffectInstance(UGEffects.BRITTLENESS.get(), 900)));
    public static final RegistryObject<Potion> LONG_BRITTLENESS = POTIONS.register("long_brittleness", () -> new Potion("brittleness", new EffectInstance(UGEffects.BRITTLENESS.get(), 1800)));
    public static final RegistryObject<Potion> STRONG_BRITTLENESS = POTIONS.register("strong_brittleness", () -> new Potion("brittleness", new EffectInstance(UGEffects.BRITTLENESS.get(), 450, 1)));

    public static final RegistryObject<Potion> FEATHERWEIGHT = POTIONS.register("featherweight", () -> new Potion("featherweight", new EffectInstance(UGEffects.FEATHERWEIGHT.get(), 900)));
    public static final RegistryObject<Potion> LONG_FEATHERWEIGHT = POTIONS.register("long_featherweight", () -> new Potion("featherweight", new EffectInstance(UGEffects.FEATHERWEIGHT.get(), 1800)));
    public static final RegistryObject<Potion> STRONG_FEATHERWEIGHT = POTIONS.register("strong_featherweight", () -> new Potion("featherweight", new EffectInstance(UGEffects.FEATHERWEIGHT.get(), 450, 1)));

    public static final RegistryObject<Potion> VIRULENT_RESISTANCE = POTIONS.register("virulent_resistance", () -> new Potion("virulent_resistance", new EffectInstance(UGEffects.VIRULENT_RESISTANCE.get(), 3600)));
    public static final RegistryObject<Potion> LONG_VIRULENT_RESISTANCE = POTIONS.register("long_virulent_resistance", () -> new Potion("virulent_resistance", new EffectInstance(UGEffects.VIRULENT_RESISTANCE.get(), 9600)));

    public static final RegistryObject<Potion> GLOWING = POTIONS.register("glowing", () -> new Potion("glowing", new EffectInstance(Effects.GLOWING, 3600)));
    public static final RegistryObject<Potion> LONG_GLOWING = POTIONS.register("long_glowing", () -> new Potion("glowing", new EffectInstance(Effects.GLOWING, 9600)));
}