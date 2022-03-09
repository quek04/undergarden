package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public abstract class SlingshotProjectile extends ThrowableItemProjectile {

    protected boolean ricochet;

    public SlingshotProjectile(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public SlingshotProjectile(EntityType<? extends ThrowableItemProjectile> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    public SlingshotProjectile(EntityType<? extends ThrowableItemProjectile> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState blockstate = this.level.getBlockState(result.getBlockPos());
        Entity shooter = this.getOwner();
        if (!blockstate.getCollisionShape(this.level, result.getBlockPos()).isEmpty()) {
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

    public void setRicochet(boolean ricochet) {
        this.ricochet = ricochet;
    }


}
