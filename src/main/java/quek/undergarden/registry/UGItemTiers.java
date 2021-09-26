package quek.undergarden.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;

import java.util.function.Supplier;

public enum UGItemTiers implements Tier {
    CLOGGRUM(3, 286, 6, 4, 10, () -> {return Ingredient.of(UGItems.CLOGGRUM_INGOT.get());}),
    FROSTSTEEL(3, 575, 6, 3, 15, () -> {return Ingredient.of(UGItems.FROSTSTEEL_INGOT.get());}),
    UTHERIUM(4, 1279, 8, 3.5F, 13, () -> {return Ingredient.of(UGItems.UTHERIUM_INGOT.get());}),
    FORGOTTEN(4, 1565, 10, 6, 0, () -> {return Ingredient.of(UGItems.FORGOTTEN_INGOT.get());})
    ;

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    UGItemTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}