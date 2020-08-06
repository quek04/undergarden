package quek.undergarden.biome;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenSoundEvents;

import java.awt.*;

public abstract class UndergardenBiome extends Biome {

    public UndergardenBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp, int skyColor, SoundEvent ambiance, ParticleEffectAmbience ambientParticle) {
        super((new Biome.Builder())
                .surfaceBuilder(surface, config)
                .precipitation(RainType.NONE)
                .category(category)
                .depth(depth)
                .scale(scale)
                .temperature(temp)
                .downfall(0)
                .func_235097_a_((new BiomeAmbience.Builder())
                        .func_235240_a_(new BackgroundMusicSelector(UndergardenSoundEvents.UNDERGARDEN_MUSIC, 12000, 24000, true))//bgm
                        .func_235246_b_(342306)//water
                        .func_235248_c_(332810)//water fog
                        .func_235239_a_(skyColor)//sky fog
                        .func_235241_a_(ambiance)//ambiance
                        .func_235244_a_(ambientParticle)//ambient particle
                        .func_235238_a_())//probably .build()
                .func_235098_a_(ImmutableList.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F)))
                .parent((null))
        );
    }

    public abstract void addFeatures();

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return new Color(0, 0, 0).getRGB();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(double posX, double posZ) {
        return new Color(91, 117, 91).getRGB();
    }

}
