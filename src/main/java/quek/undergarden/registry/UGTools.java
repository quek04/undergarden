package quek.undergarden.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum UGTools implements IItemTier {
    CLOGGRUM(286,5,4,2, 10, () -> {return Ingredient.of(UGItems.CLOGGRUM_INGOT.get());}),
    FROSTSTEEL(575, 6, 3, 3, 15, () -> {return Ingredient.of(UGItems.FROSTSTEEL_INGOT.get());}),
    UTHERIC(1279,8,3.5F,4,13, () -> {return Ingredient.of(UGItems.UTHERIUM_INGOT.get());}),
    FORGOTTEN(1565, 10, 6, 4, 0, () -> {return Ingredient.of(UGItems.FORGOTTEN_INGOT.get());})
    ;

    int maxUses;
    float toolEfficiency;
    float attackDamage;
    int harvestLvl;
    int enchantability;
    LazyValue<Ingredient> repairMaterial;

    UGTools(int durability, float efficiency, float attack, int harvestLevel, int enchant, Supplier<Ingredient> material) {
        maxUses = durability;
        toolEfficiency = efficiency;
        attackDamage = attack;
        harvestLvl = harvestLevel;
        enchantability = enchant;
        repairMaterial = new LazyValue<>(material);
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return toolEfficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLvl;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}