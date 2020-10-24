package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quek.undergarden.registry.UndergardenBlocks;

import net.minecraft.block.AbstractBlock.Properties;

public class LooseTremblecrustBlock extends Block {

    public LooseTremblecrustBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if(worldIn.getBlockState(pos.down()) == UndergardenBlocks.loose_tremblecrust.get().getDefaultState() || worldIn.isAirBlock(pos.down())) {
            worldIn.destroyBlock(pos, false);
        }

    }
}
