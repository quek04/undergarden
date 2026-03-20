package quek.undergarden.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class CrumblingCatalystItem extends CatalystItem {

	public CrumblingCatalystItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult res = super.useOn(context);
		if (res.consumesAction()) {
			if (context.getPlayer() != null && !context.getLevel().isClientSide()) {
				context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
			}
		}

		return res;
	}
}
