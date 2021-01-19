package quek.undergarden.world.dimension;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGBiomeProvider extends NetherBiomeProvider {

    public static final MapCodec<NetherBiomeProvider> PACKET_CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(SeedBearer::giveMeSeed)
                            .forGetter((netherProvider) -> netherProvider.seed),
                    RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create(
                            (biomeAttributes) -> biomeAttributes.group(
                                    Biome.Attributes.CODEC.fieldOf("parameters")
                                            .forGetter(Pair::getFirst),
                                    Biome.BIOME_CODEC.fieldOf("biome")
                                            .forGetter(Pair::getSecond))
                                    .apply(biomeAttributes, Pair::of))
                            .listOf().fieldOf("biomes")
                            .forGetter((netherProvider) -> netherProvider.biomeAttributes),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("temperature_noise")
                            .forGetter((netherProvider) -> netherProvider.temperatureNoise),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("humidity_noise")
                            .forGetter((netherProvider) -> netherProvider.humidityNoise),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("altitude_noise")
                            .forGetter((netherProvider) -> netherProvider.altitudeNoise),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise")
                            .forGetter((netherProvider) -> netherProvider.weirdnessNoise))
                    .apply(instance, NetherBiomeProvider::new));

    public static final Codec<NetherBiomeProvider> CODEC = Codec.mapEither(DefaultBuilder.CODEC, PACKET_CODEC).xmap((either) -> {
        return either.map(DefaultBuilder::build, Function.identity());
    }, (netherProvider) -> {
        return netherProvider.getDefaultBuilder().map(Either::<DefaultBuilder, NetherBiomeProvider>left).orElseGet(() -> {
            return Either.right(netherProvider);
        });
    }).codec();

    private UGBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, NetherBiomeProvider.Preset>> netherProviderPreset) {
        super(seed, biomeAttributes, netherProviderPreset);
    }

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BiomeProvider getBiomeProvider(long seed) {
        return new UGBiomeProvider(seed, this.biomeAttributes, this.netherProviderPreset);
    }
}