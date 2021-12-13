package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class CatacombsStructure extends JigsawFeature {

    public CatacombsStructure(Codec<JigsawConfiguration> codec) {
        super(codec, 33, false, false, (predicate) -> true);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public String getFeatureName() {
        return "undergarden:catacombs";
    }
}