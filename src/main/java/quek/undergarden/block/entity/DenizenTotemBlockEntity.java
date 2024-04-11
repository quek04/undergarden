package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.block.DenizenTotemBlock;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGParticleTypes;

import java.util.List;

public class DenizenTotemBlockEntity extends BlockEntity {

	private int ticker;

	public DenizenTotemBlockEntity(BlockPos pos, BlockState state) {
		super(UGBlockEntities.DENIZEN_TOTEM.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, DenizenTotemBlockEntity blockEntity) {
		blockEntity.ticker++;
		if (blockEntity.ticker % 20 == 0) {
			List<LivingEntity> entityList = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(5.0D));
			if (!entityList.isEmpty()) {
				level.setBlockAndUpdate(pos, state.setValue(DenizenTotemBlock.ACTIVE, true));
                entityList.stream().filter(entity -> !entity.hasEffect(UGEffects.PURITY.get())).forEach(entity -> {
                    entity.addEffect(new MobEffectInstance(UGEffects.PURITY.get(), 200, 0, true, true));
					drawParticlesTo(level, pos.getCenter(), entity);
                });
			} else level.setBlockAndUpdate(pos, state.setValue(DenizenTotemBlock.ACTIVE, false));
		}
	}

	private static void drawParticlesTo(Level level, Vec3 totemPos, Entity highlight) {
		int particles = (int) Math.min(12, Math.round(totemPos.distanceToSqr(highlight.position())));
		for (int i = 0; i < particles; i++) {
			double trailFactor = i / (particles - 1.0D);
			double x = totemPos.x() + (highlight.position().x() - totemPos.x()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			double y = totemPos.y() + (highlight.getEyeY() - totemPos.y()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			double z = totemPos.z() + (highlight.position().z() - totemPos.z()) * trailFactor + level.getRandom().nextFloat() * 0.25D;
			level.addParticle(UGParticleTypes.ROGDORIUM_SPARKLE.get(), x, y, z, 0, 0, 0);
		}
	}
}
