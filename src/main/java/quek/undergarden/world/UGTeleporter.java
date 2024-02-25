package quek.undergarden.world;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenConfig;
import quek.undergarden.block.UndergardenPortalBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGPointOfInterests;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public class UGTeleporter implements ITeleporter {

	protected final ServerLevel level;
	private final BlockState frame = !ForgeRegistries.BLOCKS.containsKey(ResourceLocation.tryParse(UndergardenConfig.Common.return_portal_frame_block_id.get())) ? Blocks.STONE_BRICKS.defaultBlockState() : ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(UndergardenConfig.Common.return_portal_frame_block_id.get())).defaultBlockState();

	public UGTeleporter(ServerLevel level) {
		this.level = level;
	}

	public Optional<BlockUtil.FoundRectangle> getExistingPortal(BlockPos pos) {
		PoiManager poiManager = this.level.getPoiManager();
		poiManager.ensureLoadedAndValid(this.level, pos, 64);
		Optional<PoiRecord> optional = poiManager.getInSquare((poiType) ->
				poiType.is(UGPointOfInterests.UNDERGARDEN_PORTAL.getKey()), pos, 64, PoiManager.Occupancy.ANY).sorted(Comparator.<PoiRecord>comparingDouble((poi) ->
				poi.getPos().distSqr(pos)).thenComparingInt((poi) ->
				poi.getPos().getY())).filter((poi) ->
				this.level.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS)).findFirst();
		return optional.map((poi) -> {
			BlockPos blockpos = poi.getPos();
			this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
			BlockState blockstate = this.level.getBlockState(blockpos);
			return BlockUtil.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (blockPos) ->
					this.level.getBlockState(blockPos) == blockstate);
		});
	}

	public Optional<BlockUtil.FoundRectangle> makePortal(BlockPos pos, Direction.Axis axis) {
		Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
		double d0 = -1.0D;
		BlockPos blockpos = null;
		double d1 = -1.0D;
		BlockPos blockpos1 = null;
		WorldBorder worldborder = this.level.getWorldBorder();
		int minHeight = Math.min(this.level.getMaxBuildHeight(), this.level.getLogicalHeight()) - 1;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

		for(BlockPos.MutableBlockPos checkPos : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
			int validStartHeight = Math.min(minHeight, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, checkPos.getX(), checkPos.getZ()));
			if (worldborder.isWithinBounds(checkPos) && worldborder.isWithinBounds(checkPos.move(direction, 1))) {
				checkPos.move(direction.getOpposite(), 1);

				for(int y = validStartHeight; y >= 0; --y) {
					checkPos.setY(y);
					if (this.canPortalReplaceBlock(checkPos)) {
						int i1;
						for(i1 = y; y > 0 && this.canPortalReplaceBlock(checkPos.move(Direction.DOWN)); --y) {
						}

						if (y + 4 <= minHeight) {
							int j1 = i1 - y;
							if (j1 <= 0 || j1 >= 3) {
								checkPos.setY(y);
								if (this.canHostFrame(checkPos, blockpos$mutableblockpos, direction, 0)) {
									double distSqr = pos.distSqr(checkPos);
									if (this.canHostFrame(checkPos, blockpos$mutableblockpos, direction, -1) && this.canHostFrame(checkPos, blockpos$mutableblockpos, direction, 1) && (d0 == -1.0D || d0 > distSqr)) {
										d0 = distSqr;
										blockpos = checkPos.immutable();
									}

									if (d0 == -1.0D && (d1 == -1.0D || d1 > distSqr)) {
										d1 = distSqr;
										blockpos1 = checkPos.immutable();
									}
								}
							}
						}
					}
				}
			}
		}

		if (d0 == -1.0D && d1 != -1.0D) {
			blockpos = blockpos1;
			d0 = d1;
		}

		if (d0 == -1.0D) {
			int k1 = Mth.clamp(pos.getY(), 32, 70);
			int i2 = minHeight - 9;
			if (i2 < k1) {
				return Optional.empty();
			}

			blockpos = (new BlockPos(pos.getX(), Mth.clamp(pos.getY(), k1, i2), pos.getZ())).immutable();
			Direction direction1 = direction.getClockWise();
			if (!worldborder.isWithinBounds(blockpos)) {
				return Optional.empty();
			}

			for (int i3 = -1; i3 < 2; ++i3) {
				for (int j3 = 0; j3 < 2; ++j3) {
					for (int k3 = -1; k3 < 3; ++k3) {
						BlockState blockstate1 = k3 < 0 ? this.frame : Blocks.AIR.defaultBlockState();
						blockpos$mutableblockpos.setWithOffset(blockpos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ());
						this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate1);
					}
				}
			}
		}

		for (int l1 = -1; l1 < 3; ++l1) {
			for (int j2 = -1; j2 < 4; ++j2) {
				if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
					blockpos$mutableblockpos.setWithOffset(blockpos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
					this.level.setBlock(blockpos$mutableblockpos, this.frame, 3);
				}
			}
		}

		BlockState blockstate = UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState().setValue(UndergardenPortalBlock.AXIS, axis);

		for (int k2 = 0; k2 < 2; ++k2) {
			for (int l2 = 0; l2 < 3; ++l2) {
				blockpos$mutableblockpos.setWithOffset(blockpos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
				this.level.setBlock(blockpos$mutableblockpos, blockstate, 18);
			}
		}

		return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
	}

	private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
		BlockState blockstate = this.level.getBlockState(pos);
		return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
	}

	private boolean canHostFrame(BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction direction, int offsetScale) {
		Direction checkDir = direction.getClockWise();

		for (int i = -1; i < 3; ++i) {
			for (int j = -1; j < 4; ++j) {
				offsetPos.setWithOffset(originalPos, direction.getStepX() * i + checkDir.getStepX() * offsetScale, j, direction.getStepZ() * i + checkDir.getStepZ() * offsetScale);
				if (j < 0 && !this.level.getBlockState(offsetPos).isSolid()) {
					return false;
				}

				if (j >= 0 && !this.canPortalReplaceBlock(offsetPos)) {
					return false;
				}
			}
		}

		return true;
	}

	@Nullable
	@Override
	public PortalInfo getPortalInfo(Entity entity, ServerLevel level, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		boolean destinationIsUG = level.dimension() == UGDimensions.UNDERGARDEN_LEVEL;
		if (entity.level().dimension() != UGDimensions.UNDERGARDEN_LEVEL && !destinationIsUG) {
			return null;
		} else {
			WorldBorder border = level.getWorldBorder();
			double coordinateDifference = DimensionType.getTeleportationScale(entity.level().dimensionType(), level.dimensionType());
			BlockPos pos = border.clampToBounds(entity.getX() * coordinateDifference, entity.getY(), entity.getZ() * coordinateDifference);
			return this.getOrMakePortal(entity, pos).map((result) -> {
				BlockState blockstate = entity.level().getBlockState(entity.portalEntrancePos);
				Direction.Axis axis;
				Vec3 vector3d;
				if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
					axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
					BlockUtil.FoundRectangle rectangle = BlockUtil.getLargestRectangleAround(entity.portalEntrancePos, axis, 21, Direction.Axis.Y, 21, blockPos -> entity.level().getBlockState(blockPos) == blockstate);
					vector3d = PortalShape.getRelativePosition(rectangle, axis, entity.position(), entity.getDimensions(entity.getPose()));
				} else {
					axis = Direction.Axis.X;
					vector3d = new Vec3(0.5D, 0.0D, 0.0D);
				}

				return PortalShape.createPortalInfo(level, result, axis, vector3d, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot());
			}).orElse(null);
		}
	}

	protected Optional<BlockUtil.FoundRectangle> getOrMakePortal(Entity entity, BlockPos pos) {
		Optional<BlockUtil.FoundRectangle> existingPortal = this.getExistingPortal(pos);
		if (existingPortal.isPresent()) {
			return existingPortal;
		} else {
			Direction.Axis portalAxis = this.level.getBlockState(entity.portalEntrancePos).getOptionalValue(UndergardenPortalBlock.AXIS).orElse(Direction.Axis.X);
			return this.makePortal(pos, portalAxis);
		}
	}

	@Override
	public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
		return false;
	}
}