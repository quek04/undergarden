package quek.undergarden.item.tool.slingshot;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.slingshot.RottenBlisterberry;

import java.util.List;

public class RottenBlisterberryItem extends Item implements ProjectileItem {

	public RottenBlisterberryItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.undergarden.slingshot_ammo").withStyle(ChatFormatting.GRAY));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		if (!level.isClientSide) {
			level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0.5F, Level.ExplosionInteraction.NONE);
		}
		return super.finishUsingItem(stack, level, livingEntity);
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		return new RottenBlisterberry(level, pos.x(), pos.y(), pos.z());
	}
}