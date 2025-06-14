package quek.undergarden.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import quek.undergarden.Undergarden;

public class UGWoodStuff {

	public static final BlockSetType WIGGLEWOOD_WOOD_SET = new BlockSetType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "wigglewood").toString());
	public static final BlockSetType SMOGSTEM_WOOD_SET = new BlockSetType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "smogstem").toString());
	public static final BlockSetType GRONGLE_WOOD_SET = new BlockSetType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "grongle").toString());
	public static final BlockSetType ANCIENT_ROOT_WOOD_SET = new BlockSetType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "ancient_root").toString());

	public static final WoodType WIGGLEWOOD_WOOD_TYPE = WoodType.register(new WoodType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "wigglewood").toString(), WIGGLEWOOD_WOOD_SET));
	public static final WoodType SMOGSTEM_WOOD_TYPE = WoodType.register(new WoodType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "smogstem").toString(), SMOGSTEM_WOOD_SET));
	public static final WoodType GRONGLE_WOOD_TYPE = WoodType.register(new WoodType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "grongle").toString(), GRONGLE_WOOD_SET));
	public static final WoodType ANCIENT_ROOT_WOOD_TYPE = WoodType.register(new WoodType(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "ancient_root").toString(), ANCIENT_ROOT_WOOD_SET));
}
