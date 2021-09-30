package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags;
import quek.undergarden.registry.UGBlockEntities;

public class ShardTorchBlockEntity extends BlockEntity {

    public ShardTorchBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(UGBlockEntities.SHARD_TORCH.get(), pWorldPosition, pBlockState);
    }

    public static <B extends BlockEntity>void tick(Level level, BlockPos pos, BlockState state, B blockEntity) {
        if(level.getGameTime() % 20 == 0) { // every second
            level.getEntitiesOfClass(LivingEntity.class, new AABB(
                    pos.getX() - 4,
                    pos.getY() - 4,
                    pos.getZ() - 4,
                    pos.getX() + 4,
                    pos.getY() + 4,
                    pos.getZ() + 4),
                    entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4));
        }
    }
}