package quek.undergarden.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import quek.undergarden.registry.UGBlocks;

public class UGDoublePlantBlock extends DoublePlantBlock {

    public UGDoublePlantBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(UGBlocks.DEEPTURF_BLOCK.get()) || state.isIn(UGBlocks.DEEPSOIL.get()) || state.isIn(UGBlocks.FROZEN_DEEPTURF_BLOCK.get());
    }
}
