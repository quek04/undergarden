package quek.undergarden.recipe;

import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGRecipeTypes;
import quek.undergarden.registry.UGTags;

import java.util.Locale;

public interface InfusingRecipe extends Recipe<SingleRecipeInput> {

	SlotType getRecipeSlotType();

	float experience();

	int infusingTime();

	InfusingBookCategory category();

	@Override
	default boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	default ItemStack getToastSymbol() {
		return new ItemStack(UGBlocks.INFUSER);
	}

	@Override
	default RecipeType<?> getType() {
		return UGRecipeTypes.INFUSING.get();
	}

	@Override
	default boolean isSpecial() {
		return true;
	}

	enum SlotType implements StringRepresentable {
		UTHERIUM(1, UGTags.Items.INFUSER_UTHERIUM_FUELS),
		ROGDORIUM(2, UGTags.Items.INFUSER_ROGDORIUM_FUELS);

		public static final StringRepresentable.EnumCodec<SlotType> CODEC = StringRepresentable.fromEnum(SlotType::values);

		private final int slotIndex;
		private final TagKey<Item> validItems;

		SlotType(int slotIndex, TagKey<Item> validItems) {
			this.slotIndex = slotIndex;
			this.validItems = validItems;
		}

		public int getSlotIndex() {
			return this.slotIndex;
		}

		public TagKey<Item> getValidItems() {
			return this.validItems;
		}

		@Override
		public String getSerializedName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
