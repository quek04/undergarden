package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public abstract class AbstractUndergardenStructure extends Structure<NoFeatureConfig> {

    public AbstractUndergardenStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }
}
