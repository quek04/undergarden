package quek.undergarden.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import quek.undergarden.registry.UndergardenBiomeFeatures;
import quek.undergarden.registry.UndergardenFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class WigglewoodTree extends Tree {

    @Nullable
    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return UndergardenFeatures.WIGGLEWOOD_TREE.get().withConfiguration(UndergardenBiomeFeatures.WIGGLEWOOD_TREE_CONFIG);
        //return Feature.ACACIA_TREE.withConfiguration(UndergardenBiomeFeatures.WIGGLEWOOD_TREE_CONFIG);
    }
}
