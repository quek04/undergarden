package quek.undergarden.item.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class UGBucketItem extends BucketItem {

	public UGBucketItem(Supplier<? extends Fluid> fluid, Properties properties) {
		super(fluid, properties);
	}

	@Override
	protected void playEmptySound(@Nullable Player player, LevelAccessor level, BlockPos pos) {
		SoundEvent soundEvent = UGSoundEvents.BUCKET_EMPTY_VIRULENT.get();
		level.playSound(player, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
		level.gameEvent(player, GameEvent.FLUID_PLACE, pos);
	}
}
