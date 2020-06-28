package quek.undergarden.registry;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.gen.feature.*;

public class UndergardenFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UndergardenMod.MODID);

    //tree
    public static final RegistryObject<Feature<TreeFeatureConfig>> SMOGSTEM_TREE = FEATURES.register(
            "smogstem_tree", () -> new SmogstemFeature(TreeFeatureConfig::func_227338_a_));
    public static final RegistryObject<Feature<TreeFeatureConfig>> FANCY_SMOGSTEM_TREE = FEATURES.register(
            "fancy_smogstem", () -> new FancySmogstemFeature(TreeFeatureConfig::func_227338_a_));
    public static final RegistryObject<Feature<TreeFeatureConfig>> WIGGLEWOOD_TREE = FEATURES.register(
            "wigglewood_tree", () -> new WigglewoodFeature(TreeFeatureConfig::func_227338_a_));

    //other
    public static final RegistryObject<Feature<NoFeatureConfig>> GLOWING_KELP = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> SMOG_VENT = FEATURES.register(
            "smog_vent", () -> new SmogVentFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> ENIGMATIC_STATUE = FEATURES.register(
            "enigmatic_statue", () -> new EnigmaticStatueFeature(NoFeatureConfig::deserialize));

    //structures


}
