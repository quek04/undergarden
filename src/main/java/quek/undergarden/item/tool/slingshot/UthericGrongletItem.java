package quek.undergarden.item.tool.slingshot;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import quek.undergarden.entity.projectile.slingshot.UthericGronglet;

public class UthericGrongletItem extends GrongletItem {
	public UthericGrongletItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		return new UthericGronglet(level, pos.x(), pos.y(), pos.z());
	}
}
