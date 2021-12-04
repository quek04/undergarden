package quek.undergarden.world.gen.tree;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import quek.undergarden.registry.UGConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SmogstemTree extends AbstractTreeGrower {

    @Nullable
    @Override
    public ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
        return UGConfiguredFeatures.SMOGSTEM_TREE;
    }
}