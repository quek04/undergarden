package quek.undergarden.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import quek.undergarden.UndergardenMod;

import java.util.function.Supplier;

public abstract class UndergardenBlockStateProvider extends BlockStateProvider {

    private static UndergardenBlockModelProvider provider;

    public UndergardenBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, UndergardenMod.MODID, fileHelper);

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

    protected ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    protected String blockName(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void normalBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void woodBlock(Supplier<? extends LogBlock> block, String name) {
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

    public void grassBlock(Supplier<? extends Block> block, String bottom) {
        String baseName = blockName(block);
        ModelFile model = models().sideBottomTop(
                block.get(),
                texture(baseName + "_side"),
                texture(bottom),
                texture(baseName + "_top"));
        grassBlock(block, model);
    }

    private void grassBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
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

    public void wall(Supplier<? extends WallBlock> block, String name) {
        wallBlock(block.get(), texture(name));
        models().wallInventory(block.get().getRegistryName().toString() + "_inventory", texture(name));
        wallColumn(block, name);
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

    private void wallColumn(Supplier<? extends WallBlock> block, String side) {
        String baseName = block.get().getRegistryName().toString();
        fourWayBlock(block.get(),
                models().wallPost(baseName + "_post", texture(side)),
                models().wallSide(baseName + "_side", texture(side)));
    }

    public void door(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), texture(name + "_door_bottom"), texture(name + "_door_top"));
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), texture(name + "_trapdoor"), true);
    }

}
