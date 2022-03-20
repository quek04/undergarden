package quek.undergarden.item.tool.slingshot;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import quek.undergarden.entity.projectile.slingshot.GrongletEntity;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;

import javax.annotation.Nullable;
import java.util.List;

public class GrongletItem extends BlockItem implements SlingshotAmmo {

    public GrongletItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public SlingshotProjectile createProjectile(Level level, LivingEntity shooter) {
        return new GrongletEntity(shooter, level);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        SlingshotAmmo.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }
}
