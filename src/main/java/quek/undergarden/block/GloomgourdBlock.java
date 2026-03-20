package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGBuiltinLootTables;

public class GloomgourdBlock extends PumpkinBlock {

	public GloomgourdBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!stack.canPerformAction(ItemAbilities.SHEARS_CARVE)) {
			return super.useItemOn(stack, state, level, pos, player, hand, result);
		} else if (level instanceof ServerLevel serverLevel) {
			Direction clickedDirection = result.getDirection();
			Direction direction = clickedDirection.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : clickedDirection;
			dropFromBlockInteractLootTable(
				serverLevel,
				UGBuiltinLootTables.CARVE_GLOOMGOURD,
				state,
				level.getBlockEntity(pos),
				stack,
				player,
				(ignored, seeds) -> {
					ItemEntity entity = new ItemEntity(
						level, pos.getX() + 0.5 + direction.getStepX() * 0.65, pos.getY() + 0.1, pos.getZ() + 0.5 + direction.getStepZ() * 0.65, seeds
					);
					RandomSource random = level.getRandom();
					entity.setDeltaMovement(
						0.05 * direction.getStepX() + random.nextDouble() * 0.02, 0.05, 0.05 * direction.getStepZ() + random.nextDouble() * 0.02
					);
					level.addFreshEntity(entity);
				}
			);

			level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, UGBlocks.CARVED_GLOOMGOURD.get().defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction), 11);
			stack.hurtAndBreak(1, player, hand);
			level.gameEvent(player, GameEvent.SHEAR, pos);
			player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
			return InteractionResult.CONSUME;
		}
		return InteractionResult.SUCCESS;
	}
}