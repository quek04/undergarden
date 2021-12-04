package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGConfiguredFeatures;

import java.util.Random;

public class UGMushroomBlock extends UGBushBlock implements BonemealableBlock {

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public UGMushroomBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(25) == 0) {
            int i = 5;

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
                if (worldIn.getBlockState(blockpos).getBlock() == this) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.offset(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (worldIn.isEmptyBlock(blockpos1) && state.canSurvive(worldIn, blockpos1)) {
                    pos = blockpos1;
                }

                blockpos1 = pos.offset(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }

            if (worldIn.isEmptyBlock(blockpos1) && state.canSurvive(worldIn, blockpos1)) {
                worldIn.setBlock(blockpos1, state, 2);
            }
        }

    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.isSolidRender(worldIn, pos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (block != UGBlocks.DEEPTURF_BLOCK.get() && block != UGBlocks.DEEPSOIL.get()) {
            return worldIn.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.core.Direction.UP, this);
        } else {
            return true;
        }
    }

    public void bigMushroom(ServerLevel world, BlockPos pos, BlockState state, Random rand) {
        world.removeBlock(pos, false);
        ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> feature;
        if (this == UGBlocks.BLOOD_MUSHROOM.get()) {
            feature = UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM;
        }
        else if(this == UGBlocks.INDIGO_MUSHROOM.get()) {
            feature = UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM;
        }
        else if(this == UGBlocks.INK_MUSHROOM.get()) {
            feature = UGConfiguredFeatures.HUGE_INK_MUSHROOM;
        }
        else if(this == UGBlocks.VEIL_MUSHROOM.get()) {
            feature = UGConfiguredFeatures.HUGE_VEIL_MUSHROOM;
        }
        else {
            return;
        }

        if (feature.place(world, world.getChunkSource().getGenerator(), rand, pos)) {
        }
        else {
            world.setBlock(pos, state, 3);
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos blockPos, BlockState blockState) {
        return (double)random.nextFloat() < 0.4D;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
        this.bigMushroom(world, pos, state, rand);
    }
}