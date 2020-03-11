package quek.undergarden.world;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;
import quek.undergarden.registry.UndergardenBlocks;

public class UndergardenGenerationSettings extends GenerationSettings {

    public UndergardenGenerationSettings() {
        setDefaultBlock(UndergardenBlocks.depthrock.get().getDefaultState());
        setDefaultFluid(Blocks.WATER.getDefaultState());
    }

    public static int sea_level = 32;

    public int getBedrockRoofHeight() {
        return 256;
    }

    public int getBedrockFloorHeight() {
        return 0;
    }

}
