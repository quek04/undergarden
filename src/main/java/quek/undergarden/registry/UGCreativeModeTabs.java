package quek.undergarden.registry;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.component.RogdoriumInfusion;

import java.util.ArrayList;
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
					output.accept(EnchantmentHelper.createBook(new EnchantmentInstance(enchantmentRegistryLookup.getOrThrow(UGEnchantments.RICOCHET), 3)));
					output.accept(EnchantmentHelper.createBook(new EnchantmentInstance(enchantmentRegistryLookup.getOrThrow(UGEnchantments.LONGEVITY), 3)));
					output.accept(EnchantmentHelper.createBook(new EnchantmentInstance(enchantmentRegistryLookup.getOrThrow(UGEnchantments.SELF_SLING), 1)));
				});
				UGItems.ITEMS.getEntries().forEach(item -> {
					if (!DONT_INCLUDE.contains(item) && !item.getKey().identifier().getPath().contains("tremblecrust")) {
						output.accept(item.get());
					}
					if (item.components().has(DataComponents.EQUIPPABLE) && item.components().get(DataComponents.EQUIPPABLE).assetId().isPresent()) {
						ItemStack armorStack = new ItemStack(item);
						armorStack.set(UGDataComponents.ROGDORIUM_INFUSION, RogdoriumInfusion.setInfusionAmount(RogdoriumInfusion.DEFAULT.infusionMax()));
						output.accept(armorStack);
					}
				});
			}).build());

	public static void registerBuckets(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == TAB.getKey()) {
			List<EntityType<?>> usedEntities = new ArrayList<>();
			List<Block> usedBlocks = new ArrayList<>();

			for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof MobBucketItem).toList().reversed()) {
				EntityType<?> type = ((MobBucketItem) item).type;
				if (!usedEntities.contains(type)) {
					FluidStack stack = new FluidStack(((MobBucketItem) item).content, FluidType.BUCKET_VOLUME);
					Identifier id = EntityType.getKey(type);
					event.insertAfter(UGItems.CLOGGRUM_BUCKET.toStack(), new ItemStack(UGItems.CLOGGRUM_BUCKET, 1, DataComponentPatch.builder()
						.set(DataComponents.BUCKET_ENTITY_DATA, CustomData.of(Util.make(new CompoundTag(), tag -> tag.putString("id", id.toString()))))
						.set(UGDataComponents.STORED_FLUID.get(), SimpleFluidContent.copyOf(stack)).build()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
					usedEntities.add(type);
				}
			}

			for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item instanceof SolidBucketItem).toList().reversed()) {
				Block block = ((SolidBucketItem) item).getBlock();
				if (!usedBlocks.contains(block)) {
					event.insertAfter(UGItems.CLOGGRUM_BUCKET.toStack(), new ItemStack(UGItems.CLOGGRUM_BUCKET, 1, DataComponentPatch.builder().set(UGDataComponents.STORED_BLOCK.get(), block.defaultBlockState()).build()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
					usedBlocks.add(block);
				}
			}

			for (Fluid fluid : BuiltInRegistries.FLUID.stream().filter(fluid -> fluid.isSource(fluid.defaultFluidState())).toList().reversed()) {
				FluidStack stack = new FluidStack(fluid, FluidType.BUCKET_VOLUME);
				DataComponentPatch.Builder builder =  DataComponentPatch.builder();
				builder.set(UGDataComponents.STORED_FLUID.get(), SimpleFluidContent.copyOf(stack));
				if (stack.is(NeoForgeMod.MILK)) {
					builder.set(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET);
				}
				event.insertAfter(UGItems.CLOGGRUM_BUCKET.toStack(), new ItemStack(UGItems.CLOGGRUM_BUCKET, 1, builder.build()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			}
		}
	}
}