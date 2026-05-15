package quek.undergarden.registry;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class UGArmorMaterials {


	public static final ArmorMaterial CLOGGRUM = new ArmorMaterial(20, makeDefense(2, 5, 6, 2, 6), 6, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, UGTags.Items.REPAIRS_CLOGGRUM_ARMOR, UGEquipmentAssets.CLOGGRUM);
	public static final ArmorMaterial FROSTSTEEL = new ArmorMaterial(40, makeDefense(4, 7, 9, 4, 19), 18, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.05F, UGTags.Items.REPAIRS_FROSTSTEEL_ARMOR, UGEquipmentAssets.FROSTSTEEL);
	public static final ArmorMaterial UTHERIUM = new ArmorMaterial(30, makeDefense(3, 6, 8, 3, 15), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.0F, UGTags.Items.REPAIRS_UTHERIC_ARMOR, UGEquipmentAssets.UTHERIUM);
	public static final ArmorMaterial ANCIENT = new ArmorMaterial(20, makeDefense(2, 5, 6, 2, 6), 12, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.05F, UGTags.Items.REPAIRS_ANCIENT_ARMOR, UGEquipmentAssets.ANCIENT);

	private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
		return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
	}
}