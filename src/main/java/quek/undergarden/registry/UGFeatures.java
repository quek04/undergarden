package quek.undergarden.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.feature.*;

public class UGFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Undergarden.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GLITTERKELP = FEATURES.register(
            "glitterkelp", () -> new GlitterkelpFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SMOG_VENT = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DROOPVINE = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ICE_PILLAR = FEATURES.register(
            "ice_pillar", () -> new IcePillarFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<DeltaFeatureConfiguration>> DELTA = FEATURES.register(
            "delta", () -> new UGDeltaFeature(DeltaFeatureConfiguration.CODEC));
}