package quek.undergarden;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.block.TallDeepturfVariantBlock;
import quek.undergarden.registry.UGTags;

public class TallDeadWispygrassBlock extends TallDeepturfVariantBlock {
	public TallDeadWispygrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(UGTags.Blocks.SUPPORTS_WISPYGRASS);
	}
}