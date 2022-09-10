package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags;

public class CarvedGloomgourdShardBlock extends CarvedGloomgourdBlock {

    public CarvedGloomgourdShardBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, this, 20);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        //if(pLevel.getGameTime() % 20 == 0) {
        level.getEntitiesOfClass(LivingEntity.class, new AABB(
                        pos.getX() - 4,
                        pos.getY() - 4,
                        pos.getZ() - 4,
                        pos.getX() + 4,
                        pos.getY() + 4,
                        pos.getZ() + 4),
                entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4));
        //}
        level.scheduleTick(pos, this, 20);
    }
}