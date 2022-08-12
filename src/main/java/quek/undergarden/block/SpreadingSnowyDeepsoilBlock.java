package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import quek.undergarden.registry.UGBlocks;

public class SpreadingSnowyDeepsoilBlock extends SpreadingSnowyDirtBlock {

    public SpreadingSnowyDeepsoilBlock(Properties properties) {
        super(properties);
    }

    private static boolean canBeGrass(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        }
        else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        }
        else {
            int i = LayerLightEngine.getLightBlockInto(level, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(level, blockpos));
            return i < level.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            level.setBlockAndUpdate(pos, UGBlocks.DEEPSOIL.get().defaultBlockState());
        }
        else {
            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (level.getBlockState(blockpos).is(UGBlocks.DEEPSOIL.get()) && canPropagate(blockstate, level, blockpos)) {
                    level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                }
            }
        }
    }
}