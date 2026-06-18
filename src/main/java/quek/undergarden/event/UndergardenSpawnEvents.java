package quek.undergarden.event;

import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.jspecify.annotations.Nullable;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.registry.UGAttachments;
import quek.undergarden.registry.UGConfiguredFeatures;
import quek.undergarden.registry.UGDimensions;

import java.util.Set;

public class UndergardenSpawnEvents {

	public static void init() {
		NeoForge.EVENT_BUS.addListener(UndergardenSpawnEvents::cancelVanillaSpawnLogic);
		NeoForge.EVENT_BUS.addListener(UndergardenSpawnEvents::setWorldSpawnToUndergarden);
	}

	//cancel vanilla spawn event, we will be firing our own logic when the player exists.
	private static void cancelVanillaSpawnLogic(LevelEvent.CreateSpawnPosition event) {
		if (!event.isCanceled() && UndergardenConfig.Server.spawn_in_undergarden.get()) {
			event.setCanceled(true);
		}
	}

	private static void setWorldSpawnToUndergarden(PlayerEvent.PlayerLoggedInEvent event) {
		if (UndergardenConfig.Server.spawn_in_undergarden.get() && !event.getEntity().getData(UGAttachments.UNDERGARDEN_DATA).handledStartSpawn() && event.getEntity() instanceof ServerPlayer player) {
			MinecraftServer server = player.level().getServer();
			ServerLevel level = server.getLevel(UGDimensions.UNDERGARDEN_LEVEL);
			if (level != null) {
				player.setData(UGAttachments.UNDERGARDEN_DATA, player.getData(UGAttachments.UNDERGARDEN_DATA).handleSpawn());
				ServerLevelData levelData = (ServerLevelData) level.getLevelData();
				ServerChunkCache chunkSource = level.getChunkSource();
				ChunkPos spawnChunk = ChunkPos.containing(chunkSource.randomState().sampler().findSpawnPosition());

				int xChunkOffset = 0;
				int zChunkOffset = 0;
				int dXChunk = 0;
				int dZChunk = -1;

				for (int i = 0; i < Mth.square(11); i++) {
					if (xChunkOffset >= -5 && xChunkOffset <= 5 && zChunkOffset >= -5 && zChunkOffset <= 5) {
						BlockPos testedPos = getSpawnPosInChunk(level, new ChunkPos(spawnChunk.x() + xChunkOffset, spawnChunk.z() + zChunkOffset));
						if (testedPos != null) {
							level.getChunk(testedPos);
							levelData.setSpawn(LevelData.RespawnData.of(level.dimension(), testedPos, 0.0F, 0.0F));
							break;
						}
					}

					if (xChunkOffset == zChunkOffset || xChunkOffset < 0 && xChunkOffset == -zChunkOffset || xChunkOffset > 0 && xChunkOffset == 1 - zChunkOffset) {
						int olddx = dXChunk;
						dXChunk = -dZChunk;
						dZChunk = olddx;
					}

					xChunkOffset += dXChunk;
					zChunkOffset += dZChunk;
				}

				Vec3 pos = fixupSpawnHeight(level, levelData.getRespawnData().pos());
				player.teleportTo(level, pos.x(), pos.y(), pos.z(), Set.of(), player.getYRot(), player.getXRot(), false);
				player.setRespawnPosition(new ServerPlayer.RespawnConfig(levelData.getRespawnData(), true), false);

				if (server.getWorldGenSettings().options().generateBonusChest()) {
					level.registryAccess()
						.lookup(Registries.CONFIGURED_FEATURE)
						.flatMap(registry -> registry.get(UGConfiguredFeatures.UG_BONUS_CHEST))
						.ifPresent(feature -> feature.value().place(level, chunkSource.getGenerator(), level.getRandom(), levelData.getRespawnData().pos()));
				}
			}
		}
	}

	public static @Nullable BlockPos getUndergardenRespawnPos(LevelReader level, int x, int z) {
		int topY = level.getMaxY();
		if (topY >= 0) {
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			for (int y = topY - 1; y >= 0; y--) {
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

	public static @Nullable BlockPos getSpawnPosInChunk(ServerLevel level, ChunkPos chunkPos) {
		if (!SharedConstants.debugVoidTerrain(chunkPos)) {
			for (int x = chunkPos.getMinBlockX(); x <= chunkPos.getMaxBlockX(); x++) {
				for (int z = chunkPos.getMinBlockZ(); z <= chunkPos.getMaxBlockZ(); z++) {
					BlockPos validSpawnPosition = getUndergardenRespawnPos(level, x, z);
					if (validSpawnPosition != null) {
						return validSpawnPosition;
					}
				}
			}

		}
		return null;
	}

	private static Vec3 fixupSpawnHeight(CollisionGetter level, BlockPos spawnPos) {
		BlockPos.MutableBlockPos mutablePos = spawnPos.mutable();

		while (!noCollisionNoLiquid(level, mutablePos) && mutablePos.getY() < level.getMaxY() - 1) {
			mutablePos.move(Direction.UP);
		}

		do {
			mutablePos.move(Direction.DOWN);
		} while (noCollisionNoLiquid(level, mutablePos) && mutablePos.getY() > 0);

		mutablePos.move(Direction.UP);
		return Vec3.atBottomCenterOf(mutablePos);
	}

	private static boolean noCollisionNoLiquid(CollisionGetter level, BlockPos pos) {
		return level.noCollision(null, EntityType.PLAYER.getDimensions().makeBoundingBox(pos.getBottomCenter()), true);
	}
}
