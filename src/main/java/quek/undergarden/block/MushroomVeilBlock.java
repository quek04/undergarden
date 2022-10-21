package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class MushroomVeilBlock extends VineBlock {

    public static final BooleanProperty END = BooleanProperty.create("end");

    public MushroomVeilBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(END, false)
                .setValue(UP, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
        );
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState updatedState = this.getUpdatedState(state, level, currentPos);
        boolean end = !level.getBlockState(currentPos.below()).is(this);
        return !this.hasFaces(updatedState) ? Blocks.AIR.defaultBlockState() : updatedState.setValue(END, end);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        if (!(super.getStateForPlacement(context) == null)) {
            boolean end = !level.getBlockState(clickedPos.below()).is(this);
            return super.getStateForPlacement(context).setValue(END, end);
        }
        return super.getStateForPlacement(context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(END);
        super.createBlockStateDefinition(builder);
    }
}
