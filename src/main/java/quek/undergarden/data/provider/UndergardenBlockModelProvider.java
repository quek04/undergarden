package quek.undergarden.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import quek.undergarden.Undergarden;

public abstract class UndergardenBlockModelProvider extends BlockModelProvider {

    public UndergardenBlockModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Undergarden.MODID, fileHelper);
    }

    public BlockModelBuilder grassModel(Block block, ResourceLocation top, ResourceLocation bottom, ResourceLocation side) {
        return withExistingParent(block.getRegistryName().getPath(), modLoc("block/"))
                .texture("top", top)
                .texture("bottom", bottom)
                .texture("side", side);
    }

}
