package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;

import java.util.List;

public class UGCreativeModeTabs {

	private static final List<DeferredItem<Item>> DONT_INCLUDE = List.of(UGItems.GLOOMPER_SECRET_DISC, UGItems.MASTICATOR_SCALES, UGItems.MASTICATED_CHESTPLATE);

	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Undergarden.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("undergarden_group", () -> CreativeModeTab.builder()
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.undergarden_group"))
			.icon(() -> new ItemStack(UGBlocks.DEEPTURF_BLOCK.get()))
			.displayItems((parameters, output) -> {
				output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(UGEnchantments.RICOCHET.get(), UGEnchantments.RICOCHET.get().getMaxLevel())));
				output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(UGEnchantments.LONGEVITY.get(), UGEnchantments.LONGEVITY.get().getMaxLevel())));
				output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(UGEnchantments.SELF_SLING.get(), UGEnchantments.SELF_SLING.get().getMaxLevel())));
				UGItems.ITEMS.getEntries().forEach(item -> {
					if (!DONT_INCLUDE.contains(item) && !item.getKey().location().getPath().contains("tremblecrust")) {
						output.accept(item.get());
					}
				});
			}).build());
}