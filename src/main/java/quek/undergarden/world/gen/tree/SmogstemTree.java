package quek.undergarden.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SmogstemTree extends Tree {

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return UGFeatures.undergarden_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(UGBlocks.smogstem_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(UGBlocks.smogstem_leaves.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build());
    }
}
