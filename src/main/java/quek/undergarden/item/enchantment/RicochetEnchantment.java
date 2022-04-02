package quek.undergarden.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import quek.undergarden.item.tool.slingshot.SlingshotItem;

public class RicochetEnchantment extends Enchantment {

    public RicochetEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof SlingshotItem || super.canEnchant(stack);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return !(other instanceof SelfSlingEnchantment) && super.checkCompatibility(other);
    }
}
