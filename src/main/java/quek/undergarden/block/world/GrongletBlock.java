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

public class GrongletBlock extends UGBushBlock implements IGrowable {
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

    @Override
    public boolean isValidGround(BlockState state, IBlockReader reader, BlockPos pos) {
        return state.isIn(Blocks.MYCELIUM) || super.isValidGround(state, reader, pos);
    }

    @Override
    public boolean canGrow(IBlockReader reader, BlockPos pos, BlockState state, boolean isClient) {
        Block lvt_5_1_ = this.fungusConfig.get().config.field_236303_f_.getBlock();
        Block lvt_6_1_ = reader.getBlockState(pos.down()).getBlock();
        return lvt_6_1_ == lvt_5_1_;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return (double)rand.nextFloat() < 0.4D;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        this.fungusConfig.get().func_242765_a(world, world.getChunkProvider().getChunkGenerator(), rand, pos);
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }
}