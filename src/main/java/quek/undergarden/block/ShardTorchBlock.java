package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTags;

public class ShardTorchBlock extends TorchBlock {

    public ShardTorchBlock(Properties properties) {
        super(properties, ParticleTypes.FLAME);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 0.7D;
        double z = (double)pos.getZ() + 0.5D;
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
        level.addParticle(UGParticleTypes.SHARD.get(), x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, this, 20);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.getEntitiesOfClass(LivingEntity.class, new AABB(
                        pos.getX() - 4,
                        pos.getY() - 4,
                        pos.getZ() - 4,
                        pos.getX() + 4,
                        pos.getY() + 4,
                        pos.getZ() + 4
                ),
                entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4));

        level.scheduleTick(pos, this, 20);
    }
}