package quek.undergarden.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class DroopvineTopBlock extends AbstractTopPlantBlock {

    public static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 16.0D, 16.0D, 16.0D);

    public DroopvineTopBlock(Properties properties, Direction direction, boolean waterloggable, double growthChance) {
        super(properties, direction, SHAPE, waterloggable, growthChance);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
        if (direction == this.growthDirection.getOpposite() && !state.canSurvive(world, pos)) {
            world.getBlockTicks().scheduleTick(pos, this, 1);
        }

        if (direction != this.growthDirection || !state2.is(this) && !state2.is(this.getBodyBlock())) {
            if (this.scheduleFluidTicks) {
                world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
            }

            return super.updateShape(state, direction, state2, world, pos, pos2);
        }
        else {
            return this.getBodyBlock().defaultBlockState().setValue(DroopvineBlock.GLOWY, world.getRandom().nextBoolean());
        }
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
        return UGBlocks.DROOPVINE.get();
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }
}