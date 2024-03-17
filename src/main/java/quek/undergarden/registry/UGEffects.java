package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.effect.*;

public class UGEffects {

	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Undergarden.MODID);

	public static final DeferredHolder<MobEffect, MobEffect> GOOEY = EFFECTS.register("gooey", GooeyEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> BRITTLENESS = EFFECTS.register("brittleness", BrittlenessEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> FEATHERWEIGHT = EFFECTS.register("featherweight", FeatherweightEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> VIRULENT_RESISTANCE = EFFECTS.register("virulent_resistance", VirulentResistanceEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> VIRULENCE = EFFECTS.register("virulence", VirulenceEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> CHILLY = EFFECTS.register("chilly", ChillyEffect::new);

}