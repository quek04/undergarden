package quek.undergarden;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class UGEnumExtensions {

	public static Object FORGOTTEN(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> -1;
			case 1 -> "undergarden:forgotten";
			case 2 -> (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.GREEN);
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object WIGGLEWOOD_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> UGBlocks.WIGGLEWOOD_PLANKS;
			case 1 -> "undergarden:wigglewood";
			case 2 -> UGItems.WIGGLEWOOD_BOAT;
			case 3 -> UGItems.WIGGLEWOOD_CHEST_BOAT;
			case 4 -> UGItems.TWISTYTWIG;
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object SMOGSTEM_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> UGBlocks.SMOGSTEM_PLANKS;
			case 1 -> "undergarden:smogstem";
			case 2 -> UGItems.SMOGSTEM_BOAT;
			case 3 -> UGItems.SMOGSTEM_CHEST_BOAT;
			case 4 -> (Supplier<Item>) () -> Items.STICK;
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object GRONGLE_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> UGBlocks.GRONGLE_PLANKS;
			case 1 -> "undergarden:grongle";
			case 2 -> UGItems.GRONGLE_BOAT;
			case 3 -> UGItems.GRONGLE_CHEST_BOAT;
			case 4 -> (Supplier<Item>) () -> Items.STICK;
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}

	public static Object VIRULENT_HEARTS(int idx, Class<?> type) {
		return type.cast(switch (idx) {
			case 0 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/normal");
			case 1 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/normal_blinking");
			case 2 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/half");
			case 3 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/half_blinking");
			case 4 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/hardcore");
			case 5 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/hardcore_blinking");
			case 6 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/hardcore_half");
			case 7 -> ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "virulence_hearts/hardcore_half_blinking");
			default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
		});
	}
}
