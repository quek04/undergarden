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
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;

public class RottenBlisterberryEntity extends ProjectileItemEntity {

    public RottenBlisterberryEntity(EntityType<? extends RottenBlisterberryEntity> type, World world) {
        super(type, world);
    }

    public RottenBlisterberryEntity(World world, LivingEntity thrower) {
        super(UndergardenEntities.rotten_blisterberry, thrower, world);
    }

    @Override
    protected Item getDefaultItem() {
        return UndergardenItems.rotten_blisterberry.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY || result.getType() == RayTraceResult.Type.BLOCK) {
                this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.5F, Explosion.Mode.NONE);
                this.remove();
            }
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
