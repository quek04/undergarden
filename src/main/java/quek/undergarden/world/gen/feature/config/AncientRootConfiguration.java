package quek.undergarden.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record AncientRootConfiguration(int rogdoricProbability) implements FeatureConfiguration {
	public static final Codec<AncientRootConfiguration> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
				Codec.INT.fieldOf("rogdoric_probability").forGetter(config -> config.rogdoricProbability)
			)
			.apply(instance, AncientRootConfiguration::new)
	);
}