package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGItems;

public class DreadrockBlock extends Block {
    public DreadrockBlock(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        float destroySpeed = state.getDestroySpeed(level, pos);
        int i = net.neoforged.neoforge.common.CommonHooks.isCorrectToolForDrops(state, player) ? 30 : 100;
        if (destroySpeed == -1.0F) {
            return 0.0F;
        } else if (!(player.getMainHandItem().getItem() == UGItems.FORGOTTEN_PICKAXE.get())) {
            return player.getDigSpeed(state, pos) / (destroySpeed * 64.0F) / (float)i;
        } else {
            return player.getDigSpeed(state, pos) / destroySpeed / (float)i;
        }
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        return player.getMainHandItem().getItem() == UGItems.FORGOTTEN_PICKAXE.get();
    }
}
