package quek.undergarden.block.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Optional;

public class UndergardenPortalBlock extends Block implements Portal {

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public UndergardenPortalBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(AXIS, Direction.Axis.X));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(AXIS) == Direction.Axis.X ? X_AABB : Z_AABB;
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbor, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		Direction.Axis updateAxis = directionToNeighbor.getAxis();
		Direction.Axis axis = state.getValue(AXIS);
		boolean wrongAxis = axis != updateAxis && updateAxis.isHorizontal();
		return !wrongAxis && neighborState.getBlock() != this && !UndergardenPortalShape.findAnyShape(level, pos, axis).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbor, neighborPos, neighborState, random);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		if (entity.canUsePortal(false)) {
			entity.setAsInsidePortal(this, pos);
		}
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (random.nextInt(100) == 0) {
			level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, UGSoundEvents.UNDERGARDEN_PORTAL_AMBIENT.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
		}

		for (int i = 0; i < 4; ++i) {
			double x = (double) pos.getX() + random.nextDouble();
			double y = (double) pos.getY() + random.nextDouble();
			double z = (double) pos.getZ() + random.nextDouble();
			double xSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
			double ySpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
			double zSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
			int j = random.nextInt(2) * 2 - 1;
			if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
				x = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				xSpeed = random.nextFloat() * 2.0F * (float) j;
			} else {
				z = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
				zSpeed = random.nextFloat() * 2.0F * (float) j;
			}

			level.addParticle(UGParticleTypes.UNDERGARDEN_PORTAL.get(), x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
		return ItemStack.EMPTY;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		switch (rot) {
			case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> {
				return switch (state.getValue(AXIS)) {
					case Z -> state.setValue(AXIS, Direction.Axis.X);
					case X -> state.setValue(AXIS, Direction.Axis.Z);
					default -> state;
				};
			}
			default -> {
				return state;
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	@Override
	public int getPortalTransitionTime(ServerLevel level, Entity entity) {
		return entity instanceof Player player ? Math.max(0,
			level.getGameRules()
				.get(player.getAbilities().invulnerable
					? GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY
					: GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY
				)
		) : 0;
	}

	@Override
	public @Nullable TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
		ResourceKey<Level> resourcekey = level.dimension() == UGDimensions.UNDERGARDEN_LEVEL ? Level.OVERWORLD : UGDimensions.UNDERGARDEN_LEVEL;
		ServerLevel destination = level.getServer().getLevel(resourcekey);
		if (destination == null) {
			return null;
		} else {
			boolean flag = destination.dimension() == UGDimensions.UNDERGARDEN_LEVEL;
			WorldBorder worldborder = destination.getWorldBorder();
			double d0 = DimensionType.getTeleportationScale(level.dimensionType(), destination.dimensionType());
			BlockPos blockpos = worldborder.clampToBounds(entity.getX() * d0, entity.getY(), entity.getZ() * d0);
			return this.getExitPortal(destination, entity, pos, blockpos, flag, worldborder);
		}
	}

	@Nullable
	private TeleportTransition getExitPortal(ServerLevel level, Entity entity, BlockPos pos, BlockPos exitPos, boolean isUndergarden, WorldBorder worldBorder) {
		Optional<BlockPos> potentialPortalSpot = UndergardenPortalForcer.findClosestPortalPosition(level, exitPos, isUndergarden, worldBorder);
		BlockUtil.FoundRectangle rect;
		TeleportTransition.PostTeleportTransition post;
		if (potentialPortalSpot.isPresent()) {
			BlockPos blockpos = potentialPortalSpot.get();
			BlockState blockstate = level.getBlockState(blockpos);
			rect = BlockUtil.getLargestRectangleAround(
				blockpos,
				blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS),
				21,
				Direction.Axis.Y,
				21,
				p_351970_ -> level.getBlockState(p_351970_) == blockstate
			);
			post = UndergardenPortalForcer.PLAY_PORTAL_SOUND.then(traveler -> traveler.placePortalTicket(blockpos));
		} else {
			Direction.Axis axis = entity.level().getBlockState(pos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
			Optional<BlockUtil.FoundRectangle> createdPortal = UndergardenPortalForcer.createPortal(level, exitPos, axis);
			if (createdPortal.isEmpty()) {
				Undergarden.LOGGER.error("Unable to create a portal, likely target out of worldborder");
				return null;
			}

			rect = createdPortal.get();
			post = UndergardenPortalForcer.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
		}

		return NetherPortalBlock.getDimensionTransitionFromExit(entity, pos, rect, level, post);
	}
}