package quek.undergarden.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SmogstemTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return UndergardenFeatures.SMOGSTEM_TREE.get().withConfiguration(UndergardenBiomeFeatures.SMOGSTEM_TREE_CONFIG);
        //return Feature.NORMAL_TREE.withConfiguration(UndergardenBiomeFeatures.SMOGSTEM_TREE_CONFIG);
    }
}
