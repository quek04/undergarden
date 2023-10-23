package quek.undergarden.world.gen;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.jetbrains.annotations.NotNull;

public class UGNoiseBasedChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<UGNoiseBasedChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter((chunkGenerator) -> chunkGenerator.biomeSource),
                    NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((chunkGenerator) -> chunkGenerator.settings)
            ).apply(instance, instance.stable(UGNoiseBasedChunkGenerator::new)));
    public UGNoiseBasedChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        super(biomeSource, settings);
        super.globalFluidPicker = Suppliers.memoize(() -> createFluidPicker(settings.value()));
    }

    private static Aquifer.FluidPicker createFluidPicker(NoiseGeneratorSettings pSettings) {
        int seaLevel = pSettings.seaLevel();
        Aquifer.FluidStatus air = new Aquifer.FluidStatus(0, Blocks.AIR.defaultBlockState());
        Aquifer.FluidStatus water = new Aquifer.FluidStatus(seaLevel, pSettings.defaultFluid());
        return (x, y, z) -> y <= 0 ? air : water;
    }

    @Override
    protected @NotNull Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }
}
