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

public class DepthsHoleFeature extends Feature<NoneFeatureConfiguration> {

	public DepthsHoleFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		RandomSource random = context.random();

		if (!level.getBlockState(pos.above()).isAir()) {
			return false;
		} else {
			int radius = random.nextInt(8) + 1;
			for (int y = -32; y < pos.getY(); y++) {
				disk(radius, new BlockPos(pos.getX(), y, pos.getZ()), level);
			}
			return true;
		}
	}

	private void disk(int radius, BlockPos newPos, WorldGenLevel level) {
		float radiusSq = radius * radius;
		for (int z = 0; z < radius; z++) {
			for (int x = 0; x < radius; x++) {
				if (x * x + z * z > radiusSq) continue;
				BlockState state = Blocks.AIR.defaultBlockState();
				level.setBlock(newPos.offset(x, 0, z), state, 3);
				level.setBlock(newPos.offset(-x, 0, -z), state, 3);
				level.setBlock(newPos.offset(-x, 0, z), state, 3);
				level.setBlock(newPos.offset(x, 0, -z), state, 3);
			}
		}
	}
}
