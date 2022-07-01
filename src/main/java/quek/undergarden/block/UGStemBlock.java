package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.Random;

public class UGStemBlock extends StemBlock {

    public UGStemBlock(StemGrownBlock block, Properties properties) {
        super(block, UGItems.GLOOMGOURD_SEEDS, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getBlock() == UGBlocks.DEEPSOIL_FARMLAND.get();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        float f = CropBlock.getGrowthSpeed(this, level, pos);
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
            int i = state.getValue(AGE);
            if (i < 7) {
                level.setBlock(pos, state.setValue(AGE, i + 1), 2);
            } else {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = level.getBlockState(blockpos.below());
                Block block = blockstate.getBlock();
                if (level.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(level, blockpos.below(), Direction.UP, this) || blockstate.is(BlockTags.DIRT) || block == UGBlocks.DEEPSOIL_FARMLAND.get() || block == UGBlocks.DEEPSOIL.get() || block == UGBlocks.COARSE_DEEPSOIL.get() || block == UGBlocks.DEEPTURF_BLOCK.get())) {
                    level.setBlockAndUpdate(blockpos, this.getFruit().defaultBlockState());
                    level.setBlockAndUpdate(pos, this.getFruit().getAttachedStem().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                }
            }
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
        }
    }
}
