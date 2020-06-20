package quek.undergarden.fluid;

import net.minecraft.fluid.IFluidState;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class VirulentMixFluid extends ForgeFlowingFluid {

    protected VirulentMixFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(IFluidState state) {
        return false;
    }

    @Override
    public int getLevel(IFluidState fluidState) {
        return 0;
    }

    @Override
    public int getTickRate(IWorldReader worldReader) {
        return 5;
    }
}
