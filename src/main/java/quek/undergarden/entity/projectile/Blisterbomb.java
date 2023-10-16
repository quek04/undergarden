package quek.undergarden.entity.projectile;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class Blisterbomb extends ThrowableItemProjectile {

	public Blisterbomb(EntityType<? extends Blisterbomb> type, Level level) {
		super(type, level);
	}

	public Blisterbomb(Level level, LivingEntity thrower) {
		super(UGEntityTypes.BLISTERBOMB.get(), thrower, level);
	}

	public Blisterbomb(Level level, double x, double y, double z) {
		super(UGEntityTypes.BLISTERBOMB.get(), x, y, z, level);
	}

	@Override
	protected Item getDefaultItem() {
		return UGItems.BLISTERBOMB.get();
	}

	@Override
	protected void onHit(HitResult result) {
		if (!this.level().isClientSide()) {
			if (result.getType() == HitResult.Type.ENTITY || result.getType() == HitResult.Type.BLOCK) {
				this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3F, Level.ExplosionInteraction.BLOCK);
				this.remove(RemovalReason.KILLED);
			}
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
