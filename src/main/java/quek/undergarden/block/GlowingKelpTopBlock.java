package quek.undergarden.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.KelpTopBlock;
import quek.undergarden.registry.UGBlocks;

public class GlowingKelpTopBlock extends KelpTopBlock {

    public GlowingKelpTopBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    protected Block getBodyPlantBlock() {
        return UGBlocks.GLOWING_KELP_PLANT.get();
    }
}
