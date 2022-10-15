package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import quek.undergarden.block.Droopvine;
import quek.undergarden.registry.UGBlocks;

public class DroopvineFeature extends Feature<NoneFeatureConfiguration> {

    public DroopvineFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        if(!level.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(pos.above());
            if(!blockstate.is(UGBlocks.DEPTHROCK.get()) && !blockstate.is(UGBlocks.SHIVERSTONE.get())) {
                return false;
            } else {
                this.placeRoofDroopvine(level, random, pos);
                return true;
            }
        }
    }

    private void placeRoofDroopvine(LevelAccessor world, RandomSource random, BlockPos pos) {
        BlockPos.MutableBlockPos posMutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 100; ++i) {
            posMutable.setWithOffset(pos, random.nextInt(8) - random.nextInt(8), random.nextInt(2) - random.nextInt(7), random.nextInt(8) - random.nextInt(8));
            if (world.isEmptyBlock(posMutable)) {
                BlockState blockstate = world.getBlockState(posMutable.above());
                if (blockstate.is(UGBlocks.DEPTHROCK.get()) || blockstate.is(UGBlocks.SHIVERSTONE.get())) {
                    int length = Mth.nextInt(random, 1, 8);
                    if (random.nextInt(6) == 0) {
                        length *= 2;
                    }

                    if (random.nextInt(5) == 0) {
                        length = 1;
                    }

                    placeDroopvineColumn(world, random, posMutable, length, 17, 25);
                }
            }
        }
    }

    public static void placeDroopvineColumn(LevelAccessor level, RandomSource random, BlockPos.MutableBlockPos posMutable, int length, int min, int max) {
        for(int i = 0; i <= length; ++i) {
            if (level.isEmptyBlock(posMutable)) {
                if (i == length || !level.isEmptyBlock(posMutable.below())) {
                    level.setBlock(posMutable, UGBlocks.DROOPVINE.get().defaultBlockState().setValue(Droopvine.GLOWY, level.getRandom().nextBoolean()).setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, min, max)), 2);
                    break;
                }

                level.setBlock(posMutable, UGBlocks.DROOPVINE_PLANT.get().defaultBlockState().setValue(Droopvine.GLOWY, level.getRandom().nextBoolean()), 2);
            }

            posMutable.move(Direction.DOWN);
        }
    }
}