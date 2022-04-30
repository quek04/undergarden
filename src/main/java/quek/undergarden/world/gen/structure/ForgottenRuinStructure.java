package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class ForgottenRuinStructure extends AllYLevelStructure {

    public ForgottenRuinStructure(Codec<JigsawConfiguration> codec) {
        super(codec, AllYLevelStructure::placement);
    }
}