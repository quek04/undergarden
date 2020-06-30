package quek.undergarden.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum UndergardenItemTiers implements IItemTier {
    SMOGSTEM(127,2,1,1, 15, Ingredient.fromTag(UndergardenTags.Items.SMOGSTEM_PLANKS)),
    CLOGGRUM(286,5,4,2, 10, Ingredient.fromItems(UndergardenItems.cloggrum_ingot.get())),
    FROSTSTEEL(575, 6, 3, 3, 15, Ingredient.fromItems(UndergardenItems.froststeel_ingot.get())),
    UTHERIC(1279,8,3.5F,4,13, Ingredient.fromItems(UndergardenItems.utherium_ingot.get()))
    ;

    int maxUses;
    float toolEfficiency;
    float attackDamage;
    int harvestLvl;
    int enchantability;
    Ingredient repairMaterial;

    UndergardenItemTiers(int uses, float efficiency, float damage, int harvest, int enchant, Ingredient material) {
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
