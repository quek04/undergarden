package quek.undergarden.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.network.NetworkHooks;
import quek.undergarden.entity.Minion;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class MinionProjectile extends ThrowableItemProjectile {

	public MinionProjectile(EntityType<? extends MinionProjectile> type, Level level) {
		super(type, level);
	}

	public MinionProjectile(Level level, LivingEntity shooter) {
		super(UGEntityTypes.MINION_PROJECTILE.get(), shooter, level);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.FORGOTTEN_NUGGET.get();
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(this.getDefaultItem())), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.remove(RemovalReason.KILLED);
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (result.getEntity() instanceof LivingEntity living) {
			if (!(living instanceof Minion)) {
				living.hurt(this.damageSources().thrown(this, this.getOwner()), 10.0F);
			}
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}