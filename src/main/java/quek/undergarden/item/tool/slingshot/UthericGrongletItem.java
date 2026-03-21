package quek.undergarden.item.tool.slingshot;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import quek.undergarden.entity.projectile.slingshot.UthericGronglet;
import quek.undergarden.registry.UGAttachments;

public class UthericGrongletItem extends GrongletItem {
	public UthericGrongletItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		return new UthericGronglet(level, pos.x(), pos.y(), pos.z(), stack);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (entity instanceof Player player) {
			player.setData(UGAttachments.UTHERIC_INFECTION, 20.0D);
		}
		return super.finishUsingItem(stack, level, entity);
	}
}