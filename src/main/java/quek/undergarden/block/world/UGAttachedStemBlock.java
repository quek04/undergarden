package quek.undergarden.block.world;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.Map;

public class UGAttachedStemBlock extends AttachedStemBlock {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private final StemGrownBlock grownFruit;
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.SOUTH, Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 16.0D), Direction.WEST, Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D), Direction.NORTH, Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D), Direction.EAST, Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 16.0D, 10.0D, 10.0D)));

    public UGAttachedStemBlock(StemGrownBlock stemGrownBlock) {
        super(stemGrownBlock, Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0F)
                .sound(SoundType.STEM)
                .noDrops()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
        this.grownFruit = stemGrownBlock;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state.get(FACING));
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facingState.getBlock() != this.grownFruit && facing == stateIn.get(FACING) ? this.grownFruit.getStem().getDefaultState().with(UGStemBlock.AGE, 7) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == Blocks.FARMLAND || state.getBlock() == UGBlocks.deepsoil_farmland.get();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(UGItems.gloomgourd_seeds.get());
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
        builder.add(FACING);
    }

}
