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
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (reader.isAirBlock(pos) && !reader.isAirBlock(pos.up())) {
            BlockPos.Mutable blockpos$mutable = pos.toMutable();
            BlockPos.Mutable blockpos$mutable1 = pos.toMutable();
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;

            while(reader.isAirBlock(blockpos$mutable)) {
                if (World.isOutsideBuildHeight(blockpos$mutable)) {
                    return true;
                }

                reader.setBlockState(blockpos$mutable, Blocks.PACKED_ICE.getDefaultState(), 2);
                flag = flag && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.NORTH));
                flag1 = flag1 && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.SOUTH));
                flag2 = flag2 && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.WEST));
                flag3 = flag3 && this.stopOrPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.EAST));
                blockpos$mutable.move(Direction.DOWN);
            }

            blockpos$mutable.move(Direction.UP);
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.NORTH));
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.SOUTH));
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.WEST));
            this.tryPlaceIce(reader, rand, blockpos$mutable1.setAndMove(blockpos$mutable, Direction.EAST));
            blockpos$mutable.move(Direction.DOWN);
            BlockPos.Mutable blockpos$mutable2 = new BlockPos.Mutable();

            for(int i = -3; i < 8; ++i) {
                for(int j = -3; j < 8; ++j) {
                    int k = MathHelper.abs(i) * MathHelper.abs(j);
                    if (rand.nextInt(10) < 10 - k) {
                        blockpos$mutable2.setPos(blockpos$mutable.add(i, 0, j));
                        int l = 3;

                        while(reader.isAirBlock(blockpos$mutable1.setAndMove(blockpos$mutable2, Direction.DOWN))) {
                            blockpos$mutable2.move(Direction.DOWN);
                            --l;
                            if (l <= 0) {
                                break;
                            }
                        }

                        if (!reader.isAirBlock(blockpos$mutable1.setAndMove(blockpos$mutable2, Direction.DOWN))) {
                            reader.setBlockState(blockpos$mutable2, Blocks.PACKED_ICE.getDefaultState(), 2);
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
            world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState(), 2);
        }

    }

    private boolean stopOrPlaceIce(IWorld world, Random rand, BlockPos pos) {
        if (rand.nextInt(10) != 0) {
            world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState(), 2);
            return true;
        }
        else {
            return false;
        }
    }
}