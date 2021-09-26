package quek.undergarden.world.gen.tree;

import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import quek.undergarden.registry.UGFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class GrongleTree extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    public ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean largeHive) {
        return UGFeatures.ConfiguredFeatures.GRONGLE_TREE_SMALL;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredMegaFeature(Random rand) {
        return UGFeatures.ConfiguredFeatures.GRONGLE_TREE;
    }
}