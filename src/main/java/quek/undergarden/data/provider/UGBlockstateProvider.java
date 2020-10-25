package quek.undergarden.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import quek.undergarden.UGMod;

import java.util.function.Supplier;

public abstract class UGBlockstateProvider extends BlockStateProvider {

    private static UGBlockModelProvider provider;

    public UGBlockstateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, UGMod.MODID, fileHelper);

        provider = new UGBlockModelProvider(generator, fileHelper) {
            @Override
            public String getName() {
                return UGBlockstateProvider.this.getName();
            }

            @Override
            protected void registerModels() {

            }
        };
    }

    @Override
    public UGBlockModelProvider models() {
        return provider;
    }

    protected ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    protected String blockName(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void normalBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void woodBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), texture(name));
    }

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(model)
                        .build());
    }

    public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
        ModelFile torch = models().torch(blockName(block), texture(blockName(block)));
        ModelFile torchwall = models().torchWall(blockName(wall), texture(blockName(block)));
        simpleBlock(block.get(), torch);
        getVariantBuilder(wall.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(torchwall)
                        .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 90) % 360)
                        .build());
    }

    public void crossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().cross(blockName(block), texture(blockName(block))));
    }

    public void stairs(Supplier<? extends StairsBlock> block, String name) {
        stairsBlock(block.get(), texture(name));
    }

    public void slab(Supplier<? extends SlabBlock> block, Supplier<? extends Block> fullBlock) {
        slabBlock(block.get(), texture(blockName(fullBlock)), texture(blockName(fullBlock)));
    }

    public void fence(Supplier<? extends FenceBlock> block, String name) {
        fenceBlock(block.get(), texture(name));
        fenceColumn(block, name);
    }

    private void fenceColumn(Supplier<? extends FenceBlock> block, String side) {
        String baseName = block.get().getRegistryName().toString();
        fourWayBlock(block.get(),
                models().fencePost(baseName + "_post", texture(side)),
                models().fenceSide(baseName + "_side", texture(side)));
    }

    public void door(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), texture(name + "_door_bottom"), texture(name + "_door_top"));
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), texture(name + "_trapdoor"), true);
    }

}
