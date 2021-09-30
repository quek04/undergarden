package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

public class HangingGrongleLeavesBlock extends GrowingPlantBodyBlock {

    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public HangingGrongleLeavesBlock(Properties properties, Direction growthDirection, boolean waterloggable) {
        super(properties, growthDirection, SHAPE, waterloggable);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return UGBlocks.HANGING_GRONGLE_LEAVES_TOP.get();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate)) {
            return false;
        }
        else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || block == UGBlocks.GRONGLE_LEAVES.get();
        }
    }
}
