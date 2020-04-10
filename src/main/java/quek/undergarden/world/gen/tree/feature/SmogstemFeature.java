package quek.undergarden.world.gen.tree.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import quek.undergarden.world.gen.config.UndergardenTreeFeatureConfig;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmogstemFeature<T extends UndergardenTreeFeatureConfig> extends AbstractTreeFeature<T> {

    public SmogstemFeature(Function<Dynamic<?>, T> config) {
        super(config);
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
        int height = rand.nextInt(4) + rand.nextInt(4) + 8;
        boolean canGrow = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 128) {
            for (int cy = position.getY(); cy <= position.getY() + 1 + height; ++cy) {
                int k = 1;

                if (cy == position.getY()) {
                    k = 0;
                }

                if (cy >= position.getY() + 1 + height - 2) {
                    k = 2;
                }

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for (int cx = position.getX() - k; cx <= position.getX() + k && canGrow; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && canGrow; ++cz) {
                        if (cy >= 0 && cy < 128) {
                            if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(cx, cy, cz))) {
                                canGrow = false;
                            }
                        } else {
                            canGrow = false;
                        }
                    }
                }
            }

            if (!canGrow) {
                return false;
            } else if (isSoil(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight() - height - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int i2 = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, i2, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.func_227216_a_(worldIn, rand, blockpos, logPos, boundingBox, config);
                        posY = i2;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);

                for (int j3 = -3; j3 <= 3; ++j3) {
                    for (int i4 = -3; i4 <= 3; ++i4) {
                        if (Math.abs(j3) != 3 || Math.abs(i4) != 3){
                            this.func_227219_b_(worldIn, rand, blockpos2.add(j3, 0, i4), leavesPos, boundingBox, config);
                        }
                    }
                }

                blockpos2 = blockpos2.up();

                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        this.func_227219_b_(worldIn, rand, blockpos2.add(k3, 0, j4), leavesPos, boundingBox, config);
                    }
                }

                this.func_227219_b_(worldIn, rand, blockpos2.east(2), leavesPos, boundingBox, config);
                this.func_227219_b_(worldIn, rand, blockpos2.west(2), leavesPos, boundingBox, config);
                this.func_227219_b_(worldIn, rand, blockpos2.south(2), leavesPos, boundingBox, config);
                this.func_227219_b_(worldIn, rand, blockpos2.north(2), leavesPos, boundingBox, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
