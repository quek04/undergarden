package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
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
import quek.undergarden.registry.UGParticles;
import quek.undergarden.registry.UGTileEntities;

import java.util.Random;

public class ShardTorchBlock extends Block {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public ShardTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                        .doesNotBlockMovement()
                        .hardnessAndResistance(0F)
                        .setLightLevel((state) -> 6)
                        .sound(SoundType.WOOD)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double lvt_5_1_ = (double)pos.getX() + 0.5D;
        double lvt_7_1_ = (double)pos.getY() + 0.7D;
        double lvt_9_1_ = (double)pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, lvt_5_1_, lvt_7_1_, lvt_9_1_, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(UGParticles.shard.get(), lvt_5_1_, lvt_7_1_, lvt_9_1_, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return UGTileEntities.shard_torch_te.get().create();
    }
}
