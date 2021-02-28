package quek.undergarden.block;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import quek.undergarden.registry.UGTileEntities;

public class CarvedGloomgourdShardBlock extends CarvedGloomgourdBlock {

    public CarvedGloomgourdShardBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return UGTileEntities.SHARD_TORCH.get().create();
    }
}
