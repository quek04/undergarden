package quek.undergarden.data.provider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;

import java.util.function.Supplier;

public abstract class UGItemModelProvider extends ItemModelProvider {

	public UGItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, Undergarden.MODID, fileHelper);
	}

	private String blockName(Supplier<? extends Block> block) {
		return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
	}

	private ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	public void itemFence(Supplier<? extends Block> block, String name) {
		withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
				.texture("texture", ("block/" + name));
	}

	public void block(Supplier<? extends Block> block) {
		block(block, blockName(block));
	}

	public void block(Supplier<? extends Block> block, String name) {
		withExistingParent(blockName(block), modLoc("block/" + name));
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
		withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
	}

	public void normalItemSpecifiedTexture(Supplier<? extends Item> item, String name) {
		withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + name));
	}

	public void torchItem(Supplier<? extends Block> item) {
		withExistingParent(BuiltInRegistries.BLOCK.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(item.get()).getPath()));
	}

	public void toolItem(Supplier<? extends Item> item) {
		withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld"))
				.texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
	}

	public void rodItem(Supplier<? extends Item> item) {
		withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld_rod"))
				.texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
	}

	public void egg(Supplier<? extends Item> item) {
		withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
	}

	public void sign(Supplier<? extends SignBlock> sign) {
		withExistingParent(blockName(sign), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + blockName(sign)));
	}

	public void wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		wallInventory(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
	}

	public void button(Supplier<? extends ButtonBlock> button, Supplier<? extends Block> fullBlock) {
		buttonInventory(BuiltInRegistries.BLOCK.getKey(button.get()).getPath(), texture(blockName(fullBlock)));
	}

	public void trapdoor(Supplier<? extends TrapDoorBlock> trapdoor) {
		withExistingParent(BuiltInRegistries.BLOCK.getKey(trapdoor.get()).getPath(), new ResourceLocation(Undergarden.MODID, "block/" + blockName(trapdoor) + "_bottom"));
	}
}