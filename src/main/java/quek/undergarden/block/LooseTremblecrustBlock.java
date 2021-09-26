package quek.undergarden.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGBlocks;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LooseTremblecrustBlock extends Block {

    public LooseTremblecrustBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, Entity entityIn) {
        if(worldIn.getBlockState(pos.below()) == UGBlocks.LOOSE_TREMBLECRUST.get().defaultBlockState() || worldIn.isEmptyBlock(pos.below())) {
            worldIn.destroyBlock(pos, false);
        }
    }
}