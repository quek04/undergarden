package quek.undergarden.item.tool.slingshot;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import quek.undergarden.entity.projectile.slingshot.DepthrockPebble;

import java.util.function.Consumer;

public class DepthrockPebbleItem extends BlockItem implements ProjectileItem {

	public DepthrockPebbleItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
		builder.accept(Component.translatable("tooltip.undergarden.slingshot_ammo").withStyle(ChatFormatting.GRAY));
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		return new DepthrockPebble(level, pos.x(), pos.y(), pos.z(), stack);
	}
}
