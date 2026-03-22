package quek.undergarden.item.tool.slingshot;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.GooBall;

import java.util.List;
import java.util.function.Consumer;

public class GooBallItem extends Item implements ProjectileItem {

	public GooBallItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
		builder.accept(Component.translatable("tooltip.undergarden.slingshot_ammo").withStyle(ChatFormatting.GRAY));
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		return new GooBall(level, pos.x(), pos.y(), pos.z(), stack);
	}
}
