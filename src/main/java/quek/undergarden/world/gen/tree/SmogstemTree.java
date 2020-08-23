package quek.undergarden.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SmogstemTree extends Tree {

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return UndergardenFeatures.undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UndergardenBlocks.smogstem_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UndergardenBlocks.smogstem_leaves.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .func_236700_a_().build());
    }
}
