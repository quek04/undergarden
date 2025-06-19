package quek.undergarden.item.bucket;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class UGSolidBucketItem extends SolidBucketItem {

	public UGSolidBucketItem(Block block, SoundEvent placeSound, Properties properties) {
		super(block, placeSound, properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult interactionresult = super.useOn(context);
		Player player = context.getPlayer();
		if (interactionresult.consumesAction() && player != null) {
			player.setItemInHand(context.getHand(), UGBucketItem.getEmptySuccessItem(context.getItemInHand(), player));
		}

		return interactionresult;
	}
}
