package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;

public class UGPotions {

	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Undergarden.MODID);

	public static final DeferredHolder<Potion, Potion> BRITTLENESS = POTIONS.register("brittleness", () -> new Potion("brittleness", new MobEffectInstance(UGEffects.BRITTLENESS, 900)));
	public static final DeferredHolder<Potion, Potion> LONG_BRITTLENESS = POTIONS.register("long_brittleness", () -> new Potion("brittleness", new MobEffectInstance(UGEffects.BRITTLENESS, 1800)));
	public static final DeferredHolder<Potion, Potion> STRONG_BRITTLENESS = POTIONS.register("strong_brittleness", () -> new Potion("brittleness", new MobEffectInstance(UGEffects.BRITTLENESS, 450, 1)));

	public static final DeferredHolder<Potion, Potion> FEATHERWEIGHT = POTIONS.register("featherweight", () -> new Potion("featherweight", new MobEffectInstance(UGEffects.FEATHERWEIGHT, 900)));
	public static final DeferredHolder<Potion, Potion> LONG_FEATHERWEIGHT = POTIONS.register("long_featherweight", () -> new Potion("featherweight", new MobEffectInstance(UGEffects.FEATHERWEIGHT, 1800)));
	public static final DeferredHolder<Potion, Potion> STRONG_FEATHERWEIGHT = POTIONS.register("strong_featherweight", () -> new Potion("featherweight", new MobEffectInstance(UGEffects.FEATHERWEIGHT, 450, 1)));

	public static final DeferredHolder<Potion, Potion> VIRULENT_RESISTANCE = POTIONS.register("virulent_resistance", () -> new Potion("virulent_resistance", new MobEffectInstance(UGEffects.VIRULENT_RESISTANCE, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_VIRULENT_RESISTANCE = POTIONS.register("long_virulent_resistance", () -> new Potion("virulent_resistance", new MobEffectInstance(UGEffects.VIRULENT_RESISTANCE, 9600)));

	public static final DeferredHolder<Potion, Potion> GLOWING = POTIONS.register("glowing", () -> new Potion("glowing", new MobEffectInstance(MobEffects.GLOWING, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_GLOWING = POTIONS.register("long_glowing", () -> new Potion("glowing", new MobEffectInstance(MobEffects.GLOWING, 9600)));
}