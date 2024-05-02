package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
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
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

public class GloomgourdBlock extends PumpkinBlock {

	public GloomgourdBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!stack.canPerformAction(net.neoforged.neoforge.common.ToolActions.SHEARS_CARVE)) {
			return super.useItemOn(stack, state, level, pos, player, hand, result);
		} else if (level.isClientSide) {
			return ItemInteractionResult.sidedSuccess(true);
		} else {
			Direction direction = result.getDirection();
			Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
			level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, UGBlocks.CARVED_GLOOMGOURD.get().defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction1), 11);
			ItemEntity itementity = new ItemEntity(
				level,
				(double)pos.getX() + 0.5 + (double)direction1.getStepX() * 0.65,
				(double)pos.getY() + 0.1,
				(double)pos.getZ() + 0.5 + (double)direction1.getStepZ() * 0.65,
				new ItemStack(UGItems.GLOOMGOURD_SEEDS.get(), 4)
			);
			itementity.setDeltaMovement(
				0.05 * (double)direction1.getStepX() + level.random.nextDouble() * 0.02,
				0.05,
				0.05 * (double)direction1.getStepZ() + level.random.nextDouble() * 0.02
			);
			level.addFreshEntity(itementity);
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			level.gameEvent(player, GameEvent.SHEAR, pos);
			player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
			return ItemInteractionResult.sidedSuccess(false);
		}
	}
}