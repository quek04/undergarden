package quek.undergarden.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractSmallTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmogstemFeature extends AbstractSmallTreeFeature<TreeFeatureConfig> {

    public SmogstemFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i225820_1_) {
        super(p_i225820_1_);
    }

    @Override
    public boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos pos, Set<BlockPos> trunk, Set<BlockPos> leaves, MutableBoundingBox boundingBox, TreeFeatureConfig config) {
        int i = config.baseHeight + rand.nextInt(config.heightRandA + 1) + rand.nextInt(config.heightRandB + 1);
        int j = config.trunkHeight >= 0 ? config.trunkHeight + rand.nextInt(config.trunkHeightRandom + 1) : i - (config.foliageHeight + rand.nextInt(config.foliageHeightRandom + 1));
        int k = config.foliagePlacer.func_225573_a_(rand, j, i, config);
        Optional<BlockPos> optional = func_227212_a_(generationReader, i, j, k, pos, config);
        if (!optional.isPresent()) {
            return false;
        } else {
            BlockPos blockpos = optional.get();
            config.foliagePlacer.func_225571_a_(generationReader, rand, config, i, j, k, blockpos, leaves);
            this.func_227213_a_(generationReader, rand, i, blockpos, config.trunkTopOffset + rand.nextInt(config.trunkTopOffsetRandom + 1), trunk, boundingBox, config);
            return true;
        }
    }

    @Override
    public Optional<BlockPos> func_227212_a_(IWorldGenerationReader generationReader, int baseHeight, int trunkHeight, int foliagePlacer, BlockPos pos, TreeFeatureConfig treeFeatureConfigIn) {
        BlockPos blockpos = pos;

        if (blockpos.getY() >= 1 && blockpos.getY() + baseHeight + 1 <= generationReader.getMaxHeight()) {
            for(int i1 = 0; i1 <= baseHeight + 1; ++i1) {
                int j1 = treeFeatureConfigIn.foliagePlacer.func_225570_a_(trunkHeight, baseHeight, foliagePlacer, i1);
                BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

                for(int k = -j1; k <= j1; ++k) {
                    int l = -j1;

                    while(l <= j1) {
                        if (i1 + blockpos.getY() >= 0 && i1 + blockpos.getY() < generationReader.getMaxHeight()) {
                            blockpos$mutable.setPos(k + blockpos.getX(), i1 + blockpos.getY(), l + blockpos.getZ());
                            if (canBeReplacedByLogs(generationReader, blockpos$mutable) && (treeFeatureConfigIn.ignoreVines || !isVine(generationReader, blockpos$mutable))) {
                                ++l;
                                continue;
                            }

                            return Optional.empty();
                        }

                        return Optional.empty();
                    }
                }
            }

            return isSoilOrFarm(generationReader, blockpos.down(), treeFeatureConfigIn.getSapling()) && blockpos.getY() < generationReader.getMaxHeight() - baseHeight - 1 ? Optional.of(blockpos) : Optional.empty();
        } else {
            return Optional.empty();
        }
    }
}
