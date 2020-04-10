package quek.undergarden.world.gen.tree;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenWorldGen;
import quek.undergarden.world.gen.config.UndergardenTreeFeatureConfig;

import java.util.Random;

public class SmogstemTree extends UndergardenTree {

    @Override
    public ConfiguredFeature<UndergardenTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return UndergardenWorldGen.SMOGSTEM_TREE.get().withConfiguration(UndergardenBiomeFeatures.SMOGSTEM_TREE_CONFIG);
    }
}
