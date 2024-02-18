package quek.undergarden.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

import java.util.function.Supplier;

public abstract class UGItemModelProvider extends ItemModelProvider {

	public UGItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, Undergarden.MODID, fileHelper);
	}

	private String blockName(Supplier<? extends Block> block) {
		return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
	}

	private ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	public void itemFence(Supplier<? extends Block> block, String name) {
		withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
				.texture("texture", ("block/" + name));
	}

	public ItemModelBuilder block(Supplier<? extends Block> block) {
		return block(block, blockName(block));
	}

	public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
		return withExistingParent(blockName(block), modLoc("block/" + name));
	}

	public void blockFlat(Supplier<? extends Block> block) {
		withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + blockName(block)));
	}

	public void blockFlatWithBlockTexture(Supplier<? extends Block> block, String name) {
		withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + name))
				.renderType("translucent");
	}

	public void blockFlatWithItemTexture(Supplier<? extends Block> block, String name) {
		withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + name));
	}

	public void normalItem(Supplier<? extends Item> item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
	}

	public void normalItemSpecifiedTexture(Supplier<? extends Item> item, String name) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + name));
	}

	public void torchItem(Supplier<? extends Block> item) {
		withExistingParent(ForgeRegistries.BLOCKS.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + ForgeRegistries.BLOCKS.getKey(item.get()).getPath()));
	}

	public void toolItem(Supplier<? extends Item> item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
				.texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
	}

	public void rodItem(Supplier<? extends Item> item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld_rod"))
				.texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
	}

	public void egg(Supplier<? extends Item> item) {
		withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
	}

	public void sign(Supplier<? extends SignBlock> sign) {
		withExistingParent(blockName(sign), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + blockName(sign)));
	}

	public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
	}

	public ItemModelBuilder button(Supplier<? extends ButtonBlock> button, Supplier<? extends Block> fullBlock) {
		return buttonInventory(ForgeRegistries.BLOCKS.getKey(button.get()).getPath(), texture(blockName(fullBlock)));
	}

	public void trapdoor(Supplier<? extends TrapDoorBlock> trapdoor) {
		withExistingParent(ForgeRegistries.BLOCKS.getKey(trapdoor.get()).getPath(), new ResourceLocation(Undergarden.MODID, "block/" + blockName(trapdoor) + "_bottom"));
	}
}