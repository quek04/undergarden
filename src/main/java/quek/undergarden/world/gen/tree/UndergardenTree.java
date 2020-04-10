package quek.undergarden.world.gen.tree;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import quek.undergarden.world.gen.config.UndergardenTreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class UndergardenTree extends Tree {

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return null;
    }

    public abstract ConfiguredFeature<UndergardenTreeFeatureConfig, ?> createTreeFeature(Random rand);

    @Override
    public boolean func_225545_a_(IWorld iWorld, ChunkGenerator<?> chunkGen, BlockPos blockPos, BlockState p_225545_4_, Random rand) {
        ConfiguredFeature<UndergardenTreeFeatureConfig, ?> configuredfeature = this.createTreeFeature(rand);
        if (configuredfeature == null) {
            return false;
        } else {
            iWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 4);
            configuredfeature.config.forcePlacement();
            if (configuredfeature.place(iWorld, chunkGen, rand, blockPos)) {
                return true;
            } else {
                iWorld.setBlockState(blockPos, p_225545_4_, 4);
                return false;
            }
        }
    }
}
