package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class AshenTallDeepturfBlock extends UGPlantBlock {

    public AshenTallDeepturfBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerWorld serverWorld, Random rand, BlockPos pos, BlockState state) { }
}