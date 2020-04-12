package quek.undergarden.registry;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.gen.feature.SmogstemFeature;
import quek.undergarden.world.gen.feature.WigglewoodFeature;

public class UndergardenFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, UndergardenMod.MODID);

    public static final RegistryObject<Feature<TreeFeatureConfig>> SMOGSTEM_TREE = FEATURES.register(
            "smogstem_tree", () -> new SmogstemFeature(TreeFeatureConfig::func_227338_a_));

    public static final RegistryObject<Feature<TreeFeatureConfig>> WIGGLEWOOD_TREE = FEATURES.register(
            "wigglewood_tree", () -> new WigglewoodFeature(TreeFeatureConfig::func_227338_a_));

}
