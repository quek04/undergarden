package quek.undergarden.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

public class UGSoundTypes {
    public static final SoundType GRONGLET = new ForgeSoundType(1.0F, 1.0F, UGSoundEvents.GRONGLET_BREAK, ()-> SoundEvents.SLIME_BLOCK_STEP, UGSoundEvents.GRONGLET_PLACE, ()-> SoundEvents.SLIME_BLOCK_HIT, ()-> SoundEvents.SLIME_BLOCK_FALL);
}
