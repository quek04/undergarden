package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SpreadableSnowyDirtBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class UGGrassBlock extends SpreadableSnowyDirtBlock {

    public UGGrassBlock(Properties builder) {
        super(builder);
    }

    private static boolean isSnowyConditions(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.SNOW && blockstate.getValue(SnowBlock.LAYERS) == 1) {
            return true;
        } else {
            int i = LightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return isSnowyConditions(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!isSnowyConditions(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockAndUpdate(pos, UGBlocks.DEEPSOIL.get().defaultBlockState());
        }
        else {
            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos).is(UGBlocks.DEEPSOIL.get()) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos)) {
                    worldIn.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, worldIn.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                }
            }
        }
    }
}