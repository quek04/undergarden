package quek.undergarden.item.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import quek.undergarden.item.tool.slingshot.SlingshotItem;

public class LongevityEnchantment extends Enchantment {

	public LongevityEnchantment(EnchantmentDefinition definition) {
		super(definition);
	}

	/*@Override
	public int getMinCost(int level) {
		return 1 + (level - 1) * 10;
	}

	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}*/

	@Override
	public boolean canEnchant(ItemStack stack) {
		return stack.getItem() instanceof SlingshotItem || super.canEnchant(stack);
	}

	@Override
	public boolean isTradeable() {
		return false;
	}
}