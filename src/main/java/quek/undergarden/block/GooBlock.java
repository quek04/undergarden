package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.entity.ScintlingEntity;

import java.util.Random;

public class GooBlock extends Block {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
    protected static final VoxelShape SHAPE = makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public GooBlock() {
        super(Properties.create(Material.ORGANIC)
                .hardnessAndResistance(1F, 0F)
                .noDrops()
                .doesNotBlockMovement()
                .tickRandomly()
                .sound(SoundType.SLIME)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(!(entityIn instanceof ScintlingEntity) && entityIn.onGround) {
            entityIn.setMotionMultiplier(state, new Vec3d(0.25D, 0.25D, 0.25D));
        }

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP);
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.removeBlock(pos, false);
        }
        if(rand.nextFloat() < 100F + (float)state.get(AGE) * 0.50F) {
            worldIn.removeBlock(pos, false);
        }
    }
}
