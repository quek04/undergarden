package quek.undergarden.block;

import com.google.common.base.Predicates;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import quek.undergarden.registry.UndergardenBlocks;

public class UndergardenPortalFrameBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty CATALYST = BlockStateProperties.EYE;
    protected static final VoxelShape BASE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
    protected static final VoxelShape CATALYST_SHAPE = Block.makeCuboidShape(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape BASE_WITH_CATALYST_SHAPE = VoxelShapes.or(BASE_SHAPE, CATALYST_SHAPE);
    private static BlockPattern portalShape;

    public UndergardenPortalFrameBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(-1F)
                .noDrops()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(CATALYST, Boolean.FALSE));
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(CATALYST) ? BASE_WITH_CATALYST_SHAPE : BASE_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(CATALYST, Boolean.FALSE);
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return blockState.get(CATALYST) ? 15 : 0;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, CATALYST);
    }

    public static BlockPattern getOrCreatePortalShape() {
        if (portalShape == null) {
            portalShape = BlockPatternBuilder.start().aisle("?vvv?", ">???<", ">???<", ">???<", "?^^^?").where('?', CachedBlockInfo.hasState(BlockStateMatcher.ANY)).where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UndergardenBlocks.undergarden_portal_frame.get()).where(CATALYST, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(Direction.SOUTH)))).where('>', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UndergardenBlocks.undergarden_portal_frame.get()).where(CATALYST, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(Direction.WEST)))).where('v', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UndergardenBlocks.undergarden_portal_frame.get()).where(CATALYST, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(Direction.NORTH)))).where('<', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(UndergardenBlocks.undergarden_portal_frame.get()).where(CATALYST, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(Direction.EAST)))).build();
        }

        return portalShape;
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

}
