package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.foliageplacer.ConeFoliagePlacer;

public class UGFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registry.FOLIAGE_PLACER_TYPE_REGISTRY, Undergarden.MODID);

    public static final RegistryObject<FoliagePlacerType<ConeFoliagePlacer>> CONE = FOLIAGE_PLACERS.register("cone", () -> new FoliagePlacerType<>(ConeFoliagePlacer.CODEC));
}
