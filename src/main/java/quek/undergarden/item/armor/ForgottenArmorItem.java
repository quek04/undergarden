package quek.undergarden.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.registry.UGArmors;

public class ForgottenArmorItem extends ArmorItem {

    public ForgottenArmorItem(EquipmentSlot slot) {
        super(UGArmors.FORGOTTEN, slot, new Properties());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        if (slot == EquipmentSlot.LEGS) {
            return "undergarden:textures/armor/forgotten_layer_2.png";
        } else {
            return "undergarden:textures/armor/forgotten_layer_1.png";
        }
    }
}