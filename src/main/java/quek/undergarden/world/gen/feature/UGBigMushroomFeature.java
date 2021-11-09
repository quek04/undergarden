package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

import java.util.Random;

public abstract class UGBigMushroomFeature extends AbstractHugeMushroomFeature {

    public UGBigMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(Random random) {
        int i = random.nextInt(6) + 6;
        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        return i;
    }
}