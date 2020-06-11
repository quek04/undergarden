package quek.undergarden.client.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.LocatableSound;
import net.minecraft.util.SoundCategory;
import quek.undergarden.registry.UndergardenSoundEvents;
import quek.undergarden.world.UndergardenDimension;

public class UndergardenAmbienceSound extends LocatableSound implements ITickableSound {

    private static final Minecraft CLIENT = Minecraft.getInstance();

    public UndergardenAmbienceSound() {
        super(UndergardenSoundEvents.UNDERGARDEN_AMBIANCE, SoundCategory.AMBIENT);
        this.attenuationType = AttenuationType.NONE;
        this.repeat = true;
    }

    @Override
    public boolean isDonePlaying() {
        return CLIENT.player == null || !UndergardenDimension.isTheUndergarden(CLIENT.player.world);
    }

    @Override
    public void tick() {
        this.volume = 0.5F;
    }
}
