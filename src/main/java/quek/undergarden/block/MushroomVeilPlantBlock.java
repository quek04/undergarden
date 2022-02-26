package quek.undergarden.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;

public class MushroomVeilPlantBlock extends GrowingPlantBodyBlock {

    public static final VoxelShape SHAPE = Shapes.block();

    public MushroomVeilPlantBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return UGBlocks.MUSHROOM_VEIL.get();
    }
}