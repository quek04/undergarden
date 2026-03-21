package quek.undergarden.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import quek.undergarden.registry.UGRecipeDisplays;

public record InfusingRecipeDisplay(SlotDisplay input, SlotDisplay catalyst, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

	public static final MapCodec<InfusingRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				SlotDisplay.CODEC.fieldOf("input").forGetter(InfusingRecipeDisplay::input),
				SlotDisplay.CODEC.fieldOf("catalyst").forGetter(InfusingRecipeDisplay::catalyst),
				SlotDisplay.CODEC.fieldOf("result").forGetter(InfusingRecipeDisplay::result),
				SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(InfusingRecipeDisplay::craftingStation)
			).apply(i, InfusingRecipeDisplay::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, InfusingRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC,
		InfusingRecipeDisplay::input,
		SlotDisplay.STREAM_CODEC,
		InfusingRecipeDisplay::catalyst,
		SlotDisplay.STREAM_CODEC,
		InfusingRecipeDisplay::result,
		SlotDisplay.STREAM_CODEC,
		InfusingRecipeDisplay::craftingStation,
		InfusingRecipeDisplay::new
	);
	public static final RecipeDisplay.Type<InfusingRecipeDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public Type<? extends RecipeDisplay> type() {
		return UGRecipeDisplays.INFUSING.get();
	}
}
