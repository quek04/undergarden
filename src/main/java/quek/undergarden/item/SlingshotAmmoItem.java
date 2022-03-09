package quek.undergarden.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.GooBallEntity;
import quek.undergarden.entity.projectile.slingshot.SlingshotPebbleEntity;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

public class SlingshotAmmoItem extends Item {

    public SlingshotAmmoItem() {
        super(new Properties()
                .tab(UGItemGroups.GROUP)
        );
    }

    public SlingshotAmmoItem(Properties properties) {
        super(properties);
    }

    public SlingshotProjectile createProjectile(Level level, LivingEntity shooter) {
        if (this == UGItems.GOO_BALL.get()) {
            return new GooBallEntity(level, shooter);
        } else {
            return new SlingshotPebbleEntity(level, shooter);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent("tooltip.pebble").withStyle(ChatFormatting.GRAY));
    }
}