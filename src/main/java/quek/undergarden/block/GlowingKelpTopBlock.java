package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.block.KelpTopBlock;
import quek.undergarden.registry.UGBlocks;

import net.minecraft.block.AbstractBlock.Properties;

public class GlowingKelpTopBlock extends KelpTopBlock {

    public GlowingKelpTopBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected Block getBodyBlock() {
        return UGBlocks.GLOWING_KELP_PLANT.get();
    }
}