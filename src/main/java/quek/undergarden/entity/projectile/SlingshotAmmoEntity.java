package quek.undergarden.entity.projectile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class SlingshotAmmoEntity extends ProjectileItemEntity {

    public SlingshotAmmoEntity(EntityType<? extends SlingshotAmmoEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SlingshotAmmoEntity(World worldIn, double x, double y, double z) {
        super(UGEntityTypes.SLINGSHOT_AMMO.get(), x, y, z, worldIn);
    }

    public SlingshotAmmoEntity(World worldIn, LivingEntity shooter) {
        super(UGEntityTypes.SLINGSHOT_AMMO.get(), shooter, worldIn);
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity victim = result.getEntity();
        victim.hurt(DamageSource.thrown(this, this.getOwner()), 6.0F);
        this.playSound(SoundEvents.STONE_BREAK, 1, 1);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.remove();
        }
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        super.onHitBlock(result);
        BlockState blockstate = this.level.getBlockState(result.getBlockPos());
        Entity shooter = this.getOwner();
        if(blockstate.canOcclude()) {
            if(!(shooter instanceof PlayerEntity) || (shooter instanceof PlayerEntity && ((PlayerEntity) shooter).abilities.instabuild)) {
                //don't drop anything
            } else {
                this.spawnAtLocation(new ItemStack(getDefaultItem()));
            }
            this.playStepSound(result.getBlockPos(), blockstate);
            if(!this.level.isClientSide) {
                this.level.broadcastEntityEvent(this, (byte) 3);
                this.remove();
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.DEPTHROCK_PEBBLE.get();
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        return new ItemParticleData(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}