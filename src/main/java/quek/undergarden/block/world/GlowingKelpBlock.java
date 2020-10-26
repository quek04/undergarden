package quek.undergarden.block.world;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.KelpBlock;
import quek.undergarden.registry.UGBlocks;

public class GlowingKelpBlock extends KelpBlock {

    public GlowingKelpBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return UGBlocks.glowing_kelp.get();
    }
}
