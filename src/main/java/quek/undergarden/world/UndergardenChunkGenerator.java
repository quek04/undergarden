package quek.undergarden.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;

public class UndergardenChunkGenerator extends NoiseChunkGenerator<UndergardenGenerationSettings> {

    public UndergardenChunkGenerator(World world, BiomeProvider biomeProvider, UndergardenGenerationSettings undergardenGenerationSettings) {
        super(world, biomeProvider, 4, 8, 256, undergardenGenerationSettings, false);
    }

    @Override
    protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
        return new double[]{0.0D, 0.0D};
    }

    @Override
    protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
        return 0;
    }

    @Override
    protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
        double d0 = 684.412D;
        double d1 = 2053.236D;
        double d2 = 8.555150000000001D;
        double d3 = 34.2206D;
        int i = -10;
        int j = 3;
        this.calcNoiseColumn(noiseColumn, noiseX, noiseZ, 684.412D, 2053.236D, 8.555150000000001D, 34.2206D, 3, -10);
    }

    @Override
    public int getGroundHeight() {
        return this.world.getSeaLevel()+1;
    }

    @Override
    public int getMaxHeight() {
        return 256;
    }

    @Override
    public int getSeaLevel() {
        return UndergardenGenerationSettings.sea_level;
    }
}
