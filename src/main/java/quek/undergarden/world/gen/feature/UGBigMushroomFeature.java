package quek.undergarden.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.AbstractBigMushroomFeature;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;

import java.util.Random;

public abstract class UGBigMushroomFeature extends AbstractBigMushroomFeature {

    public UGBigMushroomFeature(Codec<BigMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override //stalk size
    protected int getTreeHeight(Random random) {
        int i = random.nextInt(6) + 6;
        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        return i;
    }
}
