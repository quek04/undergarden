package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import quek.undergarden.registry.UGBlocks;

import net.minecraft.block.AbstractBlock.Properties;

public class UGBushBlock extends BushBlock {

    public UGBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(UGBlocks.DEEPTURF_BLOCK.get()) || state.is(UGBlocks.DEEPSOIL.get()) || state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get());
    }
}