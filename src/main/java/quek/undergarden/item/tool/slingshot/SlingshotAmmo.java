package quek.undergarden.item.tool.slingshot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;

import javax.annotation.Nullable;
import java.util.List;

public interface SlingshotAmmo {
    SlingshotProjectile createProjectile(Level level, LivingEntity shooter);

    static void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.pebble").withStyle(ChatFormatting.GRAY));
    }
}
