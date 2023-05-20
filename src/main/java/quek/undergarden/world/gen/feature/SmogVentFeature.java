package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

public class SmogVentFeature extends Feature<NoneFeatureConfiguration> {

	public SmogVentFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		RandomSource random = context.random();
		while (level.isEmptyBlock(pos) && pos.getY() > 2) {
			pos = pos.below();
		}

		if (level.isEmptyBlock(pos.above(7))/* && level.getBlockState(pos).getBlock() == UGBlocks.ASHEN_DEEPTURF_BLOCK.get()*/) {
			pos = pos.above(random.nextInt(4));
			int ventHeight = 7;
			int j = ventHeight / 4 + random.nextInt(2);

			for (int k = 0; k < ventHeight; ++k) {
				float f = (1.0F - (float) k / (float) ventHeight) * (float) j;
				int l = Mth.ceil(f);

				for (int i1 = -l; i1 <= l; ++i1) {
					float f1 = (float) Mth.abs(i1) - 0.25F;

					for (int j1 = -l; j1 <= l; ++j1) {
						float f2 = (float) Mth.abs(j1) - 0.25F;
						if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(random.nextFloat() > 0.75F))) {
							BlockState blockstate = level.getBlockState(pos.offset(i1, k, j1));
							//Block block = blockstate.getBlock();
							BlockPos ventPos = new BlockPos(pos.getX(), pos.getY() + 7, pos.getZ());
							if (blockstate.isAir()/* || block == UGBlocks.ASHEN_DEEPTURF_BLOCK.get()*/) {
								this.setBlock(level, pos.offset(i1, k, j1), UGBlocks.DEPTHROCK.get().defaultBlockState());
							}
							this.setBlock(level, ventPos, UGBlocks.SMOG_VENT.get().defaultBlockState());

							if (k != 0 && l > 1) {
								blockstate = level.getBlockState(pos.offset(i1, -k, j1));
								//block = blockstate.getBlock();
								if (blockstate.isAir()/* || block == UGBlocks.ASHEN_DEEPTURF_BLOCK.get()*/) {
									this.setBlock(level, pos.offset(i1, -k, j1), UGBlocks.DEPTHROCK.get().defaultBlockState());
								}
							}
						}
					}
				}
			}

			int k1 = j - 1;

			for (int l1 = -k1; l1 <= k1; ++l1) {
				for (int i2 = -k1; i2 <= k1; ++i2) {
					BlockPos blockpos = pos.offset(l1, -1, i2);
					BlockState blockstate1 = level.getBlockState(blockpos);
					//Block block1 = blockstate1.getBlock();
					if (!blockstate1.isAir()/* && block1 != UGBlocks.ASHEN_DEEPTURF_BLOCK.get() && block1 != UGBlocks.DEEPSOIL.get() && block1 != UGBlocks.DEPTHROCK.get()*/) {
						break;
					}

					level.setBlock(blockpos, UGBlocks.DEPTHROCK.get().defaultBlockState(), 1);
				}
			}
			return true;
		} else {
			return false;
		}
	}
}