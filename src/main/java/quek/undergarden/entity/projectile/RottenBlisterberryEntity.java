package quek.undergarden.entity.projectile;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class RottenBlisterberryEntity extends ThrowableItemProjectile {

    public RottenBlisterberryEntity(EntityType<? extends RottenBlisterberryEntity> type, Level world) {
        super(type, world);
    }

    public RottenBlisterberryEntity(Level world, LivingEntity thrower) {
        super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), thrower, world);
    }

    public RottenBlisterberryEntity(Level worldIn, double x, double y, double z) {
        super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.ROTTEN_BLISTERBERRY.get();
    }

    @Override
    protected void onHit(HitResult result) {
        if (!this.level.isClientSide) {
            if (result.getType() == HitResult.Type.ENTITY || result.getType() == HitResult.Type.BLOCK) {
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.BlockInteraction.NONE);
                this.remove(RemovalReason.KILLED);
            }
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
