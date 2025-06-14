package quek.undergarden.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import java.util.List;
import java.util.function.Supplier;

public class UndergardenClient {

	public static void playPortalSound() {
		Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL.get(), 1.0F, 1.0F));
	}

	@Nullable
	public static RegistryAccess registryAccess() {
		if (Minecraft.getInstance().level != null) {
			return Minecraft.getInstance().level.registryAccess();
		}
		return null;
	}

	public static final EnumProxy<RecipeBookCategories> INFUSER_SEARCH_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_PURIFYING_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGItems.ROGDORIUM.get()))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_CORRUPTING_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGItems.UTHERIUM_CRYSTAL.get()), new ItemStack(UGItems.UTHERIC_SHARD.get()))
	);
	public static final EnumProxy<RecipeBookCategories> INFUSER_MISC_CATEGORY = new EnumProxy<>(
		RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(UGBlocks.GRONGLET.get()))
	);
}
