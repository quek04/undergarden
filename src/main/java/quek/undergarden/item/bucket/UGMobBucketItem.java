package quek.undergarden.item.bucket;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.registry.UGItems;

public class UGMobBucketItem extends MobBucketItem {

	public UGMobBucketItem(EntityType<?> type, Fluid content, SoundEvent emptySound, Properties properties) {
		super(type, content, emptySound, properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockHitResult blockhitresult = getPlayerPOVHitResult(
			level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE
		);
		if (blockhitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			BlockPos blockpos = blockhitresult.getBlockPos();
			Direction direction = blockhitresult.getDirection();
			BlockPos blockpos1 = blockpos.relative(direction);
			if (!level.mayInteract(player, blockpos) || !player.mayUseItemAt(blockpos1, direction, itemstack)) {
				return InteractionResultHolder.fail(itemstack);
			} else if (this.content == Fluids.EMPTY) {
				BlockState blockstate1 = level.getBlockState(blockpos);
				if (blockstate1.getBlock() instanceof BucketPickup bucketpickup) {
					ItemStack itemstack3 = bucketpickup.pickupBlock(player, level, blockpos, blockstate1);
					if (!itemstack3.isEmpty()) {
						player.awardStat(Stats.ITEM_USED.get(this));
						bucketpickup.getPickupSound(blockstate1).ifPresent(p_150709_ -> player.playSound(p_150709_, 1.0F, 1.0F));
						level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
						ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack3);
						if (!level.isClientSide) {
							CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, itemstack3);
						}

						return InteractionResultHolder.sidedSuccess(itemstack2, level.isClientSide());
					}
				}

				return InteractionResultHolder.fail(itemstack);
			} else {
				BlockState blockstate = level.getBlockState(blockpos);
				BlockPos blockpos2 = canBlockContainFluid(player, level, blockpos, blockstate) ? blockpos : blockpos1;
				if (this.emptyContents(player, level, blockpos2, blockhitresult, itemstack)) {
					this.checkExtraContent(player, level, itemstack, blockpos2);
					if (player instanceof ServerPlayer) {
						CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos2, itemstack);
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, getEmptySuccessItem(itemstack, player));
					return InteractionResultHolder.sidedSuccess(itemstack1, level.isClientSide());
				} else {
					return InteractionResultHolder.fail(itemstack);
				}
			}
		}
	}

	public static ItemStack getEmptySuccessItem(ItemStack bucketStack, Player player) {
		return !player.hasInfiniteMaterials() ? new ItemStack(UGItems.CLOGGRUM_BUCKET.get()) : bucketStack;
	}
}