package quek.undergarden.item.tool;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGEntityTypes;

public class UnderbeanOnAStickItem extends Item {

	public UnderbeanOnAStickItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!level.isClientSide) {
			Entity entity = player.getVehicle();
			if (player.isPassenger() && entity instanceof ItemSteerable && entity.getType() == UGEntityTypes.DWELLER.get()) {
				ItemSteerable steerable = (ItemSteerable) entity;
				if (steerable.boost()) {
					itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
					if (itemstack.isEmpty()) {
						ItemStack stick = new ItemStack(Items.STICK);
						stick.setTag(itemstack.getTag());
						return InteractionResultHolder.success(stick);
					}

					return InteractionResultHolder.success(itemstack);
				}
			}

			player.awardStat(Stats.ITEM_USED.get(this));
		}
		return InteractionResultHolder.pass(itemstack);
	}
}