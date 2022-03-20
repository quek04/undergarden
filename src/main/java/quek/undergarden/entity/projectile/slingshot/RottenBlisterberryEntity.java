package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

public class RottenBlisterberryEntity extends SlingshotProjectile {

    public RottenBlisterberryEntity(EntityType<? extends RottenBlisterberryEntity> type, Level level) {
        super(type, level);
        this.setDropItem(false);
    }

    public RottenBlisterberryEntity(Level level, LivingEntity shooter) {
        super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), shooter, level);
    }

    public RottenBlisterberryEntity(Level level, double x, double y, double z) {
        super(UGEntityTypes.ROTTEN_BLISTERBERRY.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.ROTTEN_BLISTERBERRY.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity victim = result.getEntity();
        victim.hurt(new IndirectEntityDamageSource("arrow", this, this.getOwner()), 0.0F);
        if (!this.level.isClientSide) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.BlockInteraction.NONE);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level.isClientSide && this.ricochetTimes == 0) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.BlockInteraction.NONE);
            this.discard();
        }
    }
}
