package quek.undergarden.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.KelpBlock;
import quek.undergarden.registry.UGBlocks;

public class GlowingKelpBlock extends KelpBlock {

    public GlowingKelpBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected AbstractTopPlantBlock getHeadBlock() {
        return UGBlocks.GLOWING_KELP.get();
    }
}