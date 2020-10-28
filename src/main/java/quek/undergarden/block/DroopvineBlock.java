package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;
import java.util.function.ToIntFunction;

public class DroopvineBlock extends AbstractBodyPlantBlock {

    public static final BooleanProperty GLOWY = BooleanProperty.create("glowy");

    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 16.0D, 16.0D, 16.0D);

    public DroopvineBlock(AbstractBlock.Properties properties, Direction growthDirection, boolean waterloggable) {
        super(properties, growthDirection, SHAPE, waterloggable);
        this.setDefaultState(this.stateContainer.getBaseState().with(GLOWY, randomTorF()));
    }

    public static ToIntFunction<BlockState> glowIfGlowy() {
        return (state) -> state.get(GLOWY) ? 10 : 0;
    }

    public static boolean randomTorF() {
        return new Random().nextInt(5) == 1;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(GLOWY, randomTorF());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GLOWY);
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return (AbstractTopPlantBlock) UGBlocks.droopvine_top.get();
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }
}
