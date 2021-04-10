package quek.undergarden.block;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class UGOreBlock extends OreBlock {

    public UGOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int xpOnDrop(Random rand) {
        if (this == UGBlocks.COAL_ORE.get()) {
            return MathHelper.nextInt(rand, 0, 2);
        } else {
            return this == UGBlocks.UTHERIUM_ORE.get() || this == UGBlocks.DIAMOND_ORE.get() ? MathHelper.nextInt(rand, 4, 8) : 0;
        }
    }
}