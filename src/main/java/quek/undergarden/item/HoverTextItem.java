package quek.undergarden.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class HoverTextItem extends Item {

	public static final Component FROSTSTEEL_WEAPON = Component.translatable("tooltip.undergarden.froststeel_weapon").withStyle(ChatFormatting.AQUA);
	public static final Component UTHERIUM_WEAPON = Component.translatable("tooltip.undergarden.utherium_weapon").withStyle(ChatFormatting.RED);
	public static final Component FORGOTTEN_WEAPON = Component.translatable("tooltip.undergarden.forgotten_weapon").withStyle(ChatFormatting.GREEN);
	public static final Component FORGOTTEN_TOOL = Component.translatable("tooltip.undergarden.forgotten_tool").withStyle(ChatFormatting.GREEN);

	private final List<Component> tooltip;

	public HoverTextItem(String key, Properties properties) {
		this(Component.translatable(key).withStyle(ChatFormatting.GRAY), properties);
	}

	public HoverTextItem(Component tooltip, Properties properties) {
		this(List.of(tooltip), properties);
	}

	public HoverTextItem(List<Component> tooltip, Properties properties) {
		super(properties);
		this.tooltip = tooltip;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
		this.tooltip.forEach(builder);
	}
}
