package quek.undergarden.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class UGItemTiers {

	public static final ToolMaterial CLOGGRUM = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 286, 6.0F, 3.0F, 8, UGTags.Items.CLOGGRUM_TOOL_MATERIALS);
	public static final ToolMaterial FROSTSTEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 575, 7.0F, 2.0F, 20, UGTags.Items.FROSTSTEEL_TOOL_MATERIALS);
	public static final ToolMaterial UTHERIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1279, 8.5F, 3.5F, 17, UGTags.Items.UTHERIC_TOOL_MATERIALS);
	public static final ToolMaterial FORGOTTEN = new ToolMaterial(UGTags.Blocks.INCORRECT_FOR_FORGOTTEN_TOOL, 1876, 8.0F, 3.0F, 2, UGTags.Items.FORGOTTEN_TOOL_MATERIALS);
}