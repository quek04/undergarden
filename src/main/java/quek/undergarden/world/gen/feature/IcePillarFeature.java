package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class IcePillarFeature extends Feature<NoneFeatureConfiguration> {

    public IcePillarFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
        if (reader.isEmptyBlock(pos) && !reader.isEmptyBlock(pos.above())) {
            BlockPos.MutableBlockPos blockpos$mutable = pos.mutable();
            BlockPos.MutableBlockPos blockpos$mutable1 = pos.mutable();
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;

            while(reader.isEmptyBlock(blockpos$mutable)) {
                if (Level.isOutsideBuildHeight(blockpos$mutable)) {
                    return true;
                }

                reader.setBlock(blockpos$mutable, Blocks.PACKED_ICE.defaultBlockState(), 2);
                flag = flag && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.NORTH));
                flag1 = flag1 && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.SOUTH));
                flag2 = flag2 && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.WEST));
                flag3 = flag3 && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.EAST));
                blockpos$mutable.move(Direction.DOWN);
            }

            blockpos$mutable.move(Direction.UP);
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.NORTH));
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.SOUTH));
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.WEST));
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setWithOffset(blockpos$mutable, Direction.EAST));
            blockpos$mutable.move(Direction.DOWN);
            BlockPos.MutableBlockPos blockpos$mutable2 = new BlockPos.MutableBlockPos();

            for(int i = -3; i < 8; ++i) {
                for(int j = -3; j < 8; ++j) {
                    int k = Mth.abs(i) * Mth.abs(j);
                    if (rand.nextInt(10) < 10 - k) {
                        blockpos$mutable2.set(blockpos$mutable.offset(i, 0, j));
                        int l = 3;

                        while(reader.isEmptyBlock(blockpos$mutable1.setWithOffset(blockpos$mutable2, Direction.DOWN))) {
                            blockpos$mutable2.move(Direction.DOWN);
                            --l;
                            if (l <= 0) {
                                break;
                            }
                        }

                        if (!reader.isEmptyBlock(blockpos$mutable1.setWithOffset(blockpos$mutable2, Direction.DOWN))) {
                            reader.setBlock(blockpos$mutable2, Blocks.PACKED_ICE.defaultBlockState(), 2);
                        }
                    }
                }
            }

            return true;
        }
        else {
            return false;
        }
    }

    private void tryPlaceIce(LevelAccessor world, Random rand, BlockPos pos) {
        if (rand.nextBoolean()) {
            world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
        }

    }

    private boolean stopOrPlaceIce(LevelAccessor world, Random rand, BlockPos pos) {
        if (rand.nextInt(10) != 0) {
            world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
            return true;
        }
        else {
            return false;
        }
    }
}