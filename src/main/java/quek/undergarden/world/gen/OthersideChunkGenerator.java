package quek.undergarden.world.gen;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;

public class OthersideChunkGenerator extends NoiseChunkGenerator<OthersideGenerationSettings> {

    public OthersideChunkGenerator(IWorld worldIn, BiomeProvider biomeProviderIn, OthersideGenerationSettings settingsIn) {
        super(worldIn, biomeProviderIn, 2, 8, 128, settingsIn, false);
    }

    @Override
    protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
        return new double[]{(double)this.biomeProvider.func_222365_c(noiseX, noiseZ), 0.0D};
    }

    @Override
    protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
        return 10;
    }

    //height
    @Override
    protected double func_222551_g() {
        return (int)super.func_222551_g() / 1.5D;
    }

    //spacing
    @Override
    protected double func_222553_h() {
        return 4.0D;
    }

    @Override
    protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
        this.calcNoiseColumn(noiseColumn, noiseX, noiseZ, 1368.824D, 684.412D, 17.110300000000002D, 4.277575000000001D, 64, -3000);
    }

    @Override
    public int getGroundHeight() {
        return 0;
    }

    @Override
    public int getSeaLevel() {
        return -1;
    }
}
