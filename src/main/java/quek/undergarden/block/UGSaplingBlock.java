package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class UGSaplingBlock extends SaplingBlock {

    public UGSaplingBlock(Tree tree) {
        super(tree, Properties.of(Material.PLANT)
                .strength(0F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .noCollission()
        );
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.randomTick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        this.advanceTree(worldIn, pos, state, rand);
    }
}