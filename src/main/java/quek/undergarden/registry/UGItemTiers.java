package quek.undergarden.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class UGItemTiers {

	public static final ToolMaterial CLOGGRUM = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 288, 6.0F, 3.0F, 6, UGTags.Items.CLOGGRUM_TOOL_MATERIALS);
	public static final ToolMaterial FROSTSTEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 2864, 5.0F, 2.0F, 18, UGTags.Items.FROSTSTEEL_TOOL_MATERIALS);
	public static final ToolMaterial UTHERIUM = new ToolMaterial(UGTags.Blocks.INCORRECT_FOR_FORGOTTEN_TOOL, 1248, 10.0F, 4.0F, 10, UGTags.Items.UTHERIC_TOOL_MATERIALS);
	public static final ToolMaterial FORGOTTEN = new ToolMaterial(UGTags.Blocks.INCORRECT_FOR_FORGOTTEN_TOOL, 1872, 8.0F, 3.0F, 12, UGTags.Items.FORGOTTEN_TOOL_MATERIALS);
}