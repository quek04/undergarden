package quek.undergarden.item.tool;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class UnderbeanOnAStickItem<T extends Entity & ItemSteerable> extends Item {

	private final EntityType<T> canInteractWith;
	private final int consumeItemDamage;

	public UnderbeanOnAStickItem(EntityType<T> canInteractWith, int consumeItemDamage, Item.Properties properties) {
		super(properties);
		this.canInteractWith = canInteractWith;
		this.consumeItemDamage = consumeItemDamage;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (level.isClientSide()) {
			return InteractionResult.PASS;
		} else {
			Entity vehicle = player.getControlledVehicle();
			if (player.isPassenger() && vehicle instanceof ItemSteerable steerable && vehicle.is(this.canInteractWith) && steerable.boost()) {
				EquipmentSlot slot = hand.asEquipmentSlot();
				ItemStack result = itemStack.hurtAndConvertOnBreak(this.consumeItemDamage, Items.STICK, player, slot);
				return InteractionResult.SUCCESS_SERVER.heldItemTransformedTo(result);
			} else {
				player.awardStat(Stats.ITEM_USED.get(this));
				return InteractionResult.PASS;
			}
		}
	}
}