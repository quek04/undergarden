package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;

public class UGAttachedStemBlock extends AttachedStemBlock {

    public UGAttachedStemBlock(StemGrownBlock stemGrownBlock, Properties properties) {
        super(stemGrownBlock, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getBlock() == UGBlocks.DEEPSOIL_FARMLAND.get();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(UGItems.GLOOMGOURD_SEEDS.get());
    }
}