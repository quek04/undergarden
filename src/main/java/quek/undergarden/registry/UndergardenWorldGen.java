package quek.undergarden.registry;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.gen.config.UndergardenTreeFeatureConfig;
import quek.undergarden.world.gen.tree.feature.SmogstemFeature;

public class UndergardenWorldGen {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, UndergardenMod.MODID);

    public static final RegistryObject<Feature<UndergardenTreeFeatureConfig>> SMOGSTEM_TREE = FEATURES.register(
            "smogstem_tree", () -> new SmogstemFeature<>(UndergardenTreeFeatureConfig::deserializeSmogstem));
}
