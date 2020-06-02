package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;


public class UndergardenWallBlock extends WallBlock {
    public static final BooleanProperty UP = BlockStateProperties.UP;
    private final VoxelShape[] wallShapes;
    private final VoxelShape[] wallCollisionShapes;

    public UndergardenWallBlock(Material material, float hardness, float resistance, SoundType sound, int level, ToolType tool) {
        super(Properties.create(material)
                .hardnessAndResistance(hardness, resistance)
                .sound(sound)
                .harvestLevel(level)
                .harvestTool(tool)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(UP, Boolean.valueOf(true)).with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
        this.wallShapes = this.makeShapes(4.0F, 3.0F, 16.0F, 0.0F, 14.0F);
        this.wallCollisionShapes = this.makeShapes(4.0F, 3.0F, 24.0F, 0.0F, 24.0F);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(UP) ? this.wallShapes[this.getIndex(state)] : super.getShape(state, worldIn, pos, context);
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(UP) ? this.wallCollisionShapes[this.getIndex(state)] : super.getCollisionShape(state, worldIn, pos, context);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    private boolean func_220113_a(BlockState p_220113_1_, boolean p_220113_2_, Direction p_220113_3_) {
        Block block = p_220113_1_.getBlock();
        boolean flag = block.isIn(BlockTags.WALLS) || block instanceof FenceGateBlock && FenceGateBlock.isParallel(p_220113_1_, p_220113_3_);
        return !cannotAttach(block) && p_220113_2_ || flag;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockState blockstate = iworldreader.getBlockState(blockpos1);
        BlockState blockstate1 = iworldreader.getBlockState(blockpos2);
        BlockState blockstate2 = iworldreader.getBlockState(blockpos3);
        BlockState blockstate3 = iworldreader.getBlockState(blockpos4);
        boolean flag = this.func_220113_a(blockstate, blockstate.isSolidSide(iworldreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
        boolean flag1 = this.func_220113_a(blockstate1, blockstate1.isSolidSide(iworldreader, blockpos2, Direction.WEST), Direction.WEST);
        boolean flag2 = this.func_220113_a(blockstate2, blockstate2.isSolidSide(iworldreader, blockpos3, Direction.NORTH), Direction.NORTH);
        boolean flag3 = this.func_220113_a(blockstate3, blockstate3.isSolidSide(iworldreader, blockpos4, Direction.EAST), Direction.EAST);
        boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);
        return this.getDefaultState().with(UP, Boolean.valueOf(flag4 || !iworldreader.isAirBlock(blockpos.up()))).with(NORTH, Boolean.valueOf(flag)).with(EAST, Boolean.valueOf(flag1)).with(SOUTH, Boolean.valueOf(flag2)).with(WEST, Boolean.valueOf(flag3)).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        if (facing == Direction.DOWN) {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            Direction direction = facing.getOpposite();
            boolean flag = facing == Direction.NORTH ? this.func_220113_a(facingState, facingState.isSolidSide(worldIn, facingPos, direction), direction) : stateIn.get(NORTH);
            boolean flag1 = facing == Direction.EAST ? this.func_220113_a(facingState, facingState.isSolidSide(worldIn, facingPos, direction), direction) : stateIn.get(EAST);
            boolean flag2 = facing == Direction.SOUTH ? this.func_220113_a(facingState, facingState.isSolidSide(worldIn, facingPos, direction), direction) : stateIn.get(SOUTH);
            boolean flag3 = facing == Direction.WEST ? this.func_220113_a(facingState, facingState.isSolidSide(worldIn, facingPos, direction), direction) : stateIn.get(WEST);
            boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);
            return stateIn.with(UP, Boolean.valueOf(flag4 || !worldIn.isAirBlock(currentPos.up()))).with(NORTH, Boolean.valueOf(flag)).with(EAST, Boolean.valueOf(flag1)).with(SOUTH, Boolean.valueOf(flag2)).with(WEST, Boolean.valueOf(flag3));
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UP, NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }
}
