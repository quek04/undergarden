package quek.undergarden.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.function.Supplier;

public enum UGArmors implements ArmorMaterial {
    CLOGGRUM("cloggrum", 10, new int[]{3, 6, 6, 3}, 10, SoundEvents.ARMOR_EQUIP_IRON, 1, () -> Ingredient.of(UGItems.CLOGGRUM_INGOT.get())),
    FROSTSTEEL("froststeel", 20, new int[]{2, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 4, () -> Ingredient.of(UGItems.FROSTSTEEL_INGOT.get())),
    UTHERIUM("utherium", 36, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 3, () -> Ingredient.of(UGItems.UTHERIUM_CRYSTAL.get())),
    MASTICATED("masticated", 17, new int[]{6, 6, 6, 6}, 5, SoundEvents.ARMOR_EQUIP_DIAMOND, 0, () -> Ingredient.of(UGItems.MASTICATOR_SCALES.get()))
    ;

    private static final int[] durability_arr = new int[]{13, 15, 16, 11};
    private final String armorName;
    private final int durabilityFactor;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float armorToughness;
    private final Supplier<Ingredient> repairMaterial;

    UGArmors(String name, int durability, int[] reduction, int enchant, SoundEvent sound, float toughness, Supplier<Ingredient> material) {
        armorName = name;
        durabilityFactor = durability;
        damageReduction = reduction;
        enchantability = enchant;
        equipSound = sound;
        armorToughness = toughness;
        repairMaterial = material;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slotIn) {
        return durability_arr[slotIn.getIndex()] * durabilityFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slotIn) {
        return damageReduction[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
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

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}