package quek.undergarden.item.tool.slingshot;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class RottenBlisterberryItem extends SlingshotAmmoItem {

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
}