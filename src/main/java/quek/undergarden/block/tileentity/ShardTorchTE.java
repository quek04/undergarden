package quek.undergarden.block.tileentity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import quek.undergarden.registry.UGTags;
import quek.undergarden.registry.UGTileEntities;

public class ShardTorchTE extends TileEntity implements ITickableTileEntity {

    public ShardTorchTE() {
        super(UGTileEntities.SHARD_TORCH.get());
    }

    @Override
    public void tick() {
        if(world.getGameTime() % 20 == 0) { // every second
            world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() + -4, pos.getY() + -4, pos.getZ() + -4, pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4), entity -> entity.getType().isContained(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.attackEntityFrom(DamageSource.GENERIC, 4));
        }
    }
}
