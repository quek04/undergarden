package quek.undergarden.block;

import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;
import quek.undergarden.registry.UGBlocks;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GlowingKelpBlock extends KelpPlantBlock {

    public GlowingKelpBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return UGBlocks.GLOWING_KELP.get();
    }
}