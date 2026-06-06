package quek.undergarden.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import quek.undergarden.Undergarden;

public class UGModelLayers {

	public static final ModelLayerLocation DEPTHROCK_BED_HEAD = register("depthrock_bed_head");
	public static final ModelLayerLocation DEPTHROCK_BED_FOOT = register("depthrock_bed_foot");
	public static final ModelLayerLocation BRUTE = register("brute");
	public static final ModelLayerLocation DWELLER = register("dweller");
	public static final ModelLayerLocation DWELLER_BABY = register("dweller_baby");
	public static final ModelLayerLocation DWELLER_SADDLE = register("dweller", "saddle");
	public static final ModelLayerLocation GREATER_DWELLER = register("greater_dweller");
	public static final ModelLayerLocation FORGOTTEN_GUARDIAN = register("forgotten_guardian");
	public static final ModelLayerLocation FORGOTTEN = register("forgotten");
	public static final ArmorModelSet<ModelLayerLocation> FORGOTTEN_ARMOR = registerArmorSet("forgotten");
	public static final ModelLayerLocation GLOOMPER = register("gloomper");
	public static final ModelLayerLocation GWIBLING = register("gwibling");
	public static final ModelLayerLocation GWIB = register("gwib");
	public static final ModelLayerLocation LIVING_POT = register("living_pot");
	public static final ModelLayerLocation MINION = register("minion");
	public static final ModelLayerLocation MOG = register("mog");
	public static final ModelLayerLocation MOG_BABY = register("mog_baby");
	public static final ModelLayerLocation SMOG_MOG = register("smog_mog");
	public static final ModelLayerLocation MUNCHER = register("muncher");
	public static final ModelLayerLocation NARGOYLE = register("nargoyle");
	public static final ModelLayerLocation ROTBEAST = register("rotbeast");
	public static final ModelLayerLocation ROTLING = register("rotling");
	public static final ModelLayerLocation ROTWALKER = register("rotwalker");
	public static final ModelLayerLocation SCINTLING = register("scintling");
	public static final ModelLayerLocation SCINTLING_BABY = register("scintling_baby");
	public static final ModelLayerLocation SPLOOGIE = register("sploogie");
	public static final ModelLayerLocation STONEBORN = register("stoneborn");
	public static final ModelLayerLocation GRONGLET = register("gronglet");
	public static final ModelLayerLocation DENIZEN = register("denizen");
	public static final ModelLayerLocation DENIZEN_2 = register("denizen_2");
	public static final ModelLayerLocation DENIZEN_MASK = register("denizen_mask");
	public static final ModelLayerLocation ROTBELCHER = register("rotbelcher");
	public static final ModelLayerLocation POT = register("pot");
	public static final ModelLayerLocation CLOGGRUM_SHIELD = register("cloggrum_shield");
	public static final ModelLayerLocation JAVELIN = register("javelin");
	public static final ModelLayerLocation UNDERGAR = register("undergar");
	public static final ModelLayerLocation WIGGLEWOOD_BOAT = register("wigglewood_boat");
	public static final ModelLayerLocation WIGGLEWOOD_CHEST_BOAT = register("wigglewood_chest_boat");
	public static final ModelLayerLocation SMOGSTEM_BOAT = register("smogstem_boat");
	public static final ModelLayerLocation SMOGSTEM_CHEST_BOAT = register("smogstem_chest_boat");
	public static final ModelLayerLocation GRONGLE_BOAT = register("grongle_boat");
	public static final ModelLayerLocation GRONGLE_CHEST_BOAT = register("grongle_chest_boat");
	public static final ModelLayerLocation ANCIENT_ROOT_BOAT = register("ancient_root_boat");
	public static final ModelLayerLocation ANCIENT_ROOT_CHEST_BOAT = register("ancient_root_chest_boat");

	private static ModelLayerLocation register(String name) {
		return new ModelLayerLocation(Undergarden.prefix(name), "main");
	}

	private static ModelLayerLocation register(String name, String layerName) {
		return new ModelLayerLocation(Undergarden.prefix(name), layerName);
	}

	private static ArmorModelSet<ModelLayerLocation> registerArmorSet(String modelId) {
		return new ArmorModelSet<>(register(modelId, "helmet"), register(modelId, "chestplate"), register(modelId, "leggings"), register(modelId, "boots"));
	}
}