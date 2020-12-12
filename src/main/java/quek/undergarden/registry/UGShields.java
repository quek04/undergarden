package quek.undergarden.registry;

import net.minecraft.item.crafting.Ingredient;

public enum UGShields {
    CLOGGRUM(672, Ingredient.fromItems(UGItems.CLOGGRUM_INGOT.get())),
    ;

    int maxUses;
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
