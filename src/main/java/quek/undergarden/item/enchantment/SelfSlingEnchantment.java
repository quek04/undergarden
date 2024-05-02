package quek.undergarden.item.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import quek.undergarden.item.tool.slingshot.SlingshotItem;

public class SelfSlingEnchantment extends Enchantment {

	public SelfSlingEnchantment(EnchantmentDefinition definition) {
		super(definition);
	}

	/*@Override
	public int getMinCost(int level) {
		return 20;
	}

	@Override
	public int getMaxCost(int level) {
		return 50;
	}*/

	@Override
	public boolean canEnchant(ItemStack stack) {
		return stack.getItem() instanceof SlingshotItem || super.canEnchant(stack);
	}

	@Override
	public boolean isTradeable() {
		return false;
	}

	@Override
	public boolean checkCompatibility(Enchantment other) {
		return !(other instanceof RicochetEnchantment) && super.checkCompatibility(other);
	}
}