package quek.undergarden.registry;

import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum UGItemTiers implements Tier {
	CLOGGRUM( 286, 6.0F, 3.0F, 8, () -> Ingredient.of(UGItems.CLOGGRUM_INGOT.get()), BlockTags.INCORRECT_FOR_IRON_TOOL),
	FROSTSTEEL( 575, 7.0F, 2.0F, 20, () -> Ingredient.of(UGItems.FROSTSTEEL_INGOT.get()), BlockTags.INCORRECT_FOR_IRON_TOOL),
	UTHERIUM( 1279, 8.5F, 3.5F, 17, () -> Ingredient.of(UGItems.UTHERIUM_CRYSTAL.get()), BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
	FORGOTTEN( 1876, 8.0F, 3.0F, 2, () -> Ingredient.of(UGItems.FORGOTTEN_INGOT.get()), BlockTags.INCORRECT_FOR_NETHERITE_TOOL);

	private final int durability;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Supplier<Ingredient> repairIngredient;
	private final TagKey<Block> incorrectTag;

	UGItemTiers(int durability, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Block> incorrectTag) {
		this.durability = durability;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = Suppliers.memoize(repairIngredient::get);
		this.incorrectTag = incorrectTag;
	}

	public int getUses() {
		return this.durability;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getAttackDamageBonus() {
		return this.damage;
	}

	@Override
	public TagKey<Block> getIncorrectBlocksForDrops() {
		return this.incorrectTag;
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}