package quek.undergarden.entity.monster.denizen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGPointOfInterests;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DenizenChillByCampfireGoal extends Goal {

	private final Denizen denizen;
	protected int nextStartTick;
	protected int tryTicks;
	private int maxStayTicks;
	@Nullable
	protected BlockPos restingPos = null;

	public DenizenChillByCampfireGoal(Denizen denizen) {
		this.denizen = denizen;
	}

	@Override
	public boolean canUse() {
		if (this.denizen.hasPose(Pose.SITTING) || this.denizen.getStareTarget() != null || this.denizen.getTarget() != null || this.denizen.getCampfire() != null || this.restingPos != null) return false;

		if (this.nextStartTick > 0) {
			--this.nextStartTick;
			return false;
		} else {
			this.nextStartTick = reducedTickDelay(200 + this.denizen.getRandom().nextInt(200));
			return this.findNearestCampfire((ServerLevel) this.denizen.level());
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.denizen.getStareTarget() != null || this.denizen.getTarget() != null || this.denizen.hurtTime > 0) return false;
		return this.tryTicks >= -this.maxStayTicks && this.tryTicks <= 1200 && this.isValidCampfire((ServerLevel) this.denizen.level());
	}

	@Override
	public void start() {
		this.denizen.getNavigation().moveTo(this.restingPos.getX() + 0.5D, this.restingPos.getY(), this.restingPos.getZ() + 0.5D, 0.65D);
		this.tryTicks = 0;
		this.maxStayTicks = this.denizen.getRandom().nextInt(this.denizen.getRandom().nextInt(1200) + 1200) + 1200;
	}

	@Override
	public void stop() {
		super.stop();
		this.denizen.resetCampfireLogic(getServerLevel(this.denizen));
		this.restingPos = null;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		BlockPos blockpos = this.restingPos;
		if (blockpos != null && !blockpos.closerToCenterThan(this.denizen.position(), 2.0D)) {
			if (this.denizen.hasPose(Pose.SITTING)) {
				this.denizen.setPose(Pose.STANDING);
			}
			++this.tryTicks;
			if (this.tryTicks % 40 == 0) {
				this.denizen.getNavigation().moveTo(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D, 0.65D);
			}
		} else {
			--this.tryTicks;
			this.denizen.getNavigation().stop();
			this.denizen.setPose(Pose.SITTING);
		}
	}

	protected boolean findNearestCampfire(ServerLevel level) {
		Predicate<Holder<PoiType>> predicate = holder -> holder.is(UGPointOfInterests.DENIZEN_RESTING_BLOCKS.getKey());
		Set<Pair<Holder<PoiType>, BlockPos>> set = level.getPoiManager().findAllClosestFirstWithType(predicate, pos -> true, this.denizen.blockPosition(), 20, PoiManager.Occupancy.HAS_SPACE).limit(3L).collect(Collectors.toSet());

		Path path = this.canReachCampfire(set);
		if (path != null && path.canReach()) {
			BlockPos blockpos = path.getTarget();
			if (level.getPoiManager().getType(blockpos).isPresent()) {
				this.denizen.setCampfire(level.getPoiManager().take(holder -> holder.is(UGPointOfInterests.DENIZEN_RESTING_BLOCKS.getKey()), (typeHolder, pos) -> pos.equals(blockpos), blockpos, 1).orElse(null));
				if (this.denizen.getCampfire() != null) {
					int tries = 0;
					do {
						BlockPos checkPos = this.denizen.getCampfire().offset(this.denizen.getRandom().nextIntBetweenInclusive(-2, 2), 0, this.denizen.getRandom().nextIntBetweenInclusive(-2, 2));
						//check 10 positions for a slab. If we dont get one just sit on the floor I guess
						BlockState state = level.getBlockState(checkPos);
						if (tries >= 10 || state.getBlock() instanceof SlabBlock) {
							this.restingPos = checkPos;
						}
						tries++;
					} while (this.restingPos == null || this.restingPos.equals(this.denizen.getCampfire()));
					return true;
				}
			}
		}

		return false;
	}

	@Nullable
	private Path canReachCampfire(Set<Pair<Holder<PoiType>, BlockPos>> positions) {
		if (positions.isEmpty()) {
			return null;
		} else {
			Set<BlockPos> set = new HashSet<>();
			int i = 1;

			for(Pair<Holder<PoiType>, BlockPos> pair : positions) {
				i = Math.max(i, pair.getFirst().value().validRange());
				set.add(pair.getSecond());
			}

			return this.denizen.getNavigation().createPath(set, i);
		}
	}

	private boolean isValidCampfire(ServerLevel level) {
		if (this.denizen.getCampfire() == null) return false;
		Optional<Holder<PoiType>> maybeCampfire = level.getPoiManager().getType(this.denizen.getCampfire());
		return maybeCampfire.isPresent() && maybeCampfire.get().is(UGPointOfInterests.DENIZEN_RESTING_BLOCKS.getKey());
	}
}
