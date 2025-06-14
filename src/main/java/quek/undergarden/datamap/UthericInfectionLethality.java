package quek.undergarden.datamap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record UthericInfectionLethality(float lethality) {
	public static final Codec<UthericInfectionLethality> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.floatRange(0.0F, 1.0F).fieldOf("lethality").forGetter(UthericInfectionLethality::lethality)
	).apply(instance, UthericInfectionLethality::new));
}