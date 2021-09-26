package quek.undergarden.block;

import net.minecraft.world.level.block.OreBlock;
import net.minecraft.util.Mth;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UGOreBlock extends OreBlock {

    public UGOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int xpOnDrop(Random rand) {
        if (this == UGBlocks.COAL_ORE.get()) {
            return Mth.nextInt(rand, 0, 2);
        } else {
            return this == UGBlocks.UTHERIUM_ORE.get() || this == UGBlocks.DIAMOND_ORE.get() ? Mth.nextInt(rand, 4, 8) : 0;
        }
    }
}