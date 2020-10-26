package quek.undergarden.block.world;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import quek.undergarden.registry.UGBlocks;

import java.util.Random;

public class UGOreBlock extends OreBlock {

    public UGOreBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    protected int getExperience(Random rando) {
        if (this == UGBlocks.coal_ore.get()) {
            return MathHelper.nextInt(rando, 0, 2);
        } else {
            return this == UGBlocks.utherium_ore.get() || this == UGBlocks.diamond_ore.get() ? MathHelper.nextInt(rando, 4, 8) : 0;
        }
    }

    @Override
    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
