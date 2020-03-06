package quek.undergarden.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum UndergardenToolMaterials implements IItemTier {
    //TODO: REBALANCE THIS SHIT
    SMOGSTEM(127,2,3,3, 15, Ingredient.fromTag(UndergardenTags.Items.SMOGSTEM_PLANKS)),
    CLOGGRUM(575,3,4,4, 14, Ingredient.fromItems(UndergardenItems.cloggrum_ingot.get())),
    UTHERIC(1279,4,5,5,13, Ingredient.fromItems(UndergardenItems.utheric_shard.get()))
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
