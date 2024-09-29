package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;

public class TallDeadWispygrassBlock extends TallDeepturfVariantBlock {
	public TallDeadWispygrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(UGBlocks.DEAD_WISPYGRASS_BLOCK) || super.mayPlaceOn(state, level, pos);
	}
}