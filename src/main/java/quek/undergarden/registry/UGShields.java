package quek.undergarden.registry;

import net.minecraft.item.crafting.Ingredient;

public enum UGShields {
    CLOGGRUM(672, Ingredient.fromItems(UGItems.cloggrum_ingot.get())),
    ;

    int maxUses = 0;
    Ingredient repairMaterial;

    UGShields(int uses, Ingredient material) {
        maxUses = uses;
        repairMaterial = material;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public Ingredient getRepairMaterial() {
        return repairMaterial;
    }
}
