package quek.undergarden.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.entity.MinionEntity;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class MinionProjectileEntity extends ProjectileItemEntity {

    public MinionProjectileEntity(EntityType<? extends MinionProjectileEntity> type, World world) {
        super(type, world);
    }

    public MinionProjectileEntity(World worldIn, LivingEntity throwerIn) {
        super(UGEntityTypes.MINION_PROJECTILE.get(), throwerIn, worldIn);
    }

    public MinionProjectileEntity(World worldIn, double x, double y, double z) {
        super(UGEntityTypes.MINION_PROJECTILE.get(), x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.FORGOTTEN_NUGGET.get();
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        return new ItemParticleData(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            
            if(entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if(!(livingEntity instanceof MinionEntity)) {
                    livingEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), 10.0F);
                }
            }
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