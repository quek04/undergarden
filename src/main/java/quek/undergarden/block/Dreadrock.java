package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGTags;

public interface Dreadrock {
	 static float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
		float destroySpeed = state.getDestroySpeed(level, pos);
		int i = EventHooks.doPlayerHarvestCheck(player, state, level, pos) ? 30 : 100;
		if (destroySpeed == -1.0F) {
			return 0.0F;
		} else if (player.level().dimension() == UGDimensions.UNDERGARDEN_LEVEL && !(player.getMainHandItem().is(UGTags.Items.ACCELERATED_DREADROCK_BREAKING))) {
			return player.getDestroySpeed(state, pos) / (destroySpeed * 64.0F) / (float)i;
		} else {
			return player.getDestroySpeed(state, pos) / destroySpeed / (float)i;
		}
	}
}