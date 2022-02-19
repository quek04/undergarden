package quek.undergarden.registry;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum UGItemTiers implements Tier {
    CLOGGRUM(2, 286, 6.0F, 3.0F, 8, () -> Ingredient.of(UGItems.CLOGGRUM_INGOT.get())),
    FROSTSTEEL(2, 575, 7.0F, 2.0F, 20, () -> Ingredient.of(UGItems.FROSTSTEEL_INGOT.get())),
    UTHERIUM(3, 1279, 8.5F, 3.5F, 17, () -> Ingredient.of(UGItems.UTHERIUM_CRYSTAL.get())),
    FORGOTTEN(4, 1876, 8.0F, 3.0F, 2, () -> Ingredient.of(UGItems.FORGOTTEN_INGOT.get()))
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