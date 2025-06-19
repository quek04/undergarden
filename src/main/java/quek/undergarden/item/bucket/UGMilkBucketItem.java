package quek.undergarden.item.bucket;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGItems;

public class UGMilkBucketItem extends MilkBucketItem {

	public UGMilkBucketItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
		if (entityLiving instanceof ServerPlayer serverplayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
			serverplayer.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!level.isClientSide) {
			entityLiving.removeEffectsCuredBy(net.neoforged.neoforge.common.EffectCures.MILK);
		}

		if (entityLiving instanceof Player player) {
			return ItemUtils.createFilledResult(stack, player, new ItemStack(UGItems.CLOGGRUM_BUCKET.get()), false);
		} else {
			stack.consume(1, entityLiving);
			return stack;
		}
	}
}
