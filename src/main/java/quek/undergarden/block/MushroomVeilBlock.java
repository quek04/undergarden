package quek.undergarden.block;

import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

public class MushroomVeilBlock extends AbstractBodyPlantBlock {

    public MushroomVeilBlock(Properties properties, Direction growthDirection, VoxelShape shape, boolean waterloggable) {
        super(properties, growthDirection, shape, waterloggable);
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return UGBlocks.MUSHROOM_VEIL_TOP.get();
    }
}
