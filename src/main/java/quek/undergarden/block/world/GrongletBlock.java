package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class GrongletBlock extends UndergardenBushBlock implements IGrowable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    private final Supplier<ConfiguredFeature<HugeFungusConfig, ?>> fungusConfig;

    public GrongletBlock(AbstractBlock.Properties properties, Supplier<ConfiguredFeature<HugeFungusConfig, ?>> fungusConfig) {
        super(properties);
        this.fungusConfig = fungusConfig;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    /**
     * Whether this IGrowable can grow
     */
    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        Block block = (this.fungusConfig.get()).config.field_236303_f_.getBlock();
        Block block1 = worldIn.getBlockState(pos.down()).getBlock();
        return block1 == block;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double)rand.nextFloat() < 0.4D;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.fungusConfig.get().func_236265_a_(worldIn, worldIn.func_241112_a_(), worldIn.getChunkProvider().getChunkGenerator(), rand, pos);
    }
}