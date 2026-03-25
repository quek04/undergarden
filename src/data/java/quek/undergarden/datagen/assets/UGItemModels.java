package quek.undergarden.datagen.assets;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.renderer.special.TridentSpecialRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.neoforged.neoforge.registries.DeferredHolder;
import quek.undergarden.client.render.item.CloggrumShieldSpecialRenderer;
import quek.undergarden.client.render.item.JavelinSpecialRenderer;
import quek.undergarden.registry.UGEquipmentAssets;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGMaterialAssetGroups;
import quek.undergarden.registry.UGTrimMaterials;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class UGItemModels extends ItemModelGenerators {

	private static final List<TrimMaterialData> EX_TRIM_MATERIAL_MODELS = List.of(
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.QUARTZ, TrimMaterials.QUARTZ),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.IRON, TrimMaterials.IRON),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.NETHERITE, TrimMaterials.NETHERITE),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.REDSTONE, TrimMaterials.REDSTONE),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.COPPER, TrimMaterials.COPPER),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.GOLD, TrimMaterials.GOLD),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.EMERALD, TrimMaterials.EMERALD),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.DIAMOND, TrimMaterials.DIAMOND),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.LAPIS, TrimMaterials.LAPIS),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.AMETHYST, TrimMaterials.AMETHYST),
		new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.RESIN, TrimMaterials.RESIN),
		new ItemModelGenerators.TrimMaterialData(UGMaterialAssetGroups.CLOGGRUM, UGTrimMaterials.CLOGGRUM),
		new ItemModelGenerators.TrimMaterialData(UGMaterialAssetGroups.FROSTSTEEL, UGTrimMaterials.FROSTSTEEL),
		new ItemModelGenerators.TrimMaterialData(UGMaterialAssetGroups.UTHERIUM, UGTrimMaterials.UTHERIUM),
		new ItemModelGenerators.TrimMaterialData(UGMaterialAssetGroups.REGALIUM, UGTrimMaterials.REGALIUM),
		new ItemModelGenerators.TrimMaterialData(UGMaterialAssetGroups.FORGOTTEN, UGTrimMaterials.FORGOTTEN),
		new ItemModelGenerators.TrimMaterialData(UGMaterialAssetGroups.ROGDORIUM, UGTrimMaterials.ROGDORIUM));

	public UGItemModels(ItemModelOutput output, BiConsumer<Identifier, ModelInstance> modelOutput) {
		super(output, modelOutput);
	}

	@Override
	public void run() {
		for (DeferredHolder<Item, ? extends Item> egg : UGItems.ITEMS.getEntries().stream().filter(holder -> holder.get() instanceof SpawnEggItem).toList()) {
			this.generateFlatItem(egg.get(), ModelTemplates.FLAT_ITEM);
		}

		this.generateFlatItem(UGItems.MAMMOTH_DISC.get(), ModelTemplates.MUSIC_DISC);
		this.generateFlatItem(UGItems.LIMAX_MAXIMUS_DISC.get(), ModelTemplates.MUSIC_DISC);
		this.generateFlatItem(UGItems.GLOOMPER_ANTHEM_DISC.get(), ModelTemplates.MUSIC_DISC);
		this.generateFlatItem(UGItems.RELICT_DISC.get(), ModelTemplates.MUSIC_DISC);
		this.generateFlatItem(UGItems.GLOOMPER_SECRET_DISC.get(), ModelTemplates.MUSIC_DISC);
		this.generateFlatItem(UGItems.CATALYST.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.CRUMBLING_CATALYST.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.DEPTHROCK_PEBBLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.TWISTYTWIG.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.CLOGGRUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.CLOGGRUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.FROSTSTEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.FROSTSTEEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.UTHERIC_SHARD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.UTHERIUM_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.REGALIUM_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_INGOT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_NUGGET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.BRUTE_TUSK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.MOGMOSS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.GOO_BALL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.ROTTEN_BLISTERBERRY.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.BLISTERBOMB.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.DROOPFRUIT.get(), ModelTemplates.FLAT_ITEM);
		this.generateBattleaxe(UGItems.CLOGGRUM_BATTLEAXE.get());
		this.generateFlatItem(UGItems.CLOGGRUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.CLOGGRUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.CLOGGRUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.CLOGGRUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.CLOGGRUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateExpandedTrimmableItem(UGItems.CLOGGRUM_HELMET.get(), UGEquipmentAssets.CLOGGRUM, ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateExpandedTrimmableItem(UGItems.CLOGGRUM_CHESTPLATE.get(), UGEquipmentAssets.CLOGGRUM, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateExpandedTrimmableItem(UGItems.CLOGGRUM_LEGGINGS.get(), UGEquipmentAssets.CLOGGRUM, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateExpandedTrimmableItem(UGItems.CLOGGRUM_BOOTS.get(), UGEquipmentAssets.CLOGGRUM, ItemModelGenerators.TRIM_PREFIX_BOOTS);
		this.generateExpandedTrimmableItem(UGItems.FROSTSTEEL_HELMET.get(), UGEquipmentAssets.FROSTSTEEL, ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateExpandedTrimmableItem(UGItems.FROSTSTEEL_CHESTPLATE.get(), UGEquipmentAssets.FROSTSTEEL, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateExpandedTrimmableItem(UGItems.FROSTSTEEL_LEGGINGS.get(), UGEquipmentAssets.FROSTSTEEL, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateExpandedTrimmableItem(UGItems.FROSTSTEEL_BOOTS.get(), UGEquipmentAssets.FROSTSTEEL, ItemModelGenerators.TRIM_PREFIX_BOOTS);
		this.generateExpandedTrimmableItem(UGItems.UTHERIUM_HELMET.get(), UGEquipmentAssets.UTHERIUM, ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateExpandedTrimmableItem(UGItems.UTHERIUM_CHESTPLATE.get(), UGEquipmentAssets.UTHERIUM, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateExpandedTrimmableItem(UGItems.UTHERIUM_LEGGINGS.get(), UGEquipmentAssets.UTHERIUM, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateExpandedTrimmableItem(UGItems.UTHERIUM_BOOTS.get(), UGEquipmentAssets.UTHERIUM, ItemModelGenerators.TRIM_PREFIX_BOOTS);
		this.generateFlatItem(UGItems.FROSTSTEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FROSTSTEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FROSTSTEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FROSTSTEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FROSTSTEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.UTHERIUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.UTHERIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.UTHERIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.UTHERIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.UTHERIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateBattleaxe(UGItems.FORGOTTEN_BATTLEAXE.get());
		this.generateFlatItem(UGItems.FORGOTTEN_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.FORGOTTEN_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
		this.generateFlatItem(UGItems.SMOGSTEM_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.SMOGSTEM_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.WIGGLEWOOD_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.WIGGLEWOOD_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.GRONGLE_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.GRONGLE_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.ANCIENT_ROOT_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.ANCIENT_ROOT_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.VIRULENT_MIX_BUCKET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.GWIBLING_BUCKET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.UNDERBEANS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.ROASTED_UNDERBEANS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.BLISTERBERRY.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.GLOOMGOURD_PIE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.RAW_DWELLER_MEAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.DWELLER_STEAK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.RAW_GWIBLING.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.COOKED_GWIBLING.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.RAW_GLOOMPER_LEG.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.GLOOMPER_LEG.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.BLOODY_STEW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.INDIGO_STEW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.INKY_STEW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.VEILED_STEW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.UNDERBEAN_STICK.get(), ModelTemplates.FLAT_HANDHELD_ROD_ITEM);
		this.generateFlatItem(UGItems.RAW_CLOGGRUM.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.RAW_FROSTSTEEL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.DITCHBULB_PASTE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.BLOOD_GLOBULE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.BLUE_MOGMOSS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.SLOP_BOWL.get(), ModelTemplates.FLAT_ITEM);
		this.generateExpandedTrimmableItem(UGItems.ANCIENT_HELMET.get(), UGEquipmentAssets.ANCIENT, ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateExpandedTrimmableItem(UGItems.ANCIENT_CHESTPLATE.get(), UGEquipmentAssets.ANCIENT, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateExpandedTrimmableItem(UGItems.ANCIENT_LEGGINGS.get(), UGEquipmentAssets.ANCIENT, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateFlatItem(UGItems.ROGDORIUM.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.ROGDORIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.DENIZEN_MASK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.UTHERIC_CLUSTER.get(), ModelTemplates.FLAT_ITEM);
		this.generateSpear(UGItems.CLOGGRUM_SPEAR.get());
		this.generateSpear(UGItems.FROSTSTEEL_SPEAR.get());
		this.generateSpear(UGItems.UTHERIUM_SPEAR.get());
		this.generateSpear(UGItems.FORGOTTEN_SPEAR.get());

		ItemModel.Unbaked flatJavelinModel = ItemModelUtils.plainModel(this.createFlatItemModel(UGItems.JAVELIN.get(), ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked inHandNormalModel = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.JAVELIN.get(), "_in_hand"), new JavelinSpecialRenderer.Unbaked());
		ItemModel.Unbaked inHandThrowingModel = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.JAVELIN.get(), "_throwing"), new JavelinSpecialRenderer.Unbaked());
		ItemModel.Unbaked inHandModel = ItemModelUtils.conditional(TridentSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), inHandThrowingModel, inHandNormalModel);
		this.itemModelOutput.accept(UGItems.JAVELIN.get(), createFlatModelDispatch(flatJavelinModel, inHandModel));

		ItemModel.Unbaked normalShield = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.CLOGGRUM_SHIELD.get()), new CloggrumShieldSpecialRenderer.Unbaked());
		ItemModel.Unbaked blockingShield = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.CLOGGRUM_SHIELD.get(), "_blocking"), new CloggrumShieldSpecialRenderer.Unbaked());
		this.itemModelOutput.accept(UGItems.CLOGGRUM_SHIELD.get(), ItemModelUtils.conditional(ShieldSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), blockingShield, normalShield));

		//TODO
//		this.getBuilder(UGItems.CLOGGRUM_BUCKET.getId().toString())
//			.parent(new ModelFile.UncheckedModelFile("neoforge:item/default"))
//			.customLoader(CloggrumBucketModelBuilder::begin).fluid(Fluids.EMPTY).flipGas(true).applyFluidLuminosity(true).end()
//			.texture("base", modLoc("item/cloggrum_bucket"))
//			.texture("fluid", Identifier.fromNamespaceAndPath(NeoForgeVersion.MOD_ID, "item/mask/bucket_fluid_drip"));
	}

	public void generateBattleaxe(Item item) {
		ItemModel.Unbaked flatModel = ItemModelUtils.plainModel(this.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked inHandModel = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item, "_in_hand"));
		this.itemModelOutput.accept(item, createFlatModelDispatch(flatModel, inHandModel));
	}

	public void generateExpandedTrimmableItem(Item armor, ResourceKey<EquipmentAsset> equipmentAssetId, Identifier slotTrimPrefix) {
		Identifier modelLocation = ModelLocationUtils.getModelLocation(armor);
		Material itemTexture = TextureMapping.getItemTexture(armor);
		List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases = new ArrayList<>(EX_TRIM_MATERIAL_MODELS.size());

		for (ItemModelGenerators.TrimMaterialData material : TRIM_MATERIAL_MODELS) {
			Identifier trimModelLocation = modelLocation.withSuffix("_" + material.assets().base().suffix() + "_trim");
			Material trimOverlayTexture = new Material(slotTrimPrefix.withSuffix("_" + material.assets().assetId(equipmentAssetId).suffix()));
			ItemModel.Unbaked trimModel= ItemModelUtils.plainModel(trimModelLocation);
			this.generateLayeredItem(trimModelLocation, itemTexture, trimOverlayTexture);

			cases.add(ItemModelUtils.when(material.materialKey(), trimModel));
		}

		ItemModel.Unbaked untrimmedModel = ItemModelUtils.plainModel(modelLocation);
		ModelTemplates.FLAT_ITEM.create(modelLocation, TextureMapping.layer0(itemTexture), this.modelOutput);
		this.itemModelOutput.accept(armor, ItemModelUtils.select(new TrimMaterialProperty(), untrimmedModel, cases));
	}
}