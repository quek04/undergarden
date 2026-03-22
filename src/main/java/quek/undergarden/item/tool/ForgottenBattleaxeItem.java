package quek.undergarden.item.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import quek.undergarden.item.HoverTextItem;

import java.util.function.Consumer;

public class ForgottenBattleaxeItem extends BattleaxeItem {

	public ForgottenBattleaxeItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
		builder.accept(HoverTextItem.FORGOTTEN_WEAPON);
	}
}
