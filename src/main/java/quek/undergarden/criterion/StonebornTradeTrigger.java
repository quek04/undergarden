package quek.undergarden.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import quek.undergarden.entity.stoneborn.Stoneborn;
import quek.undergarden.registry.UGCriteria;

import java.util.Optional;

public class StonebornTradeTrigger extends SimpleCriterionTrigger<StonebornTradeTrigger.TriggerInstance> {

	@Override
	public Codec<TriggerInstance> codec() {
		return StonebornTradeTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, Stoneborn stoneborn, ItemStack stack) {
		LootContext lootcontext = EntityPredicate.createContext(player, stoneborn);
		this.trigger(player, instance -> instance.matches(lootcontext, stack));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ContextAwarePredicate> stoneborn, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(p_311449_ -> p_311449_.group(
						ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(TriggerInstance::player),
						ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "stoneborn").forGetter(TriggerInstance::stoneborn),
						ExtraCodecs.strictOptionalField(ItemPredicate.CODEC, "item").forGetter(TriggerInstance::item))
				.apply(p_311449_, TriggerInstance::new)
		);

		public static Criterion<?> tradeWithStoneborn() {
			return UGCriteria.STONEBORN_TRADE.get().createCriterion(new TriggerInstance(Optional.empty(), Optional.empty(), Optional.empty()));
		}

		public boolean matches(LootContext context, ItemStack stack) {
			if (this.stoneborn().isPresent() && !this.stoneborn().get().matches(context)) {
				return false;
			}
			return this.item().isEmpty() || this.item().get().matches(stack);
		}
	}
}