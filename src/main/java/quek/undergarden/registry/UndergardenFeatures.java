package quek.undergarden.registry;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.gen.feature.*;

public class UndergardenFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UndergardenMod.MODID);

    //tree
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SMOGSTEM_TREE = FEATURES.register(
            "smogstem_tree", () -> new UndergardenTreeFeature(BaseTreeFeatureConfig.field_236676_a_));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> WIGGLEWOOD_TREE = FEATURES.register(
            "wigglewood_tree", () -> new UndergardenTreeFeature(BaseTreeFeatureConfig.field_236676_a_));

    //other
    public static final RegistryObject<Feature<NoFeatureConfig>> GLOWING_KELP = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> SMOG_VENT = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> ENIGMATIC_STATUE = FEATURES.register(
            "enigmatic_statue", () -> new EnigmaticStatueFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> DROOPVINE = FEATURES.register(
            "droopvine", () -> new DroopvineFeature(NoFeatureConfig.field_236558_a_));

    //structures


}
