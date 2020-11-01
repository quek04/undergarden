package quek.undergarden.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import quek.undergarden.registry.UGBlocks;

public class UGDoublePlantBlock extends DoublePlantBlock {

    public UGDoublePlantBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == UGBlocks.deepturf_block.get() || block == UGBlocks.deepsoil.get();
    }
}
