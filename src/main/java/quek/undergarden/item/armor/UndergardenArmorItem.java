package quek.undergarden.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenArmorItem extends ArmorItem {

    public UndergardenArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot) {
        super(materialIn, slot, new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return "undergarden:textures/models/armor/" + material.getName() + "_layer_2.png";
        } else {
            return "undergarden:textures/models/armor/" + material.getName() + "_layer_1.png";
        }
    }

}
