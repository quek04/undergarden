package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class UGAttachedStemBlock extends AttachedStemBlock {

    public UGAttachedStemBlock(StemGrownBlock stemGrownBlock, Properties properties) {
        super(stemGrownBlock, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == UGBlocks.DEEPSOIL_FARMLAND.get();
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(UGItems.GLOOMGOURD_SEEDS.get());
    }
}