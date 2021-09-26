package quek.undergarden.entity.projectile;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.protocol.Packet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class SlingshotAmmoEntity extends ThrowableItemProjectile {

    public SlingshotAmmoEntity(EntityType<? extends SlingshotAmmoEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public SlingshotAmmoEntity(Level worldIn, double x, double y, double z) {
        super(UGEntityTypes.SLINGSHOT_AMMO.get(), x, y, z, worldIn);
    }

    public SlingshotAmmoEntity(Level worldIn, LivingEntity shooter) {
        super(UGEntityTypes.SLINGSHOT_AMMO.get(), shooter, worldIn);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
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
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState blockstate = this.level.getBlockState(result.getBlockPos());
        Entity shooter = this.getOwner();
        if(blockstate.isCollisionShapeFullBlock(this.level, result.getBlockPos())) {
            if(!(shooter instanceof Player) || ((Player) shooter).abilities.instabuild) {
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
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.DEPTHROCK_PEBBLE.get();
    }

    @OnlyIn(Dist.CLIENT)
    private ParticleOptions makeParticle() {
        return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}