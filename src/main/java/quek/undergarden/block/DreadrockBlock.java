package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DreadrockBlock extends Block implements Dreadrock {
    public DreadrockBlock(Properties properties) {
        super(properties);
    }

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
		return Dreadrock.getDestroyProgress(state, player, level, pos);
	}
}