package quek.undergarden.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenParticles;
import quek.undergarden.registry.UndergardenTEs;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class ShardWallTorchBlock extends ShardTorchBlock {

    public static final DirectionProperty HORIZONTAL_FACING;
    private static final Map<Direction, VoxelShape> SHAPES;

    public ShardWallTorchBlock() {

    }

    @Override
    public String getTranslationKey() {
        return this.asItem().getTranslationKey();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return getShapeForState(state);
    }

    public static VoxelShape getShapeForState(BlockState state) {
        return SHAPES.get(state.get(HORIZONTAL_FACING));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction lvt_4_1_ = state.get(HORIZONTAL_FACING);
        BlockPos lvt_5_1_ = pos.offset(lvt_4_1_.getOpposite());
        BlockState lvt_6_1_ = worldIn.getBlockState(lvt_5_1_);
        return lvt_6_1_.isSolidSide(worldIn, lvt_5_1_, lvt_4_1_);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState lvt_2_1_ = this.getDefaultState();
        IWorldReader lvt_3_1_ = context.getWorld();
        BlockPos lvt_4_1_ = context.getPos();
        Direction[] lvt_5_1_ = context.getNearestLookingDirections();
        Direction[] var6 = lvt_5_1_;
        int var7 = lvt_5_1_.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Direction lvt_9_1_ = var6[var8];
            if (lvt_9_1_.getAxis().isHorizontal()) {
                Direction lvt_10_1_ = lvt_9_1_.getOpposite();
                lvt_2_1_ = lvt_2_1_.with(HORIZONTAL_FACING, lvt_10_1_);
                if (lvt_2_1_.isValidPosition(lvt_3_1_, lvt_4_1_)) {
                    return lvt_2_1_;
                }
            }
        }

        return null;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction lvt_5_1_ = stateIn.get(HORIZONTAL_FACING);
        double lvt_6_1_ = (double)pos.getX() + 0.5D;
        double lvt_8_1_ = (double)pos.getY() + 0.7D;
        double lvt_10_1_ = (double)pos.getZ() + 0.5D;
        Direction lvt_16_1_ = lvt_5_1_.getOpposite();
        worldIn.addParticle(ParticleTypes.SMOKE, lvt_6_1_ + 0.27D * (double)lvt_16_1_.getXOffset(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double)lvt_16_1_.getZOffset(), 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(UndergardenParticles.shard.get(), lvt_6_1_ + 0.27D * (double)lvt_16_1_.getXOffset(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double)lvt_16_1_.getZOffset(), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    static {
        HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
        SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return UndergardenTEs.shard_torch_te.get().create();
    }
}
