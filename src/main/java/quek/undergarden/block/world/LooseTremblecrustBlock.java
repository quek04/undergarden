package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LooseTremblecrustBlock extends Block {

    public LooseTremblecrustBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if(worldIn.isAirBlock(pos.down())) {
            worldIn.destroyBlock(pos, false);
        }

    }
}
