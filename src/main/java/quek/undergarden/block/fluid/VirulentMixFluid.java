package quek.undergarden.block.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import quek.undergarden.registry.UGSoundEvents;

public abstract class VirulentMixFluid extends ForgeFlowingFluid {

    public VirulentMixFluid(Properties properties) {
        super(properties);
    }

    @Override
    protected void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
        if (random.nextInt(200) == 0) {
            level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_BUBBLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
        if (!state.isSource() && random.nextInt(64) == 0) {
            level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), UGSoundEvents.VIRULENT_FLOW.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }
}
