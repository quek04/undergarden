package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class IcePillarFeature extends Feature<NoFeatureConfig> {

    public IcePillarFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (reader.isEmptyBlock(pos) && !reader.isEmptyBlock(pos.above())) {
            BlockPos.Mutable blockpos$mutable = pos.mutable();
            BlockPos.Mutable blockpos$mutable1 = pos.mutable();
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;

            while(reader.isEmptyBlock(blockpos$mutable)) {
                if (World.isOutsideBuildHeight(blockpos$mutable)) {
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
            BlockPos.Mutable blockpos$mutable2 = new BlockPos.Mutable();

            for(int i = -3; i < 8; ++i) {
                for(int j = -3; j < 8; ++j) {
                    int k = MathHelper.abs(i) * MathHelper.abs(j);
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

    private void tryPlaceIce(IWorld world, Random rand, BlockPos pos) {
        if (rand.nextBoolean()) {
            world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
        }

    }

    private boolean stopOrPlaceIce(IWorld world, Random rand, BlockPos pos) {
        if (rand.nextInt(10) != 0) {
            world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
            return true;
        }
        else {
            return false;
        }
    }
}