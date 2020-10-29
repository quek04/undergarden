package quek.undergarden.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class MushroomVeilTopBlock extends AbstractTopPlantBlock {

    public MushroomVeilTopBlock(Properties properties, Direction direction, VoxelShape shape, boolean waterloggable, double growthChance) {
        super(properties, direction, shape, waterloggable, growthChance);
    }

    @Override
    protected int getGrowthAmount(Random rand) {
        return PlantBlockHelper.getGrowthAmount(rand);
    }

    @Override
    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Override
    protected Block getBodyPlantBlock() {
        return UGBlocks.mushroom_veil.get();
    }
}
