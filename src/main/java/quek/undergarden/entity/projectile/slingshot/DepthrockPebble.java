package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class DepthrockPebble extends SlingshotProjectile {

    private int airTime = 1;

    public DepthrockPebble(EntityType<? extends DepthrockPebble> type, Level level) {
        super(type, level);
    }

    public DepthrockPebble(Level level, double x, double y, double z) {
        super(UGEntityTypes.DEPTHROCK_PEBBLE.get(), x, y, z, level);
    }

    public DepthrockPebble(Level level, LivingEntity shooter) {
        super(UGEntityTypes.DEPTHROCK_PEBBLE.get(), shooter, level);
        this.setDropItem(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level.getGameTime() % 5 == 0) {
            airTime++;
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity victim = result.getEntity();
        float length = (float) this.getDeltaMovement().length();
        int damage = Mth.ceil(Mth.clamp((double) length * airTime, 0.0D, 2.147483647E9D));
        victim.hurt(new IndirectEntityDamageSource("arrow", this, this.getOwner()), damage);
        this.playSound(SoundEvents.STONE_BREAK, 1.0F, 1.0F);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.DEPTHROCK_PEBBLE.get();
    }

    private ParticleOptions makeParticle() {
        return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.makeParticle();

            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}