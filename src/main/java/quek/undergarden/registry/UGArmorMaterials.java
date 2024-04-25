package quek.undergarden.registry;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;

import java.util.EnumMap;
import java.util.List;

public class UGArmorMaterials {

	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Undergarden.MODID);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CLOGGRUM = ARMOR_MATERIALS.register("cloggrum", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 5);
		map.put(ArmorItem.Type.CHESTPLATE, 6);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(UGItems.CLOGGRUM_INGOT.get()), List.of(new ArmorMaterial.Layer(new ResourceLocation("cloggrum"))), 1.0F, 0.0F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> FROSTSTEEL = ARMOR_MATERIALS.register("froststeel", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 15, SoundEvents.ARMOR_EQUIP_GOLD, () -> Ingredient.of(UGItems.FROSTSTEEL_INGOT.get()), List.of(new ArmorMaterial.Layer(new ResourceLocation("froststeel"))), 4.0F, 0.05F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> UTHERIUM = ARMOR_MATERIALS.register("utherium", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 8);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(UGItems.UTHERIUM_CRYSTAL.get()), List.of(new ArmorMaterial.Layer(new ResourceLocation("utherium"))), 3.0F, 0.0F)
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ANCIENT = ARMOR_MATERIALS.register("ancient", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 5);
		map.put(ArmorItem.Type.CHESTPLATE, 6);
		map.put(ArmorItem.Type.HELMET, 2);
		}), 0, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(new ResourceLocation("ancient"))), 0.0F, 0.0F)
	);
}