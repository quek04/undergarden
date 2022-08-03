package quek.undergarden.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

import java.util.function.Supplier;

public abstract class UGItemModelProvider extends ItemModelProvider {

    public UGItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Undergarden.MODID, fileHelper);
    }

    private String blockName(Supplier<? extends Block> block) {
        return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
    }

    private ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    public ItemModelBuilder itemFence(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", ("block/" + name));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/" + name));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block) {
        return blockFlat(block, blockName(block));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name));
    }

    public ItemModelBuilder blockFlatWithItemName(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public ItemModelBuilder normalItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder torchItem(Supplier<? extends Block> item) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + ForgeRegistries.BLOCKS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder toolItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder rodItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld_rod"))
                .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder egg(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
    }

    public ItemModelBuilder sign(Supplier<? extends SignBlock> sign) {
        return withExistingParent(blockName(sign), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + blockName(sign)));
    }

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
    }

    public ItemModelBuilder button(Supplier<? extends ButtonBlock> button, Supplier<? extends Block> fullBlock) {
        return buttonInventory(ForgeRegistries.BLOCKS.getKey(button.get()).getPath(), texture(blockName(fullBlock)));
    }
}