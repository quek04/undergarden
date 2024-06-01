package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import quek.undergarden.registry.UGTags;

public class UGBlockBlobFeature extends Feature<BlockStateConfiguration> {

	public UGBlockBlobFeature(Codec<BlockStateConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
		BlockPos blockpos = context.origin();
		WorldGenLevel worldgenlevel = context.level();
		RandomSource randomsource = context.random();

		BlockStateConfiguration blockstateconfiguration;
		for (blockstateconfiguration = context.config(); blockpos.getY() > worldgenlevel.getMinBuildHeight() + 3; blockpos = blockpos.below()) {
			if (!worldgenlevel.isEmptyBlock(blockpos.below())) {
				BlockState blockstate = worldgenlevel.getBlockState(blockpos.below());
				if (isDirt(blockstate) || blockstate.is(UGTags.Blocks.BASE_STONE_UNDERGARDEN)) {
					break;
				}
			}
		}

		if (blockpos.getY() <= worldgenlevel.getMinBuildHeight() + 3) {
			return false;
		} else {
			for (int l = 0; l < 3; l++) {
				int i = randomsource.nextInt(2);
				int j = randomsource.nextInt(2);
				int k = randomsource.nextInt(2);
				float f = (float)(i + j + k) * 0.333F + 0.5F;

				for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i, -j, -k), blockpos.offset(i, j, k))) {
					if (blockpos1.distSqr(blockpos) <= (double)(f * f)) {
						worldgenlevel.setBlock(blockpos1, blockstateconfiguration.state, 3);
					}
				}

				blockpos = blockpos.offset(-1 + randomsource.nextInt(2), -randomsource.nextInt(2), -1 + randomsource.nextInt(2));
			}

			return true;
		}
	}
}