package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTags;

public class ShardTorchBlock extends TorchBlock {

	public ShardTorchBlock(Properties properties) {
		super(ParticleTypes.FLAME, properties);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		double x = (double) pos.getX() + 0.5D;
		double y = (double) pos.getY() + 0.7D;
		double z = (double) pos.getZ() + 0.5D;
		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(UGParticleTypes.SHARD.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		level.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(4.0D),
				entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> {
			if (entity.hurt(UGDamageSources.getShardTorchDamage(level, pos.getCenter()), 4)) {
				drawParticlesTo(level, pos.getCenter(), entity);
			}
		});

		level.scheduleTick(pos, this, 20);
	}

	public static void drawParticlesTo(ServerLevel level, Vec3 torchPos, Entity highlight) {
		int particles = (int) Math.min(12, Math.round(torchPos.distanceToSqr(highlight.position())));
		for (int i = 0; i < particles; i++) {
			double trailFactor = i / (particles - 1.0D);
			double x = torchPos.x() + (highlight.position().x() - torchPos.x()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			double y = torchPos.y() + 0.25D + (highlight.getEyeY() - torchPos.y()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			double z = torchPos.z() + (highlight.position().z() - torchPos.z()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			level.sendParticles(UGParticleTypes.SHARD_BEAM.get(), x, y, z, 1, 0, 0, 0, 0);
		}
	}
}