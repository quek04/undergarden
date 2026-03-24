package quek.undergarden.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import quek.undergarden.block.portal.UndergardenPortalShape;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Optional;

public class CatalystItem extends Item {

	public CatalystItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		if (level.dimension() == Level.OVERWORLD || level.dimension() == UGDimensions.UNDERGARDEN_LEVEL) {
			BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
			Optional<UndergardenPortalShape> optionalShape = UndergardenPortalShape.findEmptyPortalShape(level, pos, Direction.Axis.X);
			if (optionalShape.isPresent()) {
				optionalShape.get().createPortalBlocks(level);
				level.playSound(context.getPlayer(), pos, UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}
}