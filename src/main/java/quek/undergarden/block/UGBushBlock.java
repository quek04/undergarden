package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import quek.undergarden.registry.UGBlocks;

public class UGBushBlock extends BushBlock {

    public UGBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(UGBlocks.deepturf_block.get()) || state.isIn(UGBlocks.deepsoil.get());
    }
}
