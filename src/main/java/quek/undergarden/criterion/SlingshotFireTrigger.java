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
import net.minecraft.world.level.ItemLike;
import quek.undergarden.registry.UGCriteria;
import quek.undergarden.registry.UGItems;

import java.util.Optional;

public class SlingshotFireTrigger extends SimpleCriterionTrigger<SlingshotFireTrigger.TriggerInstance> {

	@Override
	public Codec<TriggerInstance> codec() {
		return SlingshotFireTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer shooter, ItemStack slingshot, ItemStack ammo) {
		this.trigger(shooter, instance -> instance.matches(slingshot, ammo));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> slingshot, Optional<ItemPredicate> ammo) implements SimpleInstance {
		public static final Codec<SlingshotFireTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
						ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(SlingshotFireTrigger.TriggerInstance::player),
						ExtraCodecs.strictOptionalField(ItemPredicate.CODEC, "slingshot").forGetter(SlingshotFireTrigger.TriggerInstance::slingshot),
						ExtraCodecs.strictOptionalField(ItemPredicate.CODEC, "ammo").forGetter(SlingshotFireTrigger.TriggerInstance::ammo))
				.apply(instance, SlingshotFireTrigger.TriggerInstance::new));

		public static Criterion<?> shotItem(ItemLike slingshot, ItemLike ammo) {
			return UGCriteria.SLINGSHOT_FIRE.get().createCriterion(new SlingshotFireTrigger.TriggerInstance(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(slingshot).build()), Optional.of(ItemPredicate.Builder.item().of(ammo).build())));
		}

		public static Criterion<?> shotItem(ItemLike ammo) {
			return shotItem(UGItems.SLINGSHOT.get(), ammo);
		}

		public boolean matches(ItemStack slingshot, ItemStack ammo) {
			if (this.slingshot.isPresent() && !this.slingshot.get().matches(slingshot)) return false;
			return this.ammo.isEmpty() || this.ammo.get().matches(ammo);
		}
	}
}
