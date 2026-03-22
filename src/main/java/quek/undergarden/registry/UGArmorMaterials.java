package quek.undergarden.registry;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class UGArmorMaterials {


	public static final ArmorMaterial CLOGGRUM = new ArmorMaterial(20, makeDefense(1, 5, 6, 2, 6), 10, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, UGTags.Items.REPAIRS_CLOGGRUM_ARMOR, UGEquipmentAssets.CLOGGRUM);
	public static final ArmorMaterial FROSTSTEEL = new ArmorMaterial(25, makeDefense(2, 6, 7, 3, 10), 15, SoundEvents.ARMOR_EQUIP_GOLD, 4.0F, 0.05F, UGTags.Items.REPAIRS_FROSTSTEEL_ARMOR, UGEquipmentAssets.FROSTSTEEL);
	public static final ArmorMaterial UTHERIUM = new ArmorMaterial(30, makeDefense(3, 6, 8, 3, 15), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.0F, UGTags.Items.REPAIRS_UTHERIC_ARMOR, UGEquipmentAssets.UTHERIUM);
	public static final ArmorMaterial ANCIENT = new ArmorMaterial(37, makeDefense(2, 5, 6, 2, 11), 0, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, UGTags.Items.REPAIRS_ANCIENT_ARMOR, UGEquipmentAssets.ANCIENT);

	private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
		return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
	}
}