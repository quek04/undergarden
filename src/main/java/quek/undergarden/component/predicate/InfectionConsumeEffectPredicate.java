package quek.undergarden.component.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.SingleComponentItemPredicate;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import quek.undergarden.item.consumeeffects.ModifyUthericInfectionConsumeEffect;

public record InfectionConsumeEffectPredicate(MinMaxBounds.Doubles amount) implements SingleComponentItemPredicate<Consumable> {

	public static final Codec<InfectionConsumeEffectPredicate> CODEC = RecordCodecBuilder.create(i -> i.group(
			MinMaxBounds.Doubles.CODEC.fieldOf("amount").forGetter(InfectionConsumeEffectPredicate::amount))
		.apply(i, InfectionConsumeEffectPredicate::new));

	@Override
	public DataComponentType<Consumable> componentType() {
		return DataComponents.CONSUMABLE;
	}

	@Override
	public boolean matches(Consumable value) {
		for (ConsumeEffect effect : value.onConsumeEffects()) {
			if (effect instanceof ModifyUthericInfectionConsumeEffect infectionEffect) {
				return this.amount().matches(infectionEffect.value());
			}
		}
		return false;
	}
}
