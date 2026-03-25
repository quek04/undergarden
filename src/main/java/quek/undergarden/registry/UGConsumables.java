package quek.undergarden.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import quek.undergarden.item.consumeeffects.ModifyUthericInfectionConsumeEffect;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UGConsumables {

	public static final Consumable UNDERBEANS = Consumable.builder().consumeSeconds(0.8F).build();
	public static final Consumable GRONGLET = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WITHER, 1200, 9))).build();
	public static final Consumable UTHERIC_GRONGLET = Consumable.builder().onConsume(new ModifyUthericInfectionConsumeEffect(20.0F, false)).build();
	public static final Consumable ROGDORIC_GRONGLET = Consumable.builder().onConsume(new ModifyUthericInfectionConsumeEffect(0.0F, false)).build();
	public static final Consumable ROGDORIUM_NUGGET = Consumable.builder().onConsume(new ModifyUthericInfectionConsumeEffect(-2.0F, true)).build();
	public static final Consumable ROGDORIUM = Consumable.builder().onConsume(new ModifyUthericInfectionConsumeEffect(-20.0F, true)).build();

	public static final Function<MobEffectInstance, Consumable> MOB_EFFECT_CONSUMABLE = (effect) -> Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(effect)).build();
	public static final BiFunction<MobEffectInstance, MobEffectInstance, Consumable> STEW_CONSUMABLE = (effect, secondary) -> Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(effect, secondary))).build();
}
