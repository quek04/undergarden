package quek.undergarden;

import net.minecraft.ChatFormatting;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.network.chat.Style;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class UGEnumExtensions {
	public static final EnumProxy<Rarity> FORGOTTEN = new EnumProxy<>(
		Rarity.class, -1, "undergarden:forgotten", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.GREEN)
	);
	public static final EnumProxy<Rarity> ROGDORIUM = new EnumProxy<>(
		Rarity.class, -1, "undergarden:rogdorium", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.AQUA)
	);
	public static final EnumProxy<Rarity> UTHERIUM = new EnumProxy<>(
		Rarity.class, -1, "undergarden:utherium", (UnaryOperator<Style>) style -> style.withColor(ChatFormatting.RED)
	);

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
}
