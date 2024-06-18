package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.enums.BubbleColumnDirection;
import quek.undergarden.block.entity.SmogVentBlockEntity;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGEntityTypes;

import javax.annotation.Nullable;

public class SmogVentBlock extends Block implements EntityBlock {

	public SmogVentBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (entity.getType() != UGEntityTypes.SMOG_MOG.get() && !entity.fireImmune() && entity instanceof LivingEntity /*living && !EnchantmentHelper.hasFrostWalker(living)*/) {
			entity.hurt(level.damageSources().hotFloor(), 1.0F);
		}
		super.stepOn(level, pos, state, entity);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return UGBlockEntities.SMOG_VENT.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
		return blockEntity == UGBlockEntities.SMOG_VENT.get() ? SmogVentBlockEntity::tick : null;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BubbleColumnBlock.updateColumn(level, pos.above(), state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && facingState.is(Blocks.WATER)) {
			level.scheduleTick(currentPos, this, 20);
		}

		return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		level.scheduleTick(pos, this, 20);
	}

	@Override
	public BubbleColumnDirection getBubbleColumnDirection(BlockState state) {
		return BubbleColumnDirection.UPWARD;
	}
}