package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.feature.*;

public class UGFeatures {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Undergarden.MODID);

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GLITTERKELP = FEATURES.register(
			"glitterkelp", () -> new GlitterkelpFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SMOG_VENT = FEATURES.register(
			"smog_vent", () -> new SmogVentFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DROOPVINE = FEATURES.register(
			"droopvine", () -> new DroopvineFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ICE_PILLAR = FEATURES.register(
			"ice_pillar", () -> new IcePillarFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<DeltaFeatureConfiguration>> DELTA = FEATURES.register(
			"delta", () -> new UGDeltaFeature(DeltaFeatureConfiguration.CODEC));
}