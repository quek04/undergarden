package quek.undergarden.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class HangingGrongleLeavesTopBlock extends AbstractTopPlantBlock {

    protected static final VoxelShape SHAPE = Block.box(4.0D, 5.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public HangingGrongleLeavesTopBlock(Properties properties, Direction direction, boolean waterloggable, double growthChance) {
        super(properties, direction, SHAPE, waterloggable, growthChance);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(Random rand) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return PlantBlockHelper.isValidGrowthState(state);
    }

    @Override
    protected Block getBodyBlock() {
        return UGBlocks.HANGING_GRONGLE_LEAVES.get();
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachToBlock(block)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || block == UGBlocks.GRONGLE_LEAVES.get();
        }
    }
}