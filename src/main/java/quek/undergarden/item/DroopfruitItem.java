package quek.undergarden.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignApplicator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import quek.undergarden.registry.UGSoundEvents;

public class DroopfruitItem extends BlockItem implements SignApplicator {

	public DroopfruitItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public boolean tryApplyToSign(Level level, SignBlockEntity sign, boolean isFrontText, ItemStack item, Player player) {
		if (sign.updateText(signText -> signText.setHasGlowingText(true), isFrontText)) {
			level.playSound(null, sign.getBlockPos(), UGSoundEvents.DITCHBULB_PASTE_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			return true;
		} else {
			return false;
		}
	}
}
