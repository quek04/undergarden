package quek.undergarden.block.tileentity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags;
import quek.undergarden.registry.UGTileEntities;

public class ShardTorchTE extends BlockEntity implements TickableBlockEntity {

    public ShardTorchTE() {
        super(UGTileEntities.SHARD_TORCH.get());
    }

    @Override
    public void tick() {
        if(level.getGameTime() % 20 == 0) { // every second
            level.getEntitiesOfClass(LivingEntity.class, new AABB(worldPosition.getX() + -4, worldPosition.getY() + -4, worldPosition.getZ() + -4, worldPosition.getX() + 4, worldPosition.getY() + 4, worldPosition.getZ() + 4), entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4));
        }
    }
}