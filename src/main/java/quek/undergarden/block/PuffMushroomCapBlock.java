package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class PuffMushroomCapBlock extends Block {

	public PuffMushroomCapBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		RandomSource random = level.getRandom();
		for (int i = 0; i < 10; i++) {
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + 1.0D + random.nextDouble();
			double z = pos.getZ() + random.nextDouble();
			level.addParticle(ParticleTypes.WHITE_SMOKE, x, y, z, 0.0F, 0.1F, 0.0F);
		}
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
		if (entity.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(level, entity);
		} else {
			this.bounceUp(entity);
		}
	}

	private void bounceUp(Entity pEntity) {
		Vec3 vec3 = pEntity.getDeltaMovement();
		if (vec3.y < 0.0) {
			double d0 = pEntity instanceof LivingEntity ? 1.0 : 0.8;
			pEntity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
		}
	}
}
