package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;

public class LooseTremblecrustBlock extends Block {

    public LooseTremblecrustBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pLevel.getBlockState(pPos.below()) == UGBlocks.LOOSE_TREMBLECRUST.get().defaultBlockState() || pLevel.isEmptyBlock(pPos.below())) {
            pLevel.destroyBlock(pPos, false);
        }
    }
}