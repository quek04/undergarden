package quek.undergarden;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.RecipeBookType;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class UGEnumExtensions {
	public static final EnumProxy<RecipeBookType> INFUSER = new EnumProxy<>(RecipeBookType.class);

	public static Object FORGOTTEN(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> -1;
			case 1 -> "undergarden:forgotten";
			case 2 -> (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.GREEN);
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object ROGDORIUM(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> -1;
			case 1 -> "undergarden:rogdorium";
			case 2 -> (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.AQUA);
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object UTHERIUM(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> -1;
			case 1 -> "undergarden:utherium";
			case 2 -> (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.RED);
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object VIRULENT_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> Undergarden.prefix("virulence_hearts/normal");
			case 1 -> Undergarden.prefix("virulence_hearts/normal_blinking");
			case 2 -> Undergarden.prefix("virulence_hearts/half");
			case 3 -> Undergarden.prefix("virulence_hearts/half_blinking");
			case 4 -> Undergarden.prefix("virulence_hearts/hardcore");
			case 5 -> Undergarden.prefix("virulence_hearts/hardcore_blinking");
			case 6 -> Undergarden.prefix("virulence_hearts/hardcore_half");
			case 7 -> Undergarden.prefix("virulence_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static final EnumProxy<MobCategory> STUPID_MOB_CATEGORY = new EnumProxy<>(
		MobCategory.class, "undergarden:stupid", 50, true, true, 128
	);
}