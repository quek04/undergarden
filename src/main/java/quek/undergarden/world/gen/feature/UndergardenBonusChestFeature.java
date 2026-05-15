package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGBuiltinLootTables;

import java.util.stream.IntStream;

public class UndergardenBonusChestFeature extends Feature<NoneFeatureConfiguration> {

	public UndergardenBonusChestFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		ChunkPos chunkPos = ChunkPos.containing(context.origin());
		IntArrayList xPoses = Util.toShuffledList(IntStream.rangeClosed(chunkPos.getMinBlockX(), chunkPos.getMaxBlockX()), random);
		IntArrayList zPoses = Util.toShuffledList(IntStream.rangeClosed(chunkPos.getMinBlockZ(), chunkPos.getMaxBlockZ()), random);

		for (Integer x : xPoses) {
			for (Integer z : zPoses) {
				BlockPos chestPos = getFreeYSpotNearby(level, x, z);
				if (chestPos != null && (level.isEmptyBlock(chestPos) || level.getBlockState(chestPos).getCollisionShape(level, chestPos).isEmpty())) {
					level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 2);
					RandomizableContainer.setBlockEntityLootTable(level, random, chestPos, UGBuiltinLootTables.UG_BONUS_CHEST);
					BlockState torch = UGBlocks.SHARD_TORCH.get().defaultBlockState();

					for (Direction direction : Direction.Plane.HORIZONTAL) {
						for (int i = -1; i <= 1; i++) {
							BlockPos torchPos = chestPos.relative(direction).atY(chestPos.getY() + i);
							if (torch.canSurvive(level, torchPos) && level.getBlockState(torchPos).canBeReplaced()) {
								level.setBlock(torchPos, torch, 2);
								break;
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	private static @Nullable BlockPos getFreeYSpotNearby(WorldGenLevel level, int x, int z) {
		int startY = level.getLevelData().getRespawnData().pos().getY() + 20;
		if (startY >= 0) {
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			for (int y = startY - 1; y >= startY - 40; y--) {
				pos.set(x, y, z);
				BlockState blockState = level.getBlockState(pos);
				if (!blockState.getFluidState().isEmpty()) {
					break;
				}

				if (Block.isFaceFull(blockState.getCollisionShape(level, pos), Direction.UP) && level.isEmptyBlock(pos.above())) {
					return pos.above().immutable();
				}
			}
		}
		return null;
	}
}
