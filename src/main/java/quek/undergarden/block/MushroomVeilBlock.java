package quek.undergarden.block;

import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MushroomVeilBlock extends GrowingPlantBodyBlock {

    public MushroomVeilBlock(Properties properties, Direction growthDirection, VoxelShape shape, boolean waterloggable) {
        super(properties, growthDirection, shape, waterloggable);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return UGBlocks.MUSHROOM_VEIL_TOP.get();
    }
}