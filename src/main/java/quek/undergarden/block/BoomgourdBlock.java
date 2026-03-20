package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.registry.UGSoundEvents;

public class BoomgourdBlock extends TntBlock {

	public BoomgourdBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity entity) {
		if (!level.isClientSide()) {
			Boomgourd boomgourd = new Boomgourd(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity);
			level.addFreshEntity(boomgourd);
			level.playSound(null, boomgourd.getX(), boomgourd.getY(), boomgourd.getZ(), UGSoundEvents.BOOMGOURD_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			level.gameEvent(entity, GameEvent.PRIME_FUSE, pos);
			return true;
		}
		return false;
	}

	@Override
	public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
		Boomgourd boomgourd = new Boomgourd(level, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, explosion.getIndirectSourceEntity());
		int fuse = boomgourd.getFuse();
		boomgourd.setFuse((short) (level.getRandom().nextInt(fuse / 4) + fuse / 8));
		level.addFreshEntity(boomgourd);
	}

	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT)) {
			return super.useItemOn(stack, state, level, pos, player, hand, result);
		} else {
			if (this.onCaughtFire(state, level, pos, result.getDirection(), player)) {
				level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
				Item item = stack.getItem();
				if (stack.isDamageableItem()) {
					stack.hurtAndBreak(1, player, hand);
				} else {
					stack.consume(1, player);
				}
				player.awardStat(Stats.ITEM_USED.get(item));
			} else if (level instanceof ServerLevel serverLevel && !serverLevel.getGameRules().get(GameRules.TNT_EXPLODES)) {
				player.sendOverlayMessage(Component.translatable("block.minecraft.tnt.disabled"));
				return InteractionResult.PASS;
			}
		}
		return InteractionResult.PASS;
	}
}