package quek.undergarden.recipe;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.ChatTypeDecoration;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
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
import java.util.function.IntFunction;

public interface InfusingRecipe extends Recipe<SingleRecipeInput> {

	SlotType getRecipeSlotType();

	float experience();

	int infusingTime();

	InfusingBookCategory category();

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
