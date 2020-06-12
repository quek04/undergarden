package quek.undergarden.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;

public class BlisterbombEntity extends ProjectileItemEntity {

    public BlisterbombEntity(EntityType<? extends BlisterbombEntity> type, World world) {
        super(type, world);
    }

    public BlisterbombEntity(World world, LivingEntity thrower) {
        super(UndergardenEntities.blisterbomb, thrower, world);
    }

    @Override
    protected Item getDefaultItem() {
        return UndergardenItems.blisterbomb.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY || result.getType() == RayTraceResult.Type.BLOCK) {
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3F, Explosion.Mode.BREAK);
            this.remove();
        }


        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
