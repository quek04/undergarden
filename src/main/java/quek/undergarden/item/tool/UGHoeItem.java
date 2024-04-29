package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import quek.undergarden.registry.UGItems;

import java.util.List;

public class UGHoeItem extends HoeItem {

	public UGHoeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (stack.getItem() == UGItems.FORGOTTEN_HOE.get()) {
			tooltip.add(Component.translatable("tooltip.undergarden.forgotten_tool").withStyle(ChatFormatting.GREEN));
		}
	}
}