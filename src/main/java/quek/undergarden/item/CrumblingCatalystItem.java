package quek.undergarden.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import quek.undergarden.block.portal.UndergardenPortalShape;
import quek.undergarden.registry.UGDimensions;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Optional;

public class CrumblingCatalystItem extends CatalystItem {

	public CrumblingCatalystItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getLevel().dimension() == Level.OVERWORLD || context.getLevel().dimension() == UGDimensions.UNDERGARDEN_LEVEL) {
			BlockPos framePos = context.getClickedPos().relative(context.getClickedFace());
			Optional<UndergardenPortalShape> optional = findPortalShape(context.getLevel(), framePos, shape -> shape.isValid() && shape.getPortalBlocks() == 0, Direction.Axis.X);
			if (optional.isPresent()) {
				optional.get().createPortalBlocks();
				context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
				if (context.getPlayer() != null && !context.getLevel().isClientSide) {
					context.getItemInHand().hurtAndBreak(1, context.getPlayer(), LivingEntity.getSlotForHand(context.getPlayer().getUsedItemHand()));
				}
				return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
			}
		}
		return InteractionResult.FAIL;
	}
}
