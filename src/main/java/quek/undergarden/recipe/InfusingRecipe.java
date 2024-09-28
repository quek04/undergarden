package quek.undergarden.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGRecipeSerializers;
import quek.undergarden.registry.UGRecipeTypes;

public class InfusingRecipe implements Recipe<InfuserRecipeInput> {

	protected final InfusingBookCategory category;
	protected final String group;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int infusingTime;
	protected final boolean utheriumFuel;

	public InfusingRecipe(String group, InfusingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int infusingTime, boolean fuelType) {
		this.category = category;
		this.group = group;
		this.ingredient = ingredient;
		this.result = result;
		this.experience = experience;
		this.infusingTime = infusingTime;
		this.utheriumFuel = fuelType;
	}

	public InfusingBookCategory getCategory() {
		return category;
	}

	public int getInfusingTime() {
		return infusingTime;
	}

	public boolean isUtheriumFuel() {
		return utheriumFuel;
	}

	public float getExperience() {
		return experience;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(UGBlocks.INFUSER);
	}

	@Override
	public boolean matches(InfuserRecipeInput input, Level level) {
		return this.ingredient.test(input.item()) && this.utheriumFuel == input.isUtheriumFuel();
	}

	@Override
	public ItemStack assemble(InfuserRecipeInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return this.result;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return UGRecipeSerializers.INFUSING.get();
	}

	@Override
	public RecipeType<?> getType() {
		return UGRecipeTypes.INFUSING.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> list = NonNullList.create();
		list.add(this.ingredient);
		return list;
	}

	public static class Serializer implements RecipeSerializer<InfusingRecipe> {

		private final MapCodec<InfusingRecipe> codec;
		private final StreamCodec<RegistryFriendlyByteBuf, InfusingRecipe> streamCodec;

		public Serializer() {
			this.codec = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
					Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
					InfusingBookCategory.CODEC.fieldOf("category").orElse(InfusingBookCategory.MISC).forGetter(recipe -> recipe.category),
					Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
					ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
					Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(recipe -> recipe.experience),
					Codec.INT.fieldOf("infusingTime").orElse(200).forGetter(recipe -> recipe.infusingTime),
					Codec.BOOL.fieldOf("utheriumFuel").forGetter(recipe -> recipe.utheriumFuel)
				).apply(instance, InfusingRecipe::new)
			);
			this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
		}

		@Override
		public MapCodec<InfusingRecipe> codec() {
			return this.codec;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, InfusingRecipe> streamCodec() {
			return this.streamCodec;
		}

		private InfusingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
			String group = buf.readUtf();
			InfusingBookCategory category = buf.readEnum(InfusingBookCategory.class);
			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
			ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
			float experience = buf.readFloat();
			int infusingTime = buf.readInt();
			boolean utheriumFuel = buf.readBoolean();
			return new InfusingRecipe(group, category, ingredient, result, experience, infusingTime, utheriumFuel);
		}

		private void toNetwork(RegistryFriendlyByteBuf buf, InfusingRecipe recipe) {
			buf.writeUtf(recipe.group);
			buf.writeEnum(recipe.category);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ingredient);
			ItemStack.STREAM_CODEC.encode(buf, recipe.result);
			buf.writeFloat(recipe.experience);
			buf.writeInt(recipe.infusingTime);
			buf.writeBoolean(recipe.utheriumFuel);
		}
	}
}