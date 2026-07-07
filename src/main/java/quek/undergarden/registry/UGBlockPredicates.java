package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicateType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.feature.predicate.InDimensionPredicate;

public class UGBlockPredicates {

	public static final DeferredRegister<BlockPredicateType<?>> BLOCK_PREDICATES = DeferredRegister.create(Registries.BLOCK_PREDICATE_TYPE, Undergarden.MODID);

	public static final DeferredHolder<BlockPredicateType<?>, BlockPredicateType<InDimensionPredicate>> IN_DIMENSION = BLOCK_PREDICATES.register("in_dimension", () -> () -> InDimensionPredicate.CODEC);
}
