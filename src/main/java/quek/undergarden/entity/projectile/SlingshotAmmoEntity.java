package quek.undergarden.entity.projectile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;

import java.util.List;

public class SlingshotAmmoEntity extends AbstractArrowEntity implements IRendersAsItem {

    public SlingshotAmmoEntity(EntityType<? extends SlingshotAmmoEntity> p_i50172_1_, World p_i50172_2_) {
        super(p_i50172_1_, p_i50172_2_);
        setDamage(2D);
    }

    public SlingshotAmmoEntity(World worldIn, double x, double y, double z) {
        super(UndergardenEntities.slingshot_ammo, x, y, z, worldIn);
    }

    public SlingshotAmmoEntity(World worldIn, LivingEntity shooter) {
        super(UndergardenEntities.slingshot_ammo, shooter, worldIn);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onEntityHit((EntityRayTraceResult)raytraceResultIn);
        }
        else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceResultIn;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());
            this.entityDropItem(new ItemStack(UndergardenItems.depthrock_pebble.get()));
            this.playStepSound(blockraytraceresult.getPos(), blockstate);
            this.remove();
        }
    }

    @Override
    protected SoundEvent getHitEntitySound() {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(UndergardenItems.depthrock_pebble.get());
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(UndergardenItems.depthrock_pebble.get());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
