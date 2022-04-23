package quek.undergarden.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;

public class UGModelLayers {

    public static final ModelLayerLocation BRUTE = register("brute");
    public static final ModelLayerLocation DEPTHROCK_BED_FOOT = register("depthrock_bed_foot");
    public static final ModelLayerLocation DEPTHROCK_BED_HEAD = register("depthrock_bed_head");
    public static final ModelLayerLocation DWELLER = register("dweller");
    public static final ModelLayerLocation DWELLER_SADDLE = register("dweller", "saddle");
    public static final ModelLayerLocation FORGOTTEN_GUARDIAN = register("forgotten_guardian");
    public static final ModelLayerLocation FORGOTTEN_INNER_ARMOR = register("forgotten", "inner_armor");
    public static final ModelLayerLocation FORGOTTEN = register("forgotten");
    public static final ModelLayerLocation FORGOTTEN_OUTER_ARMOR = register("forgotten", "outer_armor");
    public static final ModelLayerLocation GLOOMPER = register("gloomper");
    public static final ModelLayerLocation GWIB = register("gwib");
    public static final ModelLayerLocation GWIBLING = register("gwibling");
    public static final ModelLayerLocation MASTICATOR = register("masticator");
    public static final ModelLayerLocation MINION = register("minion");
    public static final ModelLayerLocation MOG = register("mog");
    public static final ModelLayerLocation MUNCHER = register("muncher");
    public static final ModelLayerLocation NARGOYLE = register("nargoyle");
    public static final ModelLayerLocation ROTBEAST = register("rotbeast");
    public static final ModelLayerLocation ROTLING = register("rotling");
    public static final ModelLayerLocation ROTWALKER = register("rotwalker");
    public static final ModelLayerLocation SCINTLING = register("scintling");
    public static final ModelLayerLocation SPLOOGIE = register("sploogie");
    public static final ModelLayerLocation STONEBORN = register("stoneborn");
    public static final ModelLayerLocation GRONGLET = register("gronglet");

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, name), "main");
    }

    private static ModelLayerLocation register(String name, String layerName) {
        return new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, name), layerName);
    }
}