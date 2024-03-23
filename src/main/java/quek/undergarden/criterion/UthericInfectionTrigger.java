package quek.undergarden.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import quek.undergarden.registry.UGCriteria;

import java.util.Optional;

public class UthericInfectionTrigger extends SimpleCriterionTrigger<UthericInfectionTrigger.TriggerInstance> {
	@Override
	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, int infectionLevel) {
		this.trigger(player, triggerInstance -> triggerInstance.matches(infectionLevel));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<MinMaxBounds.Ints> infectionLevel) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(TriggerInstance::player),
				ExtraCodecs.strictOptionalField(MinMaxBounds.Ints.CODEC, "infectionLevel").forGetter(TriggerInstance::infectionLevel))
			.apply(instance, TriggerInstance::new)
		);

		public static Criterion<?> isInfected() {
			return UGCriteria.UTHERIC_INFECTION.get().createCriterion(new UthericInfectionTrigger.TriggerInstance(Optional.empty(), Optional.of(MinMaxBounds.Ints.atLeast(1))));
		}

		public static Criterion<?> hasInfectionLevel(int infectionLevel) {
			return UGCriteria.UTHERIC_INFECTION.get().createCriterion(new UthericInfectionTrigger.TriggerInstance(Optional.empty(), Optional.of(MinMaxBounds.Ints.exactly(infectionLevel))));
		}

		public static Criterion<?> hasInfectionLevel(MinMaxBounds.Ints infectionLevel) {
			return UGCriteria.UTHERIC_INFECTION.get().createCriterion(new UthericInfectionTrigger.TriggerInstance(Optional.empty(), Optional.of(infectionLevel)));
		}

		public boolean matches(int infectionLevel) {
			return this.infectionLevel.isEmpty() || this.infectionLevel.get().matches(infectionLevel);
		}
	}
}