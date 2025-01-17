package quek.undergarden.entity.projectile.slingshot;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;

public class UthericGronglet extends AbstractGronglet {

	public UthericGronglet(Level level, double x, double y, double z) {
		super(UGEntityTypes.UTHERIC_GRONGLET.get(), x, y, z, level);
	}

	public UthericGronglet(LivingEntity shooter, Level level) {
		super(UGEntityTypes.UTHERIC_GRONGLET.get(), shooter, level);
	}

	public UthericGronglet(EntityType<UthericGronglet> type, Level level) {
		super(type, level);
		this.setDropItem(false);
	}

	@Override
	protected GrongletBlock getGrongletBlock() {
		return UGBlocks.UTHERIC_GRONGLET.get();
	}
}
