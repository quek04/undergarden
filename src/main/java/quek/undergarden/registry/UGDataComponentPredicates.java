package quek.undergarden.registry;

import net.minecraft.core.component.predicates.DataComponentPredicate;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.component.predicate.InfectionConsumeEffectPredicate;

public class UGDataComponentPredicates {

	public static final DeferredRegister<DataComponentPredicate.Type<?>> COMPONENT_PREDICATES = DeferredRegister.create(Registries.DATA_COMPONENT_PREDICATE_TYPE, Undergarden.MODID);

	public static final DeferredHolder<DataComponentPredicate.Type<?>, DataComponentPredicate.Type<InfectionConsumeEffectPredicate>> CONSUMABLE = COMPONENT_PREDICATES.register("infection_consumable", () -> new DataComponentPredicate.ConcreteType<>(InfectionConsumeEffectPredicate.CODEC));
}
