package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

public class UGTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, Undergarden.MODID);

    public static final RegistryObject<TrunkPlacerType<SmogstemTrunkPlacer>> SMOGSTEM_TRUNK_PLACER = TRUNK_PLACERS.register("smogstem_trunk_placer", () -> new TrunkPlacerType<>(SmogstemTrunkPlacer.CODEC));
}