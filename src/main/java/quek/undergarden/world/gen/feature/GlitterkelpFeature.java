package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import quek.undergarden.block.GlitterkelpBlock;
import quek.undergarden.registry.UGBlocks;

public class GlitterkelpFeature extends Feature<NoneFeatureConfiguration> {

    public GlitterkelpFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel level = pContext.level();
        BlockPos pos = pContext.origin();
        RandomSource random = pContext.random();
        int i = 0;
        int oceanY = 32;
        BlockPos blockpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        if(pos.getY() < oceanY) {
            if (level.getBlockState(blockpos).getBlock() == Blocks.WATER) {
                BlockState kelp = UGBlocks.GLITTERKELP.get().defaultBlockState();
                BlockState kelpTop = UGBlocks.GLITTERKELP_PLANT.get().defaultBlockState();
                int k = 1 + random.nextInt(10);

                for(int l = 0; l <= k; ++l) {
                    if (level.getBlockState(blockpos).getBlock() == Blocks.WATER && level.getBlockState(blockpos.above()).getBlock() == Blocks.WATER && kelpTop.canSurvive(level, blockpos)) {
                        if (l == k) {
                            level.setBlock(blockpos, kelp.setValue(GlitterkelpBlock.AGE, random.nextInt(4) + 20), 2);
                            ++i;
                        } else {
                            level.setBlock(blockpos, kelpTop, 2);
                        }
                    } else if (l > 0) {
                        BlockPos blockpos1 = blockpos.below();
                        if (kelp.canSurvive(level, blockpos1) && level.getBlockState(blockpos1.below()).getBlock() != UGBlocks.GLITTERKELP.get()) {
                            level.setBlock(blockpos1, kelp.setValue(GlitterkelpBlock.AGE, random.nextInt(4) + 20), 2);
                            ++i;
                        }
                        break;
                    }

                    blockpos = blockpos.above();
                }
            }
        }
        return i > 0;
    }
}