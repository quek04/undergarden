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

public record InfuserConversionRecipe(InfusingBookCategory category, Ingredient ingredient, ItemStack result, int infusingTime, float experience, SlotType slotType) implements InfusingRecipe {

	@Override
	public boolean matches(SingleRecipeInput input, Level level) {
		return this.ingredient().test(input.item());
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
		return this.result().copy();
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return this.result();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return UGRecipeSerializers.INFUSER_CONVERSION.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(Ingredient.EMPTY, this.ingredient());
	}

	@Override
	public SlotType getRecipeSlotType() {
		return this.slotType();
	}

	public static class Serializer implements RecipeSerializer<InfuserConversionRecipe> {

		private static final MapCodec<InfuserConversionRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
				InfusingBookCategory.CODEC.fieldOf("category").orElse(InfusingBookCategory.MISC).forGetter(recipe -> recipe.category),
				Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
				ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
				Codec.INT.fieldOf("infusing_time").orElse(200).forGetter(recipe -> recipe.infusingTime),
				Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(recipe -> recipe.experience),
				SlotType.CODEC.fieldOf("slot_type").forGetter(recipe -> recipe.slotType))
			.apply(instance, InfuserConversionRecipe::new));

		private static final StreamCodec<RegistryFriendlyByteBuf, InfuserConversionRecipe> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.fromCodec(InfusingBookCategory.CODEC), InfuserConversionRecipe::category,
			Ingredient.CONTENTS_STREAM_CODEC, InfuserConversionRecipe::ingredient,
			ItemStack.STREAM_CODEC, InfuserConversionRecipe::result,
			ByteBufCodecs.INT, InfuserConversionRecipe::infusingTime,
			ByteBufCodecs.FLOAT, InfuserConversionRecipe::experience,
			ByteBufCodecs.fromCodec(SlotType.CODEC), InfuserConversionRecipe::slotType,
			InfuserConversionRecipe::new
		);

		@Override
		public MapCodec<InfuserConversionRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, InfuserConversionRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}