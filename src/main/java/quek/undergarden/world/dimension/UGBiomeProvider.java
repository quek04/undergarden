package quek.undergarden.world.dimension;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.world.level.biome.MultiNoiseBiomeSource.PresetInstance;

public class UGBiomeProvider extends MultiNoiseBiomeSource {

    public static final MapCodec<MultiNoiseBiomeSource> PACKET_CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(SeedBearer::giveMeSeed)
                            .forGetter((netherProvider) -> netherProvider.seed),
                    RecordCodecBuilder.<Pair<Biome.ClimateParameters, Supplier<Biome>>>create(
                            (biomeAttributes) -> biomeAttributes.group(
                                    Biome.ClimateParameters.CODEC.fieldOf("parameters")
                                            .forGetter(Pair::getFirst),
                                    Biome.CODEC.fieldOf("biome")
                                            .forGetter(Pair::getSecond))
                                    .apply(biomeAttributes, Pair::of))
                            .listOf().fieldOf("biomes")
                            .forGetter((netherProvider) -> netherProvider.parameters),
                    MultiNoiseBiomeSource.NoiseParameters.CODEC.fieldOf("temperature_noise")
                            .forGetter((netherProvider) -> netherProvider.temperatureParams),
                    MultiNoiseBiomeSource.NoiseParameters.CODEC.fieldOf("humidity_noise")
                            .forGetter((netherProvider) -> netherProvider.humidityParams),
                    MultiNoiseBiomeSource.NoiseParameters.CODEC.fieldOf("altitude_noise")
                            .forGetter((netherProvider) -> netherProvider.altitudeParams),
                    MultiNoiseBiomeSource.NoiseParameters.CODEC.fieldOf("weirdness_noise")
                            .forGetter((netherProvider) -> netherProvider.weirdnessParams))
                    .apply(instance, MultiNoiseBiomeSource::new));

    public static final Codec<MultiNoiseBiomeSource> CODEC = Codec.mapEither(PresetInstance.CODEC, PACKET_CODEC).xmap((either) ->
            either.map(PresetInstance::biomeSource, Function.identity()), (netherProvider) ->
            netherProvider.preset().map(Either::<PresetInstance, MultiNoiseBiomeSource>left).orElseGet(() ->
                    Either.right(netherProvider))).codec();

    private UGBiomeProvider(long seed, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, MultiNoiseBiomeSource.Preset>> netherProviderPreset) {
        super(seed, biomeAttributes, netherProviderPreset);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BiomeSource withSeed(long seed) {
        return new UGBiomeProvider(seed, this.parameters, this.preset);
    }
}