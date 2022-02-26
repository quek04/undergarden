package quek.undergarden.registry;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum UGShieldTiers {
    CLOGGRUM(672, () -> Ingredient.of(UGItems.CLOGGRUM_INGOT.get())),
    ;

    private final int durability;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    UGShieldTiers(int durability, Supplier<Ingredient> repairIngredient) {
        this.durability = durability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurability() {
        return this.durability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}