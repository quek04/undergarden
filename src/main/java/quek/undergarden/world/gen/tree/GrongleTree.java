package quek.undergarden.world.gen.tree;

import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import quek.undergarden.registry.UGConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class GrongleTree extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    public ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean largeHive) {
        return UGConfiguredFeatures.SMALL_GRONGLE_TREE;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredMegaFeature(Random rand) {
        return UGConfiguredFeatures.GRONGLE_TREE;
    }
}