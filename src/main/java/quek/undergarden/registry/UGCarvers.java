package quek.undergarden.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.carver.UGCaveWorldCarver;

public class UGCarvers {

    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Undergarden.MODID);

    public static final RegistryObject<WorldCarver<ProbabilityFeatureConfiguration>> UNDERGARDEN_CAVE = CARVERS.register(
            "undergarden_cave", () -> new UGCaveWorldCarver(ProbabilityFeatureConfiguration.CODEC));

    public static void registerConfiguredCarvers() {
        register("undergarden_cave", UNDERGARDEN_CAVE.get().configured(new ProbabilityFeatureConfiguration(0.5F)));
    }

    private static <WC extends CarverConfiguration> void register(String name, ConfiguredWorldCarver<WC> carver) {
        Registry.register(BuiltinRegistries.CONFIGURED_CARVER, new ResourceLocation(Undergarden.MODID, name), carver);
    }
}