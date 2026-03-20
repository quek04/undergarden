package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.block.DenizenTotemBlock;
import quek.undergarden.client.particle.TotemBeamParticle;
import quek.undergarden.registry.UGBlockEntities;
import quek.undergarden.registry.UGEffects;

import java.util.List;

public class DenizenTotemBlockEntity extends BlockEntity {

	private int ticker;

	public DenizenTotemBlockEntity(BlockPos pos, BlockState state) {
		super(UGBlockEntities.DENIZEN_TOTEM.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, DenizenTotemBlockEntity blockEntity) {
		if (level instanceof ServerLevel sl) {
			blockEntity.ticker++;
			if (blockEntity.ticker % 20 == 0) {
				List<LivingEntity> entityList = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(5.0D));
				if (!entityList.isEmpty()) {
					level.setBlockAndUpdate(pos, state.setValue(DenizenTotemBlock.ACTIVE, true));
					entityList.stream().filter(entity -> !entity.hasEffect(UGEffects.PURITY)).forEach(entity -> {
						entity.addEffect(new MobEffectInstance(UGEffects.PURITY, 160 + (level.getRandom().nextInt(5) * 20), 0, true, true));
						blockEntity.drawParticlesTo(sl, pos.getCenter(), entity);
					});
				} else level.setBlockAndUpdate(pos, state.setValue(DenizenTotemBlock.ACTIVE, false));
			}
		}
	}

	private void drawParticlesTo(ServerLevel level, Vec3 totemPos, Entity highlight) {
		RandomSource random = level.getRandom();
		for (int i = 0; i < 20; i++) {
			AABB aabb = highlight.getBoundingBox();
			Vec3 toEntity = aabb.getMinPosition().add(random.nextDouble() * aabb.getXsize(), random.nextDouble() * aabb.getYsize(), random.nextDouble() * aabb.getZsize());
			level.sendParticles(new TotemBeamParticle.Options(toEntity, random.nextInt(40) + 10), totemPos.x, totemPos.y, totemPos.z, 1, 0, 0, 0, 0);
		}
	}
}
