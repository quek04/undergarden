package quek.undergarden.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import quek.undergarden.registry.UGTileEntities;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class CarvedGloomgourdShardBlock extends CarvedGloomgourdBlock {

    public CarvedGloomgourdShardBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return UGTileEntities.SHARD_TORCH.get().create();
    }
}