package quek.undergarden.world.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.function.Supplier;

public class UGChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<UGChunkGenerator> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                            RegistryLookupCodec.create(Registry.NOISE_REGISTRY)
                                    .forGetter((chunkGenerator -> chunkGenerator.noises)),
                            BiomeSource.CODEC.fieldOf("biome_source")
                                    .forGetter((chunkGenerator) -> chunkGenerator.biomeSource),
                            Codec.LONG.fieldOf("seed")
                                    .orElseGet(SeedBearer::giveMeSeed)
                                    .forGetter((chunkGenerator) -> chunkGenerator.seed),
                            NoiseGeneratorSettings.CODEC.fieldOf("settings")
                                    .forGetter((chunkGenerator) -> chunkGenerator.settings))
                    .apply(instance, instance.stable(UGChunkGenerator::new)));

    public UGChunkGenerator(Registry<NormalNoise.NoiseParameters> noiseParameters, BiomeSource biomeSource, long seed, Supplier<NoiseGeneratorSettings> noiseGeneratorSettings) {
        super(noiseParameters, biomeSource, seed, noiseGeneratorSettings);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new UGChunkGenerator(this.noises, this.biomeSource.withSeed(seed), seed, this.settings);
    }
}