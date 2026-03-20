package quek.undergarden.block.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

import java.util.Optional;
import java.util.function.Predicate;

public class UndergardenPortalShape {
	private static final int MIN_WIDTH = 1;
	public static final int MAX_WIDTH = 21;
	private static final int MIN_HEIGHT = 2;
	public static final int MAX_HEIGHT = 21;
	private static final BlockBehaviour.StatePredicate FRAME = (state, getter, pos) -> state.is(UGTags.Blocks.PORTAL_FRAME_BLOCKS);
	private final Direction.Axis axis;
	private final Direction rightDir;
	private final int numPortalBlocks;
	private final BlockPos bottomLeft;
	private final int height;
	private final int width;

	public UndergardenPortalShape(Direction.Axis axis, int portalBlockCount, Direction rightDir, BlockPos bottomLeft, int width, int height) {
		this.axis = axis;
		this.numPortalBlocks = portalBlockCount;
		this.rightDir = rightDir;
		this.bottomLeft = bottomLeft;
		this.width = width;
		this.height = height;
	}

	public static Optional<UndergardenPortalShape> findEmptyPortalShape(LevelAccessor level, BlockPos pos, Direction.Axis preferredAxis) {
		return findPortalShape(level, pos, shape -> shape.isValid() && shape.numPortalBlocks == 0, preferredAxis);
	}

	public static Optional<UndergardenPortalShape> findPortalShape(LevelAccessor level, BlockPos pos, Predicate<UndergardenPortalShape> isValid, Direction.Axis preferredAxis) {
		Optional<UndergardenPortalShape> firstAxis = Optional.of(findAnyShape(level, pos, preferredAxis)).filter(isValid);
		if (firstAxis.isPresent()) {
			return firstAxis;
		} else {
			Direction.Axis otherAxis = preferredAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
			return Optional.of(findAnyShape(level, pos, otherAxis)).filter(isValid);
		}
	}

	public static UndergardenPortalShape findAnyShape(BlockGetter level, BlockPos pos, Direction.Axis axis) {
		Direction rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
		BlockPos bottomLeft = calculateBottomLeft(level, rightDir, pos);
		if (bottomLeft == null) {
			return new UndergardenPortalShape(axis, 0, rightDir, pos, 0, 0);
		} else {
			int width = calculateWidth(level, bottomLeft, rightDir);
			if (width == 0) {
				return new UndergardenPortalShape(axis, 0, rightDir, bottomLeft, 0, 0);
			} else {
				MutableInt portalBlockCountOutput = new MutableInt();
				int height = calculateHeight(level, bottomLeft, rightDir, width, portalBlockCountOutput);
				return new UndergardenPortalShape(axis, portalBlockCountOutput.intValue(), rightDir, bottomLeft, width, height);
			}
		}
	}

	private static @Nullable BlockPos calculateBottomLeft(BlockGetter level, Direction rightDir, BlockPos pos) {
		int minY = Math.max(level.getMinY(), pos.getY() - 21);

		while (pos.getY() > minY && isEmpty(level.getBlockState(pos.below()))) {
			pos = pos.below();
		}

		Direction leftDir = rightDir.getOpposite();
		int edge = getDistanceUntilEdgeAboveFrame(level, pos, leftDir) - 1;
		return edge < 0 ? null : pos.relative(leftDir, edge);
	}

	private static int calculateWidth(BlockGetter level, BlockPos bottomLeft, Direction rightDir) {
		int width = getDistanceUntilEdgeAboveFrame(level, bottomLeft, rightDir);
		return width >= MIN_WIDTH && width <= MAX_WIDTH ? width : 0;
	}

	private static int getDistanceUntilEdgeAboveFrame(BlockGetter level, BlockPos pos, Direction direction) {
		BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

		for (int width = 0; width <= MAX_WIDTH; width++) {
			blockPos.set(pos).move(direction, width);
			BlockState blockState = level.getBlockState(blockPos);
			if (!isEmpty(blockState)) {
				if (FRAME.test(blockState, level, blockPos)) {
					return width;
				}
				break;
			}

			BlockState belowState = level.getBlockState(blockPos.move(Direction.DOWN));
			if (!FRAME.test(belowState, level, blockPos)) {
				break;
			}
		}

		return 0;
	}

	private static int calculateHeight(BlockGetter level, BlockPos bottomLeft, Direction rightDir, int width, MutableInt portalBlockCount) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		int height = getDistanceUntilTop(level, bottomLeft, rightDir, pos, width, portalBlockCount);
		return height >= MIN_HEIGHT && height <= MAX_HEIGHT && hasTopFrame(level, bottomLeft, rightDir, pos, width, height) ? height : 0;
	}

	private static boolean hasTopFrame(BlockGetter level, BlockPos bottomLeft, Direction rightDir, BlockPos.MutableBlockPos pos, int width, int height) {
		for (int i = 0; i < width; i++) {
			BlockPos.MutableBlockPos framePos = pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, i);
			if (!FRAME.test(level.getBlockState(framePos), level, framePos)) {
				return false;
			}
		}

		return true;
	}

	private static int getDistanceUntilTop(
		BlockGetter level, BlockPos bottomLeft, Direction rightDir, BlockPos.MutableBlockPos pos, int width, MutableInt portalBlockCount
	) {
		for (int height = 0; height < MAX_HEIGHT; height++) {
			pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, -1);
			if (!FRAME.test(level.getBlockState(pos), level, pos)) {
				return height;
			}

			pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, width);
			if (!FRAME.test(level.getBlockState(pos), level, pos)) {
				return height;
			}

			for (int i = 0; i < width; i++) {
				pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, i);
				BlockState state = level.getBlockState(pos);
				if (!isEmpty(state)) {
					return height;
				}

				if (state.is(UGBlocks.UNDERGARDEN_PORTAL)) {
					portalBlockCount.increment();
				}
			}
		}

		return MAX_HEIGHT;
	}

	private static boolean isEmpty(BlockState state) {
		return state.isAir() || state.is(UGBlocks.UNDERGARDEN_PORTAL);
	}

	public boolean isValid() {
		return this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;
	}

	public void createPortalBlocks(LevelAccessor level) {
		BlockState portalState = UGBlocks.UNDERGARDEN_PORTAL.get().defaultBlockState().setValue(UndergardenPortalBlock.AXIS, this.axis);
		BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1))
			.forEach(pos -> level.setBlock(pos, portalState, 18));
	}

	public boolean isComplete() {
		return this.isValid() && this.numPortalBlocks == this.width * this.height;
	}
}
