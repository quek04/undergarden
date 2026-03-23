package quek.undergarden.datagen.assets;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import quek.undergarden.registry.UGEquipmentAssets;

import java.util.function.BiConsumer;

public class UGEquipmentAssetProvider extends EquipmentAssetProvider {

	public UGEquipmentAssetProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
		UGEquipmentAssets.bootstrap(output);
	}
}
