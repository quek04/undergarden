package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGParticleTypes;

import java.util.Random;

public class UGDoublePlantBlock extends DoublePlantBlock {

    protected static final VoxelShape UPPER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected static final VoxelShape LOWER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public UGDoublePlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(UGBlocks.DEEPTURF_BLOCK.get()) || state.is(UGBlocks.DEEPSOIL.get()) || state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE.move(offset.x, offset.y, offset.z) : UPPER_SHAPE.move(offset.x, offset.y, offset.z);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(this == UGBlocks.TALL_SHIMMERWEED.get() && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            double x = (double) pos.getX() + random.nextFloat();
            double y = (double) pos.getY() + random.nextFloat();
            double z = (double) pos.getZ() + random.nextFloat();
            double xSpeed = (double)random.nextFloat() * -0.9D * (double)random.nextFloat();
            double zSpeed = (double)random.nextFloat() * -0.9D * (double)random.nextFloat();
            level.addParticle(UGParticleTypes.SHIMMER.get(), x, y, z, xSpeed, 0.0D, zSpeed);
        }
    }
}