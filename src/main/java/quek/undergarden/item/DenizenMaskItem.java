package quek.undergarden.item;

import net.minecraft.world.item.ArmorItem;
import quek.undergarden.registry.UGArmorMaterials;

public class DenizenMaskItem extends ArmorItem {
	public DenizenMaskItem(Properties properties) {
		super(UGArmorMaterials.DENIZEN_MASK, Type.HELMET, properties);
	}
}