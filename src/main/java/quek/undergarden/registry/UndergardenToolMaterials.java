package quek.undergarden.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum UndergardenToolMaterials implements IItemTier {
    SMOGSTEM(127,2,3.5f,1, 15, Ingredient.fromItems()),
    CLOGGRUM(575,6,9,2, 14, Ingredient.fromItems(ItemRegistry.cloggrum_ingot.get())),
    ;

    int maxUses;
    float toolEfficiency;
    float attackDamage;
    int harvestLvl;
    int enchantability;
    Ingredient repairMaterial;

    UndergardenToolMaterials(int uses, float efficiency, float damage, int harvest, int enchant, Ingredient material) {
        maxUses = uses;
        toolEfficiency = efficiency;
        attackDamage = damage;
        harvestLvl = harvest;
        enchantability = enchant;
        repairMaterial = material;
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
        return repairMaterial;
    }
}
