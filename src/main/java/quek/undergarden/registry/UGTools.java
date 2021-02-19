package quek.undergarden.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum UGTools implements IItemTier {
    CLOGGRUM(286,5,4,2, 10, () -> {return Ingredient.fromItems(UGItems.CLOGGRUM_INGOT.get());}),
    FROSTSTEEL(575, 6, 3, 3, 15, () -> {return Ingredient.fromItems(UGItems.FROSTSTEEL_INGOT.get());}),
    UTHERIC(1279,8,3.5F,4,13, () -> {return Ingredient.fromItems(UGItems.UTHERIUM_INGOT.get());}),
    FORGOTTEN(127, 10, 6, 4, 0, () -> {return Ingredient.fromItems(UGItems.FORGOTTEN_INGOT.get());})
    ;

    int maxUses;
    float toolEfficiency;
    float attackDamage;
    int harvestLvl;
    int enchantability;
    LazyValue<Ingredient> repairMaterial;

    UGTools(int uses, float efficiency, float damage, int harvest, int enchant, Supplier<Ingredient> material) {
        maxUses = uses;
        toolEfficiency = efficiency;
        attackDamage = damage;
        harvestLvl = harvest;
        enchantability = enchant;
        repairMaterial = new LazyValue<>(material);
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return toolEfficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLvl;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}