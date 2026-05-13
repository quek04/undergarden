package quek.undergarden.datagen.assets;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.renderer.special.TridentSpecialRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredHolder;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.item.CloggrumBucketModel;
import quek.undergarden.client.model.item.PullingSlingshotModel;
import quek.undergarden.client.render.item.CloggrumShieldSpecialRenderer;
import quek.undergarden.client.render.item.JavelinSpecialRenderer;
import quek.undergarden.registry.UGItems;

import java.util.Optional;
import java.util.function.BiConsumer;

public class UGItemModels extends ItemModelGenerators {

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
		this.generateDynamicTrimmableItem(UGItems.CLOGGRUM_HELMET.get(), Undergarden.prefix("trims/items/cloggrum_helmet_trim"));
		this.generateDynamicTrimmableItem(UGItems.CLOGGRUM_CHESTPLATE.get(), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateDynamicTrimmableItem(UGItems.CLOGGRUM_LEGGINGS.get(), ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateDynamicTrimmableItem(UGItems.CLOGGRUM_BOOTS.get(), ItemModelGenerators.TRIM_PREFIX_BOOTS);
		this.generateDynamicTrimmableItem(UGItems.FROSTSTEEL_HELMET.get(), ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateDynamicTrimmableItem(UGItems.FROSTSTEEL_CHESTPLATE.get(), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateDynamicTrimmableItem(UGItems.FROSTSTEEL_LEGGINGS.get(), ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateDynamicTrimmableItem(UGItems.FROSTSTEEL_BOOTS.get(), ItemModelGenerators.TRIM_PREFIX_BOOTS);
		this.generateDynamicTrimmableItem(UGItems.UTHERIUM_HELMET.get(), ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateDynamicTrimmableItem(UGItems.UTHERIUM_CHESTPLATE.get(), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateDynamicTrimmableItem(UGItems.UTHERIUM_LEGGINGS.get(), ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateDynamicTrimmableItem(UGItems.UTHERIUM_BOOTS.get(), Undergarden.prefix("trims/items/utherium_boots_trim"));
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
		this.generateDynamicTrimmableItem(UGItems.ANCIENT_HELMET.get(), ItemModelGenerators.TRIM_PREFIX_HELMET);
		this.generateDynamicTrimmableItem(UGItems.ANCIENT_CHESTPLATE.get(), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
		this.generateDynamicTrimmableItem(UGItems.ANCIENT_LEGGINGS.get(), ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
		this.generateFlatItem(UGItems.ROGDORIUM.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.ROGDORIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.DENIZEN_MASK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.UTHERIC_CLUSTER.get(), ModelTemplates.FLAT_ITEM);
		this.generateSpear(UGItems.CLOGGRUM_SPEAR.get());
		this.generateSpear(UGItems.FROSTSTEEL_SPEAR.get());
		this.generateSpear(UGItems.UTHERIUM_SPEAR.get());
		this.generateSpear(UGItems.FORGOTTEN_SPEAR.get());
		this.generateFlatItem(UGItems.RAW_UNDERGAR_FILLET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(UGItems.COOKED_UNDERGAR_FILLET.get(), ModelTemplates.FLAT_ITEM);

		ItemModel.Unbaked flatJavelinModel = ItemModelUtils.plainModel(this.createFlatItemModel(UGItems.JAVELIN.get(), ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked inHandNormalModel = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.JAVELIN.get(), "_in_hand"), new JavelinSpecialRenderer.Unbaked());
		ItemModel.Unbaked inHandThrowingModel = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.JAVELIN.get(), "_throwing"), new JavelinSpecialRenderer.Unbaked());
		ItemModel.Unbaked inHandModel = ItemModelUtils.conditional(TridentSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), inHandThrowingModel, inHandNormalModel);
		this.itemModelOutput.accept(UGItems.JAVELIN.get(), createFlatModelDispatch(flatJavelinModel, inHandModel));

		ItemModel.Unbaked normalShield = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.CLOGGRUM_SHIELD.get()), new CloggrumShieldSpecialRenderer.Unbaked());
		ItemModel.Unbaked blockingShield = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(UGItems.CLOGGRUM_SHIELD.get(), "_blocking"), new CloggrumShieldSpecialRenderer.Unbaked());
		this.itemModelOutput.accept(UGItems.CLOGGRUM_SHIELD.get(), ItemModelUtils.conditional(ShieldSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), blockingShield, normalShield));

		this.itemModelOutput.accept(UGItems.CLOGGRUM_BUCKET.get(), new CloggrumBucketModel.Unbaked(
			new CloggrumBucketModel.Textures(
				Optional.empty(),
				Optional.of(TextureMapping.getItemTexture(UGItems.CLOGGRUM_BUCKET.get())),
				Optional.of(new Material(Identifier.fromNamespaceAndPath("neoforge", "item/mask/bucket_fluid_drip"))),
				Optional.empty()
			), Fluids.EMPTY, true, true));

		this.itemModelOutput.accept(UGItems.SLINGSHOT.get(), ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), ItemModelUtils.rangeSelect(
			new UseDuration(false),
			0.05F, new PullingSlingshotModel.Unbaked(new Material(Undergarden.prefix("item/slingshot_pulling_0")), 0),
			ItemModelUtils.override(new PullingSlingshotModel.Unbaked(new Material(Undergarden.prefix("item/slingshot_pulling_1")), 1), 0.65F),
			ItemModelUtils.override(new PullingSlingshotModel.Unbaked(new Material(Undergarden.prefix("item/slingshot_pulling_2")), 2), 0.9F)
		), ItemModelUtils.plainModel(this.createFlatItemModel(UGItems.SLINGSHOT.get(), ModelTemplates.FLAT_ITEM))));
	}

	public void generateBattleaxe(Item item) {
		ItemModel.Unbaked flatModel = ItemModelUtils.plainModel(this.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked inHandModel = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item, "_in_hand"));
		this.itemModelOutput.accept(item, createFlatModelDispatch(flatModel, inHandModel));
	}

	public void generateDynamicTrimmableItem(Item armor, Identifier slotTrimPrefix) {
		this.generateDynamicTrimmableItem(armor, this.createFlatItemModel(armor, ModelTemplates.FLAT_ITEM), slotTrimPrefix);
	}
}