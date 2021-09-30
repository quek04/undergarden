package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.Random;

public class UGStemBlock extends StemBlock {

    public UGStemBlock(StemGrownBlock pFruit, Properties pProperties) {
        super(pFruit, UGItems.GLOOMGOURD_SEEDS, pProperties);
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
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        if (!worldIn.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        float f = CropBlock.getGrowthSpeed(this, worldIn, pos);
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
            int i = state.getValue(AGE);
            if (i < 7) {
                worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
            } else {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = worldIn.getBlockState(blockpos.below());
                Block block = blockstate.getBlock();
                if (worldIn.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(worldIn, blockpos.below(), Direction.UP, this) || block == UGBlocks.DEEPSOIL_FARMLAND.get() || block == UGBlocks.DEEPSOIL.get() || block == UGBlocks.COARSE_DEEPSOIL.get() || block == UGBlocks.DEEPTURF_BLOCK.get())) {
                    worldIn.setBlockAndUpdate(blockpos, this.getFruit().defaultBlockState());
                    worldIn.setBlockAndUpdate(pos, this.getFruit().getAttachedStem().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                }
            }
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }
}
