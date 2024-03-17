package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.trunkplacer.SingleForkingTrunkPlacer;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

public class UGTrunkPlacerTypes {

	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Undergarden.MODID);

	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<SmogstemTrunkPlacer>> SMOGSTEM_TRUNK_PLACER = TRUNK_PLACERS.register("smogstem_trunk_placer", () -> new TrunkPlacerType<>(SmogstemTrunkPlacer.CODEC));
	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<SingleForkingTrunkPlacer>> SINGLE_FORKING_TRUNK_PLACER = TRUNK_PLACERS.register("single_forking_trunk_placer", () -> new TrunkPlacerType<>(SingleForkingTrunkPlacer.CODEC));
}