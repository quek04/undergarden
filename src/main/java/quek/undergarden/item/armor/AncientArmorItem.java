package quek.undergarden.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.registry.UGArmorMaterials;

public class AncientArmorItem extends ArmorItem {

	public AncientArmorItem(Type slot) {
		super(UGArmorMaterials.ANCIENT, slot, new Properties());
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
		if (slot == EquipmentSlot.LEGS) {
			return "undergarden:textures/armor/ancient_layer_2.png";
		} else {
			return "undergarden:textures/armor/ancient_layer_1.png";
		}
	}
}