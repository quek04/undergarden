package quek.undergarden.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;
import quek.undergarden.registry.UGBlocks;

public class GlowingKelpTopBlock extends KelpBlock {

    public GlowingKelpTopBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected Block getBodyBlock() {
        return UGBlocks.GLOWING_KELP_PLANT.get();
    }
}