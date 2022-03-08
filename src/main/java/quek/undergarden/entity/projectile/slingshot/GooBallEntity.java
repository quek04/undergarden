package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import quek.undergarden.entity.animal.ScintlingEntity;
import quek.undergarden.registry.UGEffects;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class GooBallEntity extends ThrowableItemProjectile {

    public GooBallEntity(EntityType<? extends GooBallEntity> type, Level level) {
        super(type, level);
    }

    public GooBallEntity(Level level, LivingEntity shooter) {
        super(UGEntityTypes.GOO_BALL.get(), shooter, level);
    }

    public GooBallEntity(Level level, double x, double y, double z) {
        super(UGEntityTypes.GOO_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.GOO_BALL.get();
    }

    private ParticleOptions makeParticle() {
        return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.makeParticle();

            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) result).getEntity();

            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof ScintlingEntity) {
                    livingEntity.heal(2);
                } else {
                    livingEntity.hurt(DamageSource.thrown(this, this.getOwner()), (float) 0);
                    livingEntity.addEffect(new MobEffectInstance(UGEffects.GOOEY.get(), 100, 0, false, true));
                }
            }

            this.playSound(SoundEvents.SLIME_BLOCK_BREAK, 1, 1);
        }

        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}