package quek.undergarden.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class BlisterbombEntity extends ProjectileItemEntity {

    public BlisterbombEntity(EntityType<? extends BlisterbombEntity> type, World world) {
        super(type, world);
    }

    public BlisterbombEntity(World world, LivingEntity thrower) {
        super(UGEntityTypes.BLISTERBOMB.get(), thrower, world);
    }

    public BlisterbombEntity(World worldIn, double x, double y, double z) {
        super(UGEntityTypes.BLISTERBOMB.get(), x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.BLISTERBOMB.get();
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (!this.level.isClientSide) {
            if (result.getType() == RayTraceResult.Type.ENTITY || result.getType() == RayTraceResult.Type.BLOCK) {
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3F, Explosion.Mode.BREAK);
                this.remove();
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
