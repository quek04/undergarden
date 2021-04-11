package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;

public class LooseTremblecrustBlock extends Block {

    public LooseTremblecrustBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
        if(worldIn.getBlockState(pos.below()) == UGBlocks.LOOSE_TREMBLECRUST.get().defaultBlockState() || worldIn.isEmptyBlock(pos.below())) {
            worldIn.destroyBlock(pos, false);
        }
    }
}