package quek.undergarden.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGRecipeSerializers;

public class ItemInfusingRecipe extends SimpleInfusingRecipe {

	private static final MapCodec<ItemInfusingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
				InfusingBookCategory.CODEC.optionalFieldOf("category", InfusingBookCategory.MISC).forGetter(SimpleInfusingRecipe::category),
			Ingredient.CODEC.fieldOf("input").forGetter(SimpleInfusingRecipe::input),
			Codec.INT.optionalFieldOf("infusing_time", 200).forGetter(SimpleInfusingRecipe::infusingTime),
			Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(SimpleInfusingRecipe::experience))
		.apply(instance, ItemInfusingRecipe::new));

	private static final StreamCodec<RegistryFriendlyByteBuf, ItemInfusingRecipe> STREAM_CODEC = StreamCodec.composite(
		CommonInfo.STREAM_CODEC, o -> o.commonInfo,
		ByteBufCodecs.fromCodec(InfusingBookCategory.CODEC), SimpleInfusingRecipe::category,
		Ingredient.CONTENTS_STREAM_CODEC, SimpleInfusingRecipe::input,
		ByteBufCodecs.INT, SimpleInfusingRecipe::infusingTime,
		ByteBufCodecs.FLOAT, SimpleInfusingRecipe::experience,
		ItemInfusingRecipe::new
	);
	public static final RecipeSerializer<ItemInfusingRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

	public ItemInfusingRecipe(CommonInfo commonInfo, InfusingBookCategory category, Ingredient input, int infusingTime, float experience) {
		super(commonInfo, category, input, infusingTime, experience);
	}

	@Override
	public boolean matches(SingleRecipeInput input, Level level) {
		return this.input().test(input.item());
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input) {
		return input.item().copy();
	}

	@Override
	public SlotType getRecipeSlotType() {
		return SlotType.ROGDORIUM;
	}

	@Override
	public RecipeSerializer<? extends SimpleInfusingRecipe> getSerializer() {
		return UGRecipeSerializers.ITEM_INFUSING.get();
	}
}