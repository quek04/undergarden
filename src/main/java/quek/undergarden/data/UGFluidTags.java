package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;

public class UGFluidTags extends FluidTagsProvider {

    public UGFluidTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Undergarden.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Fluid Tags";
    }

    @Override
    protected void addTags() {
        tag(UGTags.Fluids.VIRULENT).add(UGFluids.VIRULENT_MIX_SOURCE.get(), UGFluids.VIRULENT_MIX_FLOWING.get());
    }
}
