package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGParticleTypes;

import java.util.List;

public class DenizenTotemBlock extends Block {
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public DenizenTotemBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(ACTIVE, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ACTIVE);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		level.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		List<LivingEntity> entityList = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(5.0D));
		if (!entityList.isEmpty()) {
			level.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
			entityList.forEach(entity -> {
				if (!entity.hasEffect(UGEffects.PURITY.get())) {
					entity.addEffect(new MobEffectInstance(UGEffects.PURITY.get(), 200, 0));
					drawParticlesTo(level, pos.getCenter(), entity);
				}

			});
		} else level.setBlockAndUpdate(pos, state.setValue(ACTIVE, false));

		level.scheduleTick(pos, this, 20);
	}

	public static void drawParticlesTo(ServerLevel level, Vec3 totemPos, Entity highlight) {
		int particles = (int) Math.min(12, Math.round(totemPos.distanceToSqr(highlight.position())));
		for (int i = 0; i < particles; i++) {
			double trailFactor = i / (particles - 1.0D);
			double x = totemPos.x() + (highlight.position().x() - totemPos.x()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			double y = totemPos.y() + (highlight.position().y() - totemPos.y()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			double z = totemPos.z() + (highlight.position().z() - totemPos.z()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			level.sendParticles(UGParticleTypes.ROGDORIUM_SPARKLE.get(), x, y, z, 1, 0, 0, 0, 0);
		}
	}
}