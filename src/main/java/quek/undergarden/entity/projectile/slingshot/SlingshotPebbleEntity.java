package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class SlingshotPebbleEntity extends ThrowableItemProjectile {

    private int airTime = 1;

    public SlingshotPebbleEntity(EntityType<? extends SlingshotPebbleEntity> type, Level level) {
        super(type, level);
    }

    public SlingshotPebbleEntity(Level level, double x, double y, double z) {
        super(UGEntityTypes.SLINGSHOT_PEBBLE.get(), x, y, z, level);
    }

    public SlingshotPebbleEntity(Level level, LivingEntity shooter) {
        super(UGEntityTypes.SLINGSHOT_PEBBLE.get(), shooter, level);
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
        this.playSound(SoundEvents.STONE_BREAK, 1, 1);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState blockstate = this.level.getBlockState(result.getBlockPos());
        Entity shooter = this.getOwner();
        if (blockstate.isCollisionShapeFullBlock(this.level, result.getBlockPos())) {
            if (!(shooter instanceof Player) || ((Player) shooter).getAbilities().instabuild) {
                //don't drop anything
            } else {
                this.spawnAtLocation(new ItemStack(getDefaultItem()));
            }
            this.playStepSound(result.getBlockPos(), blockstate);
            if (!this.level.isClientSide) {
                this.level.broadcastEntityEvent(this, (byte) 3);
                this.remove(RemovalReason.KILLED);
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