package quek.undergarden.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFeatures;

import java.util.Random;

public class UGMushroomBlock extends UGBushBlock implements IGrowable {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public UGMushroomBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(25) == 0) {
            int i = 5;

            for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4))) {
                if (worldIn.getBlockState(blockpos).getBlock() == this) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (worldIn.isAirBlock(blockpos1) && state.isValidPosition(worldIn, blockpos1)) {
                    pos = blockpos1;
                }

                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }

            if (worldIn.isAirBlock(blockpos1) && state.isValidPosition(worldIn, blockpos1)) {
                worldIn.setBlockState(blockpos1, state, 2);
            }
        }

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
        if (block != UGBlocks.deepturf_block.get() && block != UGBlocks.deepsoil.get()) {
            return worldIn.getLightSubtracted(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        } else {
            return true;
        }
    }

    public boolean bigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
        world.removeBlock(pos, false);
        ConfiguredFeature<?, ?> feature;
        if (this == UGBlocks.blood_mushroom.get()) {
            feature = UGFeatures.blood_mushroom.get().withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.blood_mushroom_cap.get().getDefaultState()), new SimpleBlockStateProvider(UGBlocks.blood_mushroom_stalk.get().getDefaultState()), 3));
        }
        else if(this == UGBlocks.indigo_mushroom.get()) {
            feature = UGFeatures.indigo_mushroom.get().withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.indigo_mushroom_cap.get().getDefaultState()), new SimpleBlockStateProvider(UGBlocks.indigo_mushroom_stalk.get().getDefaultState()), 3));
        }
        else if(this == UGBlocks.ink_mushroom.get()) {
            feature = UGFeatures.ink_mushroom.get().withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.ink_mushroom_cap.get().getDefaultState()), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 5));
        }
        else if(this == UGBlocks.veil_mushroom.get()) {
            feature = UGFeatures.veil_mushroom.get().withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(UGBlocks.veil_mushroom_cap.get().getDefaultState()), new SimpleBlockStateProvider(UGBlocks.veil_mushroom_stalk.get().getDefaultState()), 2));
        }
        else {
            if (this != Blocks.RED_MUSHROOM) {
                world.setBlockState(pos, state, 3);
                return false;
            }

            feature = Features.HUGE_RED_MUSHROOM;
        }

        if (feature.generate(world, world.getChunkProvider().getChunkGenerator(), rand, pos)) {
            return true;
        } else {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return (double)random.nextFloat() < 0.4D;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        this.bigMushroom(world, pos, state, rand);
    }
}
