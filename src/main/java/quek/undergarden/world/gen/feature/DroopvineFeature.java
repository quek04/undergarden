package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import quek.undergarden.block.DroopvineBlock;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class DroopvineFeature extends Feature<NoFeatureConfig> {

    private static final Direction[] directionArray = Direction.values();

    public DroopvineFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!seedReader.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState blockstate = seedReader.getBlockState(pos.above());
            if (!blockstate.is(UGBlocks.DEPTHROCK.get()) && !blockstate.is(UGBlocks.SHIVERSTONE.get())) {
                return false;
            } else {
                this.placeRoofNetherWart(seedReader, rand, pos);
                this.placeRoofWeepingVines(seedReader, rand, pos);
                return true;
            }
        }
    }

    private void placeRoofNetherWart(IWorld world, Random rand, BlockPos pos) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();

        for(int i = 0; i < 200; ++i) {
            blockpos$mutable.setWithOffset(pos, rand.nextInt(6) - rand.nextInt(6), rand.nextInt(2) - rand.nextInt(5), rand.nextInt(6) - rand.nextInt(6));
            if (world.isEmptyBlock(blockpos$mutable)) {
                int j = 0;

                for(Direction direction : directionArray) {
                    BlockState blockstate = world.getBlockState(blockpos$mutable1.setWithOffset(blockpos$mutable, direction));
                    if (blockstate.is(UGBlocks.DEPTHROCK.get()) || blockstate.is(UGBlocks.SHIVERSTONE.get())) {
                        ++j;
                    }

                    if (j > 1) {
                        break;
                    }
                }
            }
        }
    }

    private void placeRoofWeepingVines(IWorld world, Random rand, BlockPos pos) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = 0; i < 100; ++i) {
            blockpos$mutable.setWithOffset(pos, rand.nextInt(8) - rand.nextInt(8), rand.nextInt(2) - rand.nextInt(7), rand.nextInt(8) - rand.nextInt(8));
            if (world.isEmptyBlock(blockpos$mutable)) {
                BlockState blockstate = world.getBlockState(blockpos$mutable.above());
                if (blockstate.is(UGBlocks.DEPTHROCK.get()) || blockstate.is(UGBlocks.SHIVERSTONE.get())) {
                    int j = MathHelper.nextInt(rand, 1, 8);
                    if (rand.nextInt(6) == 0) {
                        j *= 2;
                    }

                    if (rand.nextInt(5) == 0) {
                        j = 1;
                    }

                    placeDroopvine(world, rand, blockpos$mutable, j * 4, 17, 25);
                }
            }
        }

    }

    public static void placeDroopvine(IWorld world, Random rand, BlockPos.Mutable posMutable, int x, int y, int z) {
        for(int i = 0; i <= x; ++i) {
            if (world.isEmptyBlock(posMutable)) {
                if (i == x || !world.isEmptyBlock(posMutable.below())) {
                    world.setBlock(posMutable, UGBlocks.DROOPVINE_TOP.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, MathHelper.nextInt(rand, y, z)), 2);
                    break;
                }

                world.setBlock(posMutable, UGBlocks.DROOPVINE.get().defaultBlockState().setValue(DroopvineBlock.GLOWY, DroopvineBlock.randomTorF()), 2);
            }

            posMutable.move(Direction.DOWN);
        }

    }
}
