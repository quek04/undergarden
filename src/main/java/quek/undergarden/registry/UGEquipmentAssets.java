package quek.undergarden.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import quek.undergarden.Undergarden;

public class UGEquipmentAssets {

	public static final ResourceKey<EquipmentAsset> CLOGGRUM = createId("cloggrum");
	public static final ResourceKey<EquipmentAsset> FROSTSTEEL = createId("froststeel");
	public static final ResourceKey<EquipmentAsset> UTHERIUM = createId("utherium");
	public static final ResourceKey<EquipmentAsset> ANCIENT = createId("ancient");
	public static final ResourceKey<EquipmentAsset> DENIZEN_MASK = createId("denizen_mask");

	static ResourceKey<EquipmentAsset> createId(String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, Undergarden.prefix(name));
	}

}
