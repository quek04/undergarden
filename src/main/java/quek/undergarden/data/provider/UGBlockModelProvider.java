package quek.undergarden.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.UGMod;

public abstract class UGBlockModelProvider extends BlockModelProvider {

    public UGBlockModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, UGMod.MODID, fileHelper);
    }

    public BlockModelBuilder sideBottomTop(Block block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return withExistingParent(block.getRegistryName().getPath(), modLoc("block/grass_block"))
                .texture("side", side)
                .texture("bottom", bottom)
                .texture("top", top);
    }

}
