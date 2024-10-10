package quek.undergarden.datamap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record BiomeLethality(float lethality) {
	public static final Codec<BiomeLethality> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.floatRange(0.0F, 1.0F).fieldOf("lethality").forGetter(BiomeLethality::lethality)
	).apply(instance, BiomeLethality::new));
}