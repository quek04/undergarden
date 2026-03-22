package quek.undergarden.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.item.consumeeffects.ModifyUthericInfectionConsumeEffect;

public class UGConsumeEffects {

	public static final DeferredRegister<ConsumeEffect.Type<?>> CONSUME_EFFECTS = DeferredRegister.create(BuiltInRegistries.CONSUME_EFFECT_TYPE, Undergarden.MODID);

	public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<ModifyUthericInfectionConsumeEffect>> UTHERIC_INFECTION = CONSUME_EFFECTS.register("utheric_infection", () -> new ConsumeEffect.Type<>(ModifyUthericInfectionConsumeEffect.CODEC, ModifyUthericInfectionConsumeEffect.STREAM_CODEC));
}
