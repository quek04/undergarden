package quek.undergarden.registry;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import quek.undergarden.Undergarden;

public class UGWoodStuff {

	public static final BlockSetType WIGGLEWOOD_WOOD_SET = new BlockSetType(Undergarden.prefix("wigglewood").toString());
	public static final BlockSetType SMOGSTEM_WOOD_SET = new BlockSetType(Undergarden.prefix("smogstem").toString());
	public static final BlockSetType GRONGLE_WOOD_SET = new BlockSetType(Undergarden.prefix("grongle").toString());
	public static final BlockSetType ANCIENT_ROOT_WOOD_SET = new BlockSetType(Undergarden.prefix("ancient_root").toString());

	public static final WoodType WIGGLEWOOD_WOOD_TYPE = WoodType.register(new WoodType(Undergarden.prefix("wigglewood").toString(), WIGGLEWOOD_WOOD_SET));
	public static final WoodType SMOGSTEM_WOOD_TYPE = WoodType.register(new WoodType(Undergarden.prefix("smogstem").toString(), SMOGSTEM_WOOD_SET));
	public static final WoodType GRONGLE_WOOD_TYPE = WoodType.register(new WoodType(Undergarden.prefix("grongle").toString(), GRONGLE_WOOD_SET));
	public static final WoodType ANCIENT_ROOT_WOOD_TYPE = WoodType.register(new WoodType(Undergarden.prefix("ancient_root").toString(), ANCIENT_ROOT_WOOD_SET));
}
