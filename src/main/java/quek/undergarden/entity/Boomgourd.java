package quek.undergarden.entity;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.registry.UGEntityTypes;

import javax.annotation.Nullable;

public class Boomgourd extends PrimedTnt {

	public Boomgourd(EntityType<? extends PrimedTnt> entity, Level level) {
		super(entity, level);
	}

	public Boomgourd(Level level, double x, double y, double z, @Nullable LivingEntity igniterEntity) {
		this(UGEntityTypes.BOOMGOURD.get(), level);
		this.setPos(x, y, z);
		double d0 = level.getRandom().nextDouble() * (double) ((float) Math.PI * 2F);
		this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
		this.setFuse(80);
		this.xo = x;
		this.yo = y;
		this.zo = z;
		this.owner = igniterEntity;
	}

	@Override
	protected void explode() {
		this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Level.ExplosionInteraction.BLOCK);
		for (int i = 0; i < 4; i++) {
			Blisterbomb blisterbomb = new Blisterbomb(this.level(), this.getX(), this.getY(), this.getZ());
			RandomSource random = this.level().getRandom();
			blisterbomb.shoot(random.nextDouble() * (random.nextBoolean() ? -1 : 1), 0.5F, random.nextDouble() * (random.nextBoolean() ? -1 : 1), 0.5F, 10.0F);
			this.level().addFreshEntity(blisterbomb);
		}
	}
}