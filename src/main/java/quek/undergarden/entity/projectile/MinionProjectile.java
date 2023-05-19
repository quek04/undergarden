package quek.undergarden.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import quek.undergarden.entity.Minion;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class MinionProjectile extends ThrowableItemProjectile {

	public MinionProjectile(EntityType<? extends MinionProjectile> type, Level world) {
		super(type, world);
	}

	public MinionProjectile(Level level, LivingEntity shooter) {
		super(UGEntityTypes.MINION_PROJECTILE.get(), shooter, level);
	}

	public MinionProjectile(Level level, double x, double y, double z) {
		super(UGEntityTypes.MINION_PROJECTILE.get(), x, y, z, level);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.FORGOTTEN_NUGGET.get();
	}

	@OnlyIn(Dist.CLIENT)
	private ParticleOptions makeParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			ParticleOptions iparticledata = this.makeParticle();

			for (int i = 0; i < 8; ++i) {
				this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHit(HitResult result) {
		if (result.getType() == HitResult.Type.ENTITY) {
			Entity entity = ((EntityHitResult) result).getEntity();

			if (entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				if (!(livingEntity instanceof Minion)) {
					livingEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 10.0F);
				}
			}
		}

		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte) 3);
			this.remove(RemovalReason.KILLED);
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}