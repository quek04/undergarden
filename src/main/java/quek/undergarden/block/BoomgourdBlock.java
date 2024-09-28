package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;

public class BoomgourdBlock extends TntBlock {

	public BoomgourdBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity entity) {
		if (!level.isClientSide()) {
			Boomgourd boomgourd = new Boomgourd(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity);
			level.addFreshEntity(boomgourd);
			level.playSound(null, boomgourd.getX(), boomgourd.getY(), boomgourd.getZ(), UGSoundEvents.BOOMGOURD_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			level.gameEvent(entity, GameEvent.PRIME_FUSE, pos);
		}
	}

	@Override
	public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
		if (!level.isClientSide()) {
			Boomgourd boomgourd = new Boomgourd(level, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, explosion.getIndirectSourceEntity());
			int fuse = boomgourd.getFuse();
			boomgourd.setFuse((short) (level.getRandom().nextInt(fuse / 4) + fuse / 8));
			level.addFreshEntity(boomgourd);
		}
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE) && !stack.is(UGItems.DITCHBULB_PASTE.get())) {
			return super.useItemOn(stack, state, level, pos, player, hand, result);
		} else {
			onCaughtFire(state, level, pos, result.getDirection(), player);
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			Item item = stack.getItem();
			if (!player.isCreative()) {
				if (stack.is(Items.FLINT_AND_STEEL)) {
					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
				} else {
					stack.shrink(1);
				}
			}

			player.awardStat(Stats.ITEM_USED.get(item));
			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		}
	}
}