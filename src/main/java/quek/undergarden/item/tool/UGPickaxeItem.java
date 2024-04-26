package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import quek.undergarden.registry.UGItems;

import java.util.List;

public class UGPickaxeItem extends PickaxeItem {

	public UGPickaxeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.getItem() == UGItems.FROSTSTEEL_PICKAXE.get()) {
			tooltip.add(Component.translatable("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
		}
		if (stack.getItem() == UGItems.FORGOTTEN_PICKAXE.get()) {
			tooltip.add(Component.translatable("tooltip.forgotten_tool").withStyle(ChatFormatting.GREEN));
		}
	}
}