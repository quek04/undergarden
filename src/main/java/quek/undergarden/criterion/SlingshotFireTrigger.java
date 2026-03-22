package quek.undergarden.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
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

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> slingshot,
								  Optional<ItemPredicate> ammo) implements SimpleInstance {
		public static final Codec<SlingshotFireTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
						EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SlingshotFireTrigger.TriggerInstance::player),
						ItemPredicate.CODEC.optionalFieldOf("slingshot").forGetter(SlingshotFireTrigger.TriggerInstance::slingshot),
						ItemPredicate.CODEC.optionalFieldOf("ammo").forGetter(SlingshotFireTrigger.TriggerInstance::ammo))
				.apply(instance, SlingshotFireTrigger.TriggerInstance::new));

		public static Criterion<?> shotItem(HolderGetter<Item> items, ItemLike slingshot, ItemLike ammo) {
			return UGCriteria.SLINGSHOT_FIRE.get().createCriterion(new SlingshotFireTrigger.TriggerInstance(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(items, slingshot).build()), Optional.of(ItemPredicate.Builder.item().of(items, ammo).build())));
		}

		public static Criterion<?> shotItem(HolderGetter<Item> items, ItemLike ammo) {
			return shotItem(items, UGItems.SLINGSHOT.get(), ammo);
		}

		public boolean matches(ItemStack slingshot, ItemStack ammo) {
			if (this.slingshot.isPresent() && !this.slingshot.get().test(slingshot)) return false;
			return this.ammo.isEmpty() || this.ammo.get().test(ammo);
		}
	}
}
