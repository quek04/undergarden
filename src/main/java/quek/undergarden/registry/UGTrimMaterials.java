package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import quek.undergarden.Undergarden;

public class UGTrimMaterials {
	public static final ResourceKey<TrimMaterial> CLOGGRUM = create("cloggrum");
	public static final ResourceKey<TrimMaterial> FROSTSTEEL = create("froststeel");
	public static final ResourceKey<TrimMaterial> UTHERIUM = create("utherium");
	public static final ResourceKey<TrimMaterial> REGALIUM = create("regalium");
	public static final ResourceKey<TrimMaterial> FORGOTTEN = create("forgotten");
	public static final ResourceKey<TrimMaterial> ROGDORIUM = create("rogdorium");

	private static ResourceKey<TrimMaterial> create(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, Undergarden.prefix(name));
	}

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, CLOGGRUM, Style.EMPTY.withColor(9863528), UGMaterialAssetGroups.CLOGGRUM);
		register(context, FROSTSTEEL, Style.EMPTY.withColor(9484768), UGMaterialAssetGroups.FROSTSTEEL);
		register(context, UTHERIUM, Style.EMPTY.withColor(14440522), UGMaterialAssetGroups.UTHERIUM);
		register(context, REGALIUM, Style.EMPTY.withColor(16570493), UGMaterialAssetGroups.REGALIUM);
		register(context, FORGOTTEN, Style.EMPTY.withColor(4769934), UGMaterialAssetGroups.FORGOTTEN);
		register(context, ROGDORIUM, Style.EMPTY.withColor(8498612), UGMaterialAssetGroups.ROGDORIUM);
	}

	private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Style hoverTextStyle, MaterialAssetGroup assetGroup) {
		TrimMaterial material = new TrimMaterial(assetGroup, Component.translatable(Util.makeDescriptionId("trim_material", trimKey.identifier())).withStyle(hoverTextStyle));
		context.register(trimKey, material);
	}
}
