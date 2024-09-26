package quek.undergarden;

import net.minecraft.ChatFormatting;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class UGEnumExtensions {
	public static final EnumProxy<RecipeBookType> INFUSER = new EnumProxy<>(RecipeBookType.class);

	public static final EnumProxy<RecipeBookCategories> INFUSER_SEARCH_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_PURIFYING_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGItems.ROGDORIUM_CRYSTAL.get()))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_CORRUPTING_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()), new ItemStack(UGItems.UTHERIC_SHARD.get()))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_MISC_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGBlocks.GRONGLET.get()))
	);

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

	public static Object ANCIENT_ROOT_BOAT(int idx, Class<?> type) {
		if (idx == 5)
			return false;
		return type.cast(switch (idx) {
			case 0 -> UGBlocks.ANCIENT_ROOT_PLANKS;
			case 1 -> "undergarden:ancient_root";
			case 2 -> UGItems.ANCIENT_ROOT_BOAT;
			case 3 -> UGItems.ANCIENT_ROOT_CHEST_BOAT;
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

	public static final EnumProxy<MobCategory> STUPID_MOB_CATEGORY = new EnumProxy<>(
		MobCategory.class, "undergarden:stupid", 50, true, true, 128
	);
}