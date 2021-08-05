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

import net.minecraft.world.biome.provider.NetherBiomeProvider.DefaultBuilder;

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
                                    Biome.CODEC.fieldOf("biome")
                                            .forGetter(Pair::getSecond))
                                    .apply(biomeAttributes, Pair::of))
                            .listOf().fieldOf("biomes")
                            .forGetter((netherProvider) -> netherProvider.parameters),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("temperature_noise")
                            .forGetter((netherProvider) -> netherProvider.temperatureParams),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("humidity_noise")
                            .forGetter((netherProvider) -> netherProvider.humidityParams),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("altitude_noise")
                            .forGetter((netherProvider) -> netherProvider.altitudeParams),
                    NetherBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise")
                            .forGetter((netherProvider) -> netherProvider.weirdnessParams))
                    .apply(instance, NetherBiomeProvider::new));

    public static final Codec<NetherBiomeProvider> CODEC = Codec.mapEither(DefaultBuilder.CODEC, PACKET_CODEC).xmap((either) ->
            either.map(DefaultBuilder::biomeSource, Function.identity()), (netherProvider) ->
            netherProvider.preset().map(Either::<DefaultBuilder, NetherBiomeProvider>left).orElseGet(() ->
                    Either.right(netherProvider))).codec();

    private UGBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, NetherBiomeProvider.Preset>> netherProviderPreset) {
        super(seed, biomeAttributes, netherProviderPreset);
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BiomeProvider withSeed(long seed) {
        return new UGBiomeProvider(seed, this.parameters, this.preset);
    }
}