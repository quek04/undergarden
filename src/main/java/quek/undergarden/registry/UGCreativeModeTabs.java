package quek.undergarden.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;

import java.util.List;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGCreativeModeTabs {

	private static final List<RegistryObject<Item>> DONT_INCLUDE = List.of(UGItems.GLOOMPER_SECRET_DISC, UGItems.MASTICATOR_SCALES, UGItems.MASTICATED_CHESTPLATE);

	@SubscribeEvent
	public static void addUGTab(CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation(Undergarden.MODID, "undergarden_group"), builder -> builder
				.title(Component.translatable("itemGroup.undergarden_group"))
				.icon(() -> new ItemStack(UGBlocks.DEEPTURF_BLOCK.get()))
				.displayItems((parameters, output) -> {
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(UGEnchantments.RICOCHET.get(), UGEnchantments.RICOCHET.get().getMaxLevel())));
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(UGEnchantments.LONGEVITY.get(), UGEnchantments.LONGEVITY.get().getMaxLevel())));
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(UGEnchantments.SELF_SLING.get(), UGEnchantments.SELF_SLING.get().getMaxLevel())));
					UGItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
						if (!DONT_INCLUDE.contains(itemRegistryObject) && !itemRegistryObject.getKey().location().getPath().contains("tremblecrust")) {
							output.accept(itemRegistryObject.get());
						}
					});
				}));
	}
}