package quek.undergarden.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;

public abstract class UGBlockModelProvider extends BlockModelProvider {

    public UGBlockModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Undergarden.MODID, helper);
    }

    public BlockModelBuilder cubeBottomTopOverlay(String name, ResourceLocation side, ResourceLocation bottom, ResourceLocation top, ResourceLocation overlay) {
        return cubeBottomTop(name, side, bottom, top);
    }
}
