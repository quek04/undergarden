package quek.undergarden.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.Minion;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;

public class CarvedGloomgourdBlock extends CarvedPumpkinBlock {

	@Nullable
	private BlockPattern minionBasePattern;
	@Nullable
	private BlockPattern minionPattern;

	public CarvedGloomgourdBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	private BlockPattern getMinionBasePattern() {
		if (this.minionBasePattern == null) {
			this.minionBasePattern = BlockPatternBuilder.start().aisle(" ", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(UGBlocks.FORGOTTEN_BLOCK.get()))).build();
		}

		return this.minionBasePattern;
	}

	private BlockPattern getMinionPattern() {
		if (this.minionPattern == null) {
			this.minionPattern = BlockPatternBuilder.start().aisle("^", "#").where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(UGBlocks.CARVED_GLOOMGOURD.get()))).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(UGBlocks.FORGOTTEN_BLOCK.get()))).build();
		}

		return this.minionPattern;
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!oldState.is(state.getBlock())) {
			this.trySpawnMinion(level, pos);
		}
	}

	@Override
	public boolean canSpawnGolem(LevelReader reader, BlockPos pos) {
		return this.getMinionBasePattern().find(reader, pos) != null;
	}

	private void trySpawnMinion(Level level, BlockPos pos) {
		BlockPattern.BlockPatternMatch minionPattern = this.getMinionPattern().find(level, pos);
		if (minionPattern != null) {
			clearPatternBlocks(level, minionPattern);

			Minion minionEntity = UGEntityTypes.MINION.get().create(level, EntitySpawnReason.TRIGGERED);
			BlockPos blockpos1 = minionPattern.getBlock(0, 2, 0).getPos();
			minionEntity.snapTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 1.0D, (double) blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
			level.addFreshEntity(minionEntity);

			for (ServerPlayer serverplayerentity : level.getEntitiesOfClass(ServerPlayer.class, minionEntity.getBoundingBox().inflate(5.0D))) {
				CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, minionEntity);
			}

			updatePatternBlocks(level, minionPattern);
		}
	}
}