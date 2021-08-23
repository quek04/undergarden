package quek.undergarden.registry;

import net.minecraft.item.crafting.Ingredient;

public enum UGShieldTiers {
    CLOGGRUM(672, Ingredient.of(UGItems.CLOGGRUM_INGOT.get())),
    ;

    int maxUses;
    Ingredient repairMaterial;

    UGShieldTiers(int uses, Ingredient material) {
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