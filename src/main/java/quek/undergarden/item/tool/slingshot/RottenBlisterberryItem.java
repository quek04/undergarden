package quek.undergarden.item.tool.slingshot;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.RottenBlisterberryEntity;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;

import javax.annotation.Nullable;
import java.util.List;

public class RottenBlisterberryItem extends Item implements SlingshotAmmo {

    public RottenBlisterberryItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide) {
            level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0.5F, Explosion.BlockInteraction.NONE);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public SlingshotProjectile createProjectile(Level level, LivingEntity shooter) {
        return new RottenBlisterberryEntity(level, shooter);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        SlingshotAmmo.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}