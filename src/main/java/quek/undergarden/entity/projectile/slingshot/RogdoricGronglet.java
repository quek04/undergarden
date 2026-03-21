package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;

public class RogdoricGronglet extends AbstractGronglet {

	public RogdoricGronglet(Level level, double x, double y, double z, ItemStack stack) {
		super(UGEntityTypes.ROGDORIC_GRONGLET.get(), x, y, z, level, stack);
	}

	public RogdoricGronglet(LivingEntity shooter, Level level, ItemStack stack) {
		super(UGEntityTypes.ROGDORIC_GRONGLET.get(), shooter, level, stack);
	}

	public RogdoricGronglet(EntityType<RogdoricGronglet> type, Level level) {
		super(type, level);
		this.setDropItem(false);
	}

	@Override
	protected GrongletBlock getGrongletBlock() {
		return UGBlocks.ROGDORIC_GRONGLET.get();
	}
}