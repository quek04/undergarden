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

	private static final List<DeferredItem<Item>> DONT_INCLUDE = List.of(UGItems.GLOOMPER_SECRET_DISC);

	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Undergarden.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("undergarden_group", () -> CreativeModeTab.builder()
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.undergarden_group"))
			.icon(() -> new ItemStack(UGBlocks.DEEPTURF_BLOCK.get()))
			.displayItems((parameters, output) -> {
				parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent(enchantmentRegistryLookup -> {
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantmentRegistryLookup.getOrThrow(UGEnchantments.RICOCHET), 3)));
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantmentRegistryLookup.getOrThrow(UGEnchantments.LONGEVITY), 3)));
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantmentRegistryLookup.getOrThrow(UGEnchantments.SELF_SLING), 1)));
				});
				UGItems.ITEMS.getEntries().forEach(item -> {
					if (!DONT_INCLUDE.contains(item) && !item.getKey().location().getPath().contains("tremblecrust")) {
						output.accept(item.get());
					}
				});
			}).build());
}