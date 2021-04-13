package quek.undergarden.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class MushroomVeilTopBlock extends AbstractTopPlantBlock {

    public MushroomVeilTopBlock(Properties properties, Direction direction, VoxelShape shape, boolean waterloggable, double growthChance) {
        super(properties, direction, shape, waterloggable, growthChance);
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
        return UGBlocks.MUSHROOM_VEIL.get();
    }
}