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

    public static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 16.0D, 16.0D, 16.0D);

    public DroopvineBlock(Properties properties, Direction growthDirection, boolean waterloggable) {
        super(properties, growthDirection, SHAPE, waterloggable);
        this.registerDefaultState(this.stateDefinition.any().setValue(GLOWY, randomTorF()));
    }

    public static ToIntFunction<BlockState> glowIfGlowy() {
        return (state) -> state.getValue(GLOWY) ? 10 : 0;
    }

    public static boolean randomTorF() {
        return new Random().nextInt(5) == 1;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(GLOWY, randomTorF());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GLOWY);
    }

    @Override
    protected AbstractTopPlantBlock getHeadBlock() {
        return (AbstractTopPlantBlock) UGBlocks.DROOPVINE_TOP.get();
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }
}