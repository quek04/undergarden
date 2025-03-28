package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DreadrockStairBlock extends StairBlock implements Dreadrock {
	public DreadrockStairBlock(BlockState baseState, Properties properties) {
		super(baseState, properties);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
		return Dreadrock.getDestroyProgress(state, player, level, pos);
	}
}