package quek.undergarden.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import quek.undergarden.biome.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SmogstemTree extends Tree {

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return randomIn.nextInt(10) == 0 ? UndergardenFeatures.FANCY_SMOGSTEM_TREE.get().withConfiguration(UndergardenBiomeFeatures.SMOGSTEM_TREE_CONFIG) : UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(UndergardenBiomeFeatures.SMOGSTEM_TREE_CONFIG);
    }
}
