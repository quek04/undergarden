package quek.undergarden.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;

public record UtheriumCrystalConfiguration(ColumnFeatureConfiguration clusterConfig, LargeDripstoneConfiguration crystalConfig, float crystalChance) implements FeatureConfiguration {
	public static final Codec<UtheriumCrystalConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			ColumnFeatureConfiguration.CODEC.fieldOf("cluster").forGetter(UtheriumCrystalConfiguration::clusterConfig),
			LargeDripstoneConfiguration.CODEC.fieldOf("crystal").forGetter(UtheriumCrystalConfiguration::crystalConfig),
			Codec.floatRange(0.0F, 1.0F).fieldOf("crystal_chance").forGetter(UtheriumCrystalConfiguration::crystalChance)
	).apply(instance, UtheriumCrystalConfiguration::new));
}
