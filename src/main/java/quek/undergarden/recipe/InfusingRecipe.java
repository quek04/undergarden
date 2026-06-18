package quek.undergarden.recipe;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGRecipeBookCategories;
import quek.undergarden.registry.UGRecipeTypes;
import quek.undergarden.registry.UGTags;

import java.util.Locale;
import java.util.function.IntFunction;

public interface InfusingRecipe extends Recipe<InfuserInput> {

	Ingredient input();

	InfusingBookCategory category();

	SlotType getRecipeSlotType();

	float experience();

	int infusingTime();

	@Override
	default boolean matches(InfuserInput input, Level level) {
		return this.input().test(input.item()) && this.getRecipeSlotType().equals(input.type());
	}

	@Override
	default RecipeType<? extends Recipe<InfuserInput>> getType() {
		return UGRecipeTypes.INFUSING.get();
	}

	@Override
	default RecipeBookCategory recipeBookCategory() {
		return switch (this.category()) {
			case CORRUPTING -> UGRecipeBookCategories.INFUSER_CORRUPTING.get();
			case PURIFYING -> UGRecipeBookCategories.INFUSER_PURIFYING.get();
			case MISC -> UGRecipeBookCategories.INFUSER_MISC.get();
		};
	}

	@Override
	default boolean isSpecial() {
		return true;
	}

	enum SlotType implements StringRepresentable {
		UTHERIUM(1, UGTags.Items.INFUSER_UTHERIUM_FUELS),
		ROGDORIUM(2, UGTags.Items.INFUSER_ROGDORIUM_FUELS);

		private static final IntFunction<SlotType> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
		public static final Codec<SlotType> CODEC = StringRepresentable.fromEnum(SlotType::values);
		public static final StreamCodec<ByteBuf, SlotType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Enum::ordinal);

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
