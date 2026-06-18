package quek.undergarden.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import quek.undergarden.recipe.display.CatalystSlotDisplay;
import quek.undergarden.recipe.display.InfusingRecipeDisplay;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGRecipeSerializers;

import java.util.List;

public class InfuserConversionRecipe extends SimpleInfusingRecipe {

	private static final MapCodec<InfuserConversionRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
			InfusingBookCategory.CODEC.optionalFieldOf("category", InfusingBookCategory.MISC).forGetter(SimpleInfusingRecipe::category),
			Ingredient.CODEC.fieldOf("input").forGetter(SimpleInfusingRecipe::input),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result),
			Codec.INT.optionalFieldOf("infusing_time", 200).forGetter(SimpleInfusingRecipe::infusingTime),
			Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(SimpleInfusingRecipe::experience),
			SlotType.CODEC.fieldOf("slot_type").forGetter(recipe -> recipe.slotType))
		.apply(instance, InfuserConversionRecipe::new));

	private static final StreamCodec<RegistryFriendlyByteBuf, InfuserConversionRecipe> STREAM_CODEC = StreamCodec.composite(
		CommonInfo.STREAM_CODEC, o -> o.commonInfo,
		ByteBufCodecs.fromCodec(InfusingBookCategory.CODEC), SimpleInfusingRecipe::category,
		Ingredient.CONTENTS_STREAM_CODEC, SimpleInfusingRecipe::input,
		ItemStackTemplate.STREAM_CODEC, o -> o.result,
		ByteBufCodecs.INT, SimpleInfusingRecipe::infusingTime,
		ByteBufCodecs.FLOAT, SimpleInfusingRecipe::experience,
		SlotType.STREAM_CODEC, o -> o.slotType,
		InfuserConversionRecipe::new
	);
	public static final RecipeSerializer<InfuserConversionRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

	private final ItemStackTemplate result;
	private final SlotType slotType;

	public InfuserConversionRecipe(Recipe.CommonInfo commonInfo, InfusingBookCategory category, Ingredient ingredient, ItemStackTemplate result, int infusingTime, float experience, SlotType slotType) {
		super(commonInfo, category, ingredient, infusingTime, experience);
		this.result = result;
		this.slotType = slotType;
	}

	@Override
	public ItemStack assemble(InfuserInput input) {
		return this.result.create();
	}

	@Override
	public RecipeSerializer<? extends SimpleInfusingRecipe> getSerializer() {
		return UGRecipeSerializers.INFUSER_CONVERSION.get();
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new InfusingRecipeDisplay(
			this.input().display(),
			new CatalystSlotDisplay(new SlotDisplay.TagSlotDisplay(this.slotType.getValidItems()), this.slotType),
			new SlotDisplay.ItemStackSlotDisplay(this.result),
			new SlotDisplay.ItemSlotDisplay(UGBlocks.INFUSER.asItem())));
	}

	@Override
	public SlotType getRecipeSlotType() {
		return this.slotType;
	}
}