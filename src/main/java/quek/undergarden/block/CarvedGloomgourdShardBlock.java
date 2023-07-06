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
		level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(4.0D),
				entity -> entity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(entity -> {
			if (entity.hurt(UGDamageSources.getShardTorchDamage(level, pos.getCenter()), 4)) {
				ShardTorchBlock.drawParticlesTo(level, pos.getCenter(), entity);
			}
		});
		level.scheduleTick(pos, this, 20);
	}
}