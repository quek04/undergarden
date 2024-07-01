package quek.undergarden.item.tool;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import quek.undergarden.registry.UGItems;

public class UGShieldItem extends ShieldItem {

	public UGShieldItem() {
		super(new Properties()
				.stacksTo(1)
				.durability(672)
		);
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility action) {
		return action == ItemAbilities.SHIELD_BLOCK;
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return Ingredient.of(UGItems.CLOGGRUM_INGOT.get()).test(repair);
	}
}