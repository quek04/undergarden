package quek.undergarden.item.tool;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.LivingEntity;
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
		ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide()) {
			Entity entity = player.getControlledVehicle();
			if (player.isPassenger() && entity instanceof ItemSteerable itemsteerable && entity.getType() == UGEntityTypes.DWELLER.get() && itemsteerable.boost()) {
				EquipmentSlot equipmentslot = LivingEntity.getSlotForHand(hand);
				ItemStack itemstack1 = stack.hurtAndConvertOnBreak(1, Items.STICK, player, equipmentslot);
				return InteractionResultHolder.success(itemstack1);
			}

			player.awardStat(Stats.ITEM_USED.get(this));
		}
		return InteractionResultHolder.pass(stack);
	}
}