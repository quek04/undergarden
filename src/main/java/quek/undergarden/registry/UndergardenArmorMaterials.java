package quek.undergarden.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum UndergardenArmorMaterials implements IArmorMaterial {
    CLOGGRUM("cloggrum", 10, new int[]{3, 6, 6, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1, () -> Ingredient.fromItems(UndergardenItems.cloggrum_ingot.get())),
    MASTICATED("masticated", 17, new int[]{6, 6, 6, 6}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F, () -> Ingredient.fromItems(UndergardenItems.masticator_scales.get()))
    ;

    private static final int[] durability_arr = new int[]{13, 15, 16, 11};
    private final String armorName;
    private final int durabilityFactor;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float armorToughness;
    private final Supplier<Ingredient> repairMaterial;

    UndergardenArmorMaterials(String name, int durability, int[] reduction, int enchant, SoundEvent sound, float toughness, Supplier<Ingredient> material) {
        armorName = name;
        durabilityFactor = durability;
        damageReduction = reduction;
        enchantability = enchant;
        equipSound = sound;
        armorToughness = toughness;
        repairMaterial = material;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return durability_arr[slotIn.getIndex()] * durabilityFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReduction[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }

    @Override
    public String getName() {
        return armorName;
    }

    @Override
    public float getToughness() {
        return armorToughness;
    }
}
