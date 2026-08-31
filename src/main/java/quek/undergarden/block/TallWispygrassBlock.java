package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGTags;

public class TallWispygrassBlock extends TallDeepturfVariantBlock {
	public TallWispygrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(UGTags.Blocks.SUPPORTS_WISPYGRASS);
	}
}