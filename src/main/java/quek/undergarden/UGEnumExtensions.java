package quek.undergarden;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class UGEnumExtensions {
	public static final EnumProxy<Rarity> FORGOTTEN = new EnumProxy<>(
		Rarity.class, -1, "undergarden:forgotten", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.GREEN)
	);
	public static final EnumProxy<Rarity> ROGDORIUM = new EnumProxy<>(
		Rarity.class, -1, "undergarden:rogdorium", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.AQUA)
	);
}
