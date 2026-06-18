package quek.undergarden.registry;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import quek.undergarden.Undergarden;

import java.util.function.BiConsumer;

public class UGEquipmentAssets {

	public static final ResourceKey<EquipmentAsset> CLOGGRUM = createId("cloggrum");
	public static final ResourceKey<EquipmentAsset> FROSTSTEEL = createId("froststeel");
	public static final ResourceKey<EquipmentAsset> UTHERIUM = createId("utherium");
	public static final ResourceKey<EquipmentAsset> ANCIENT = createId("ancient");
	public static final ResourceKey<EquipmentAsset> DENIZEN_MASK = createId("denizen_mask");

	static ResourceKey<EquipmentAsset> createId(String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, Undergarden.prefix(name));
	}

	public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
		consumer.accept(CLOGGRUM, EquipmentClientInfo.builder().addHumanoidLayers(Undergarden.prefix("cloggrum"), false).build());
		consumer.accept(FROSTSTEEL, EquipmentClientInfo.builder().addHumanoidLayers(Undergarden.prefix("froststeel"), false).build());
		consumer.accept(UTHERIUM, EquipmentClientInfo.builder().addHumanoidLayers(Undergarden.prefix("utherium"), false).build());
		consumer.accept(ANCIENT, EquipmentClientInfo.builder().addHumanoidLayers(Undergarden.prefix("ancient"), false).build());
		consumer.accept(DENIZEN_MASK, EquipmentClientInfo.builder().addMainHumanoidLayer(Undergarden.prefix("denizen_mask"), false).build());

		EquipmentClientInfo.Layer saddleLayer = new EquipmentClientInfo.Layer(Undergarden.prefix("saddle"));
		consumer.accept(EquipmentAssets.SADDLE, EquipmentClientInfo.builder().addLayers(EquipmentClientInfo.LayerType.valueOf("UNDERGARDEN_DWELLER_SADDLE"), saddleLayer).build());
	}
}
