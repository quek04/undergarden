package quek.undergarden.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGRecipeSerializers;

public record ItemInfusingRecipe(InfusingBookCategory category, Ingredient infusableItems, int infusingTime, float experience) implements InfusingRecipe {

	@Override
	public boolean matches(SingleRecipeInput input, Level level) {
		return this.infusableItems().test(input.item());
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
		return input.item().copy();
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return ItemStack.EMPTY;
	}

	@Override
	public SlotType getRecipeSlotType() {
		return SlotType.ROGDORIUM;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(Ingredient.EMPTY, this.infusableItems());
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return UGRecipeSerializers.ITEM_INFUSING.get();
	}

	public static class Serializer implements RecipeSerializer<ItemInfusingRecipe> {

		private static final MapCodec<ItemInfusingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
				InfusingBookCategory.CODEC.optionalFieldOf("category", InfusingBookCategory.MISC).forGetter(recipe -> recipe.category),
				Ingredient.CODEC_NONEMPTY.fieldOf("items").forGetter(recipe -> recipe.infusableItems),
				Codec.INT.optionalFieldOf("infusing_time", 200).forGetter(recipe -> recipe.infusingTime),
				Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(recipe -> recipe.experience))
			.apply(instance, ItemInfusingRecipe::new));

		private final StreamCodec<RegistryFriendlyByteBuf, ItemInfusingRecipe> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.fromCodec(InfusingBookCategory.CODEC), ItemInfusingRecipe::category,
			Ingredient.CONTENTS_STREAM_CODEC, ItemInfusingRecipe::infusableItems,
			ByteBufCodecs.INT, ItemInfusingRecipe::infusingTime,
			ByteBufCodecs.FLOAT, ItemInfusingRecipe::experience,
			ItemInfusingRecipe::new
		);

		@Override
		public MapCodec<ItemInfusingRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ItemInfusingRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}