package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.carver.UGCaveWorldCarver;

public class UGCarvers {

    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Undergarden.MODID);

    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> UNDERGARDEN_CAVE = CARVERS.register(
            "undergarden_cave", () -> new UGCaveWorldCarver(CaveCarverConfiguration.CODEC));

    public static void registerConfiguredCarvers() {
        register("undergarden_cave", UNDERGARDEN_CAVE.get().configured(new CaveCarverConfiguration(0.5F, UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top()), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), false, ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));
    }

    private static <WC extends CarverConfiguration> void register(String name, ConfiguredWorldCarver<WC> carver) {
        Registry.register(BuiltinRegistries.CONFIGURED_CARVER, new ResourceLocation(Undergarden.MODID, name), carver);
    }
}