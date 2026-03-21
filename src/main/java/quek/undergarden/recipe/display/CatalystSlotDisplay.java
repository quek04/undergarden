package quek.undergarden.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import quek.undergarden.recipe.InfusingRecipe;
import quek.undergarden.registry.UGSlotDisplays;

import java.util.stream.Stream;

public record CatalystSlotDisplay(SlotDisplay input, InfusingRecipe.SlotType slotType) implements SlotDisplay {

	public static final MapCodec<CatalystSlotDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				SlotDisplay.CODEC.fieldOf("input").forGetter(CatalystSlotDisplay::input),
				InfusingRecipe.SlotType.CODEC.fieldOf("slot_type").forGetter(CatalystSlotDisplay::slotType)
			)
			.apply(i, CatalystSlotDisplay::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, CatalystSlotDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC,
		CatalystSlotDisplay::input,
		InfusingRecipe.SlotType.STREAM_CODEC,
		CatalystSlotDisplay::slotType,
		CatalystSlotDisplay::new
	);
	public static final SlotDisplay.Type<CatalystSlotDisplay> TYPE = new SlotDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> builder) {
		return this.input.resolve(context, builder);
	}

	@Override
	public Type<? extends SlotDisplay> type() {
		return UGSlotDisplays.CATALYST.get();
	}
}
