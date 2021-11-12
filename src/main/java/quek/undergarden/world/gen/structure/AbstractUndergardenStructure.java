package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

public abstract class AbstractUndergardenStructure extends StructureFeature<NoneFeatureConfiguration> {

    public AbstractUndergardenStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }
}