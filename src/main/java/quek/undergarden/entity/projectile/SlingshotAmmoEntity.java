package quek.undergarden.entity.projectile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
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
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult)result).getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), 6.0F);
            this.playSound(SoundEvents.BLOCK_STONE_BREAK, 1, 1);
            this.remove();
        }
        else if(result.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) result;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());
            PlayerEntity player = (PlayerEntity) this.func_234616_v_();
            if(blockstate.isSolid()) {
                if(player != null && !player.abilities.isCreativeMode) {
                    this.entityDropItem(new ItemStack(getDefaultItem()));
                }
                this.playStepSound(blockraytraceresult.getPos(), blockstate);
                this.remove();
            }
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return UGItems.DEPTHROCK_PEBBLE.get();
    }
}