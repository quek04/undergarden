package quek.undergarden.block.tileentity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags;
import quek.undergarden.registry.UGTileEntities;

public class ShardTorchTE extends TileEntity implements ITickableTileEntity {

    public ShardTorchTE() {
        super(UGTileEntities.SHARD_TORCH.get());
    }

    @Override
    public void tick() {
        if(level.getGameTime() % 20 == 0) { // every second
            level.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(worldPosition.getX() + -4, worldPosition.getY() + -4, worldPosition.getZ() + -4, worldPosition.getX() + 4, worldPosition.getY() + 4, worldPosition.getZ() + 4), entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4));
        }
    }
}