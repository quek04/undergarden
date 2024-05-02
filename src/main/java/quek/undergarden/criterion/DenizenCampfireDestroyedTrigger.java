package quek.undergarden.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGCriteria;

import java.util.Optional;

public class DenizenCampfireDestroyedTrigger extends SimpleCriterionTrigger<DenizenCampfireDestroyedTrigger.TriggerInstance> {
	@Override
	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, BlockState state) {
		this.trigger(player, triggerInstance -> triggerInstance.matches(state));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<Holder<Block>> block) implements SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
				BuiltInRegistries.BLOCK.holderByNameCodec().optionalFieldOf("block").forGetter(TriggerInstance::block))
			.apply(instance, TriggerInstance::new)
		);

		public static Criterion<TriggerInstance> destroyedCampfire(Block block) {
			return UGCriteria.DENIZEN_CAMPFIRE_DESTROYED.get().createCriterion(new DenizenCampfireDestroyedTrigger.TriggerInstance(Optional.empty(), Optional.of(block.builtInRegistryHolder())));
		}

		public boolean matches(BlockState state) {
            return this.block.isEmpty() || state.is(this.block.get());
        }
	}
}