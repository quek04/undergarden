package quek.undergarden.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class UGSoundTypes {
	public static final SoundType GRONGLET = new DeferredSoundType(1.0F, 1.0F, UGSoundEvents.GRONGLET_BREAK, () -> SoundEvents.SLIME_BLOCK_STEP, UGSoundEvents.GRONGLET_PLACE, () -> SoundEvents.SLIME_BLOCK_HIT, () -> SoundEvents.SLIME_BLOCK_FALL);
	public static final SoundType DREADROCK = new DeferredSoundType(1.0F, 0.5F, () -> SoundEvents.BASALT_BREAK, () -> SoundEvents.BASALT_STEP, ()-> SoundEvents.BASALT_PLACE, () -> SoundEvents.BASALT_HIT, () -> SoundEvents.BASALT_FALL);
	public static final SoundType TREMBLECRUST = new DeferredSoundType(1.0F, 0.5F, () -> SoundEvents.STONE_BREAK, () -> SoundEvents.STONE_STEP, ()-> SoundEvents.STONE_PLACE, () -> SoundEvents.STONE_HIT, () -> SoundEvents.STONE_FALL);
}
