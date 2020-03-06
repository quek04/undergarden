package quek.undergarden.data.provider;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import quek.undergarden.Undergarden;

import java.util.function.Supplier;

public abstract class UndergardenBlockStateProvider extends BlockStateProvider {

    private static UndergardenBlockModelProvider provider;

    public UndergardenBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Undergarden.MODID, fileHelper);

        provider = new UndergardenBlockModelProvider(generator, fileHelper) {
            @Override
            public String getName() {
                return UndergardenBlockStateProvider.this.getName();
            }

            @Override
            protected void registerModels() {

            }
        };
    }

    @Override
    public UndergardenBlockModelProvider models() {
        return provider;
    }

    protected ResourceLocation undergardenTexture(String name) {
        return modLoc("block/" + name);
    }

    protected String undergardenBlockName(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void normalBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void woodBlock(Supplier<? extends LogBlock> block, String name) {
        axisBlock(block.get(), undergardenTexture(name));
    }

    public void grassBlock(Supplier<? extends Block> block, String bottom) {
        ModelFile modelFile = models().grassModel(
                block.get(),
                undergardenTexture(undergardenBlockName(block) + "_top"),
                undergardenTexture(undergardenBlockName(block) + "_side"),
                undergardenTexture(bottom)
        );
        grassBlock(block, modelFile);
    }

    private void grassBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
    }


}
