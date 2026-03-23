package quek.undergarden.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import quek.undergarden.registry.UGSoundEvents;

public class GrongletItem extends BlockItem {

	public GrongletItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
		if (!level.isClientSide()) {
			RandomSource random = level.getRandom();
			level.playSound(null, player.getOnPos(), UGSoundEvents.GRONGLET_BURN.get(), SoundSource.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
		}
		return super.use(level, player, usedHand);
	}
}