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
		BlockPos pos = context.origin();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();

		BlockStateConfiguration blockstateconfiguration;
		for (blockstateconfiguration = context.config(); pos.getY() > level.getMinBuildHeight() + 6; pos = pos.below()) {
			if (!level.isEmptyBlock(pos.below())) {
				BlockState blockstate = level.getBlockState(pos.below());
				if (isDirt(blockstate) || blockstate.is(UGTags.Blocks.BASE_STONE_UNDERGARDEN)) {
					break;
				}
			}
		}

		if (pos.getY() <= level.getMinBuildHeight() + 6 || pos.getY() >= 110) {
			return false;
		} else {
			for (int l = 0; l < 3; l++) {
				int x = random.nextInt(5);
				int y = random.nextInt(5);
				int z = random.nextInt(5);
				float f = (float)(x + y + z) * 0.333F + 0.5F;

				for (BlockPos blockpos1 : BlockPos.betweenClosed(pos.offset(-x, -y, -z), pos.offset(x, y, z))) {
					if (blockpos1.distSqr(pos) <= (double)(f * f)) {
						level.setBlock(blockpos1, blockstateconfiguration.state, 3);
					}
				}

				//blockpos = blockpos.offset(-1 + randomsource.nextInt(2), -randomsource.nextInt(2), -1 + randomsource.nextInt(2));
			}

			return true;
		}
	}
}