package quek.undergarden;

import net.minecraft.ChatFormatting;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.network.chat.Style;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
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

	public static final EnumProxy<RecipeBookType> INFUSER = new EnumProxy<>(RecipeBookType.class);

	public static final EnumProxy<RecipeBookCategories> INFUSER_SEARCH = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_MISC = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()))
	);
}
