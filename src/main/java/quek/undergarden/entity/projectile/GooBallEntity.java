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
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.entity.ScintlingEntity;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class GooBallEntity extends ProjectileItemEntity {

    public GooBallEntity(EntityType<? extends GooBallEntity> type, World world) {
        super(type, world);
    }

    public GooBallEntity(World worldIn, LivingEntity throwerIn) {
        super(UGEntityTypes.goo_ball, throwerIn, worldIn);
    }

    public GooBallEntity(World worldIn, double x, double y, double z) {
        super(UGEntityTypes.goo_ball, x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.goo_ball.get();
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        return new ItemParticleData(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    @OnlyIn(Dist.CLIENT)
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
                if(livingEntity instanceof ScintlingEntity) {
                    livingEntity.heal(2);
                }
                else {
                    livingEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float)0);
                    livingEntity.addPotionEffect(new EffectInstance(UGEffects.gooey.get(), 100, 0, false, true));
                }
            }

            this.playSound(SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 1, 1);
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
