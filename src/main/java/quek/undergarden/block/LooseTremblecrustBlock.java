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
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (level.getBlockState(pos.below()).is(UGBlocks.LOOSE_TREMBLECRUST.get()) || level.isEmptyBlock(pos.below())) {
			level.destroyBlock(pos, false);
		}
	}
}