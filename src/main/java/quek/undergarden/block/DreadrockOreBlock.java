package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DreadrockOreBlock extends DropExperienceBlock implements Dreadrock {
    public DreadrockOreBlock(IntProvider xpRange, Properties properties) {
        super(xpRange, properties);
    }

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
		return Dreadrock.getDestroyProgress(state, player, level, pos);
	}
}