package quek.undergarden.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record RogdoriumInfusion(int infusionAmount, int infusionMax) implements TooltipProvider {
	public static final RogdoriumInfusion DEFAULT = new RogdoriumInfusion(0, 3600);
	public static final Codec<RogdoriumInfusion> CODEC = RecordCodecBuilder.create(instance ->
		instance.group(
			Codec.INT.fieldOf("infusionAmount").forGetter(RogdoriumInfusion::infusionAmount),
			Codec.INT.fieldOf("infusionMax").forGetter(RogdoriumInfusion::infusionMax)
		).apply(instance, RogdoriumInfusion::new)
	);

	public static final StreamCodec<? super RegistryFriendlyByteBuf, RogdoriumInfusion> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.INT, RogdoriumInfusion::infusionAmount,
		ByteBufCodecs.INT, RogdoriumInfusion::infusionMax,
		RogdoriumInfusion::new
	);

	public static RogdoriumInfusion setInfusionAmount(int amount) {
		return new RogdoriumInfusion(amount, DEFAULT.infusionMax());
	}

	public static RogdoriumInfusion setInfusionMax(int max) {
		return new RogdoriumInfusion(DEFAULT.infusionAmount(), max);
	}

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
		if (infusionAmount > 0) {
			consumer.accept(Component.translatable("tooltip.undergarden.rogdorium_infusion").append(": " + infusionAmount + "/" + infusionMax).withStyle(ChatFormatting.AQUA));
		}
	}
}