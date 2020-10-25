package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class DitchbulbBlock extends UGBushBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

    public DitchbulbBlock() {
        super(Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0F)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
                .notSolid()
                .setLightLevel((state) -> 6)
        );
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isOpaqueCube(worldIn, pos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (block != UGBlocks.deepturf_block.get() && block != UGBlocks.deepsoil.get() && block != UGBlocks.depthrock.get()) {
            return worldIn.getLightSubtracted(pos, 0) < 0 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        } else {
            return true;
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.8D;
        double d2 = (double) pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

}
