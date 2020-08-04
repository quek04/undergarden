package quek.undergarden.data.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import quek.undergarden.UndergardenMod;

import java.util.function.Supplier;

public abstract class UndergardenItemModelProvider extends ItemModelProvider {

    public UndergardenItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, UndergardenMod.MODID, fileHelper);
    }

    public String blockName(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    protected ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    public ItemModelBuilder itemFence(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", ("block/" + name));
    }

    public ItemModelBuilder itemFenceGate(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("block/fence_gate_inventory"))
                .texture("texture", ("block/" + name));
    }


    public ItemModelBuilder itemBlock(Supplier<? extends Block> block) {
        return itemBlock(block, blockName(block));
    }

    public ItemModelBuilder itemBlock(Supplier<? extends Block> block, String model) {
        return withExistingParent(blockName(block), modLoc("block/" + model));
    }

    public ItemModelBuilder itemBlockFlat(Supplier<? extends Block> block) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + blockName(block)));
    }

    public ItemModelBuilder normalItem(Supplier<? extends Item> item) {
        return withExistingParent(item.get().getRegistryName().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + item.get().getRegistryName().getPath()));
    }

    public ItemModelBuilder torchItem(Supplier<? extends Item> item) {
        return withExistingParent(item.get().getRegistryName().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + item.get().getRegistryName().getPath()));
    }

    public ItemModelBuilder toolItem(Supplier<? extends Item> item) {
        return withExistingParent(item.get().getRegistryName().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.get().getRegistryName().getPath()));
    }

    public ItemModelBuilder egg(Supplier<? extends Item> item) {
        return withExistingParent(item.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
