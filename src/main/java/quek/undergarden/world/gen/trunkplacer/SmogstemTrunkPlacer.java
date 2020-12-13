package quek.undergarden.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import quek.undergarden.registry.UGTrunkPlacerTypes;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SmogstemTrunkPlacer extends AbstractTrunkPlacer {

    public static final Codec<SmogstemTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            func_236915_a_(me).apply(me, SmogstemTrunkPlacer::new));

    public SmogstemTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> func_230381_a_() {
        return UGTrunkPlacerTypes.SMOGSTEM_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader world, Random rand, int yMaybe, BlockPos pos, Set<BlockPos> posSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
        IBlockReader reader = (IBlockReader) world;
        int treeBaseHeight = config.trunkPlacer.func_236917_a_(rand);
        int j = treeBaseHeight / 8 + rand.nextInt(2);

        for (int k = 0; k < treeBaseHeight; ++k) {
            float thiccness = (1.0F - (float) k / (float) treeBaseHeight)*j;
            int l = MathHelper.ceil(treeBaseHeight);

            for (int i1 = -l; i1 <= l; ++i1) {
                float f1 = (float) MathHelper.abs(i1) - 0.25F;

                for (int j1 = -l; j1 <= l; ++j1) {
                    float f2 = (float) MathHelper.abs(j1) - 0.25F;
                    if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > thiccness * thiccness)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                        BlockState blockstate = reader.getBlockState(pos.add(i1, k, j1));
                        if (blockstate.isAir((IBlockReader) world, pos)) {
                            func_236911_a_(world, rand, pos.add(i1, k, j1), posSet, boundingBox, config);
                        }

                        if (k != 0 && l > 1) {
                            blockstate = reader.getBlockState(pos.add(i1, -k, j1));
                            if (blockstate.isAir((IBlockReader) world, pos)) {
                                func_236911_a_(world, rand, pos.add(i1, k, j1), posSet, boundingBox, config);
                            }
                        }
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(treeBaseHeight), 0, false));
    }
}
