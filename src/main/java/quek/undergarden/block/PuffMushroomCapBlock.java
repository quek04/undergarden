package quek.undergarden.block;

import net.minecraft.core.BlockPos;
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
