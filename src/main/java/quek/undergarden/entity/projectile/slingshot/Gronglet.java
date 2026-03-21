package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;

public class Gronglet extends AbstractGronglet {

	public Gronglet(Level level, double x, double y, double z, ItemStack stack) {
		super(UGEntityTypes.GRONGLET.get(), x, y, z, level, stack);
	}

	public Gronglet(LivingEntity shooter, Level level, ItemStack stack) {
		super(UGEntityTypes.GRONGLET.get(), shooter, level, stack);
	}

	public Gronglet(EntityType<Gronglet> type, Level level) {
		super(type, level);
		this.setDropItem(false);
	}

	@Override
	protected GrongletBlock getGrongletBlock() {
		return UGBlocks.GRONGLET.get();
	}
}