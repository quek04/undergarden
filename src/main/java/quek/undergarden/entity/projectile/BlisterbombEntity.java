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
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY || result.getType() == RayTraceResult.Type.BLOCK) {
                this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3F, Explosion.Mode.BREAK);
                this.remove();
            }
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
