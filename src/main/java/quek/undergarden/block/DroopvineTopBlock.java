package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class DroopvineTopBlock extends AbstractTopPlantBlock {

    public static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 16.0D, 16.0D, 16.0D);

    public DroopvineTopBlock(Properties properties, Direction direction, boolean waterloggable, double growthChance) {
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
        return UGBlocks.DROOPVINE.get().defaultBlockState().setValue(DroopvineBlock.GLOWY, DroopvineBlock.randomTorF()).getBlock();
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }
}