package quek.undergarden.block;

import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MushroomVeilTopBlock extends GrowingPlantHeadBlock {

    public MushroomVeilTopBlock(Properties properties, Direction direction, VoxelShape shape, boolean waterloggable, double growthChance) {
        super(properties, direction, shape, waterloggable, growthChance);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(Random rand) {
        return NetherVines.getBlocksToGrowWhenBonemealed(rand);
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }

    @Override
    protected Block getBodyBlock() {
        return UGBlocks.MUSHROOM_VEIL.get();
    }
}