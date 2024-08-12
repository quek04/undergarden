package quek.undergarden.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.neoforge.event.EventHooks;
import quek.undergarden.block.portal.UndergardenPortalBlock;
import quek.undergarden.block.portal.UndergardenPortalShape;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Optional;
import java.util.function.Predicate;

public class CatalystItem extends Item {

	public CatalystItem() {
		super(new Properties()
				.stacksTo(1)
				.rarity(Rarity.RARE)
		);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getPlayer() != null) {
			if (context.getPlayer().level().dimension() == UGDimensions.UNDERGARDEN_LEVEL || context.getPlayer().level().dimension() == Level.OVERWORLD) {
				for (Direction direction : Direction.Plane.VERTICAL) {
					BlockPos framePos = context.getClickedPos().relative(direction);
					if (this.tryCreatePortal(context.getLevel(), framePos)) {
						context.getLevel().playSound(context.getPlayer(), framePos, UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
						return InteractionResult.CONSUME;
					} else return InteractionResult.FAIL;
				}
			}
		}
		return InteractionResult.FAIL;
	}

	private boolean tryCreatePortal(Level level, BlockPos pos) {
		if (level.dimension() == Level.OVERWORLD || level.dimension() == UGDimensions.UNDERGARDEN_LEVEL) {
			Optional<UndergardenPortalShape> optional = findPortalShape(level, pos, shape -> shape.isValid() && shape.getPortalBlocks() == 0, Direction.Axis.X);
			if (optional.isPresent()) {
				optional.get().createPortalBlocks();
				return true;
			}
		}
		return false;
	}

	public static Optional<UndergardenPortalShape> findPortalShape(LevelAccessor accessor, BlockPos pos, Predicate<UndergardenPortalShape> shape, Direction.Axis axis) {
		Optional<UndergardenPortalShape> optional = Optional.of(new UndergardenPortalShape(accessor, pos, axis)).filter(shape);
		if (optional.isPresent()) {
			return optional;
		} else {
			Direction.Axis oppositeAxis = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
			return Optional.of(new UndergardenPortalShape(accessor, pos, oppositeAxis)).filter(shape);
		}
	}
}