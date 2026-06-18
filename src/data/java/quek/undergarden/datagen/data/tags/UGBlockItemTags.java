package quek.undergarden.datagen.data.tags;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

public abstract class UGBlockItemTags {

	public void run() {
		this.tag(UGTags.Blocks.ORES_CLOGGRUM, UGTags.Items.ORES_CLOGGRUM).add(UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get());
		this.tag(UGTags.Blocks.ORES_FROSTSTEEL, UGTags.Items.ORES_FROSTSTEEL).add(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get());
		this.tag(UGTags.Blocks.ORES_UTHERIUM, UGTags.Items.ORES_UTHERIUM).add(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get());
		this.tag(UGTags.Blocks.ORES_REGALIUM, UGTags.Items.ORES_REGALIUM).add(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get());
		this.tag(UGTags.Blocks.ORES_ROGDORIUM, UGTags.Items.ORES_ROGDORIUM).add(UGBlocks.DREADROCK_ROGDORIUM_ORE.get());

		this.tag(UGTags.Blocks.STORAGE_BLOCKS_CLOGGRUM, UGTags.Items.STORAGE_BLOCKS_CLOGGRUM).add(UGBlocks.CLOGGRUM_BLOCK.get());
		this.tag(UGTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL, UGTags.Items.STORAGE_BLOCKS_FROSTSTEEL).add(UGBlocks.FROSTSTEEL_BLOCK.get());
		this.tag(UGTags.Blocks.STORAGE_BLOCKS_UTHERIUM, UGTags.Items.STORAGE_BLOCKS_UTHERIUM).add(UGBlocks.UTHERIUM_BLOCK.get());
		this.tag(UGTags.Blocks.STORAGE_BLOCKS_REGALIUM, UGTags.Items.STORAGE_BLOCKS_REGALIUM).add(UGBlocks.REGALIUM_BLOCK.get());
		this.tag(UGTags.Blocks.STORAGE_BLOCKS_ROGDORIUM, UGTags.Items.STORAGE_BLOCKS_ROGDORIUM).add(UGBlocks.ROGDORIUM_BLOCK.get());
		this.tag(UGTags.Blocks.STORAGE_BLOCKS_FORGOTTEN_METAL, UGTags.Items.STORAGE_BLOCKS_FORGOTTEN_METAL).add(UGBlocks.FORGOTTEN_BLOCK.get());

		this.tag(UGTags.Blocks.STORAGE_BLOCKS_RAW_CLOGGRUM, UGTags.Items.STORAGE_BLOCKS_RAW_CLOGGRUM).add(UGBlocks.RAW_CLOGGRUM_BLOCK.get());
		this.tag(UGTags.Blocks.STORAGE_BLOCKS_RAW_FROSTSTEEL, UGTags.Items.STORAGE_BLOCKS_RAW_FROSTSTEEL).add(UGBlocks.RAW_FROSTSTEEL_BLOCK.get());

		this.tag(UGTags.Blocks.MUSHROOMS, UGTags.Items.MUSHROOMS).add(UGBlocks.INK_MUSHROOM.get(), UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.BLOOD_MUSHROOM.get(), UGBlocks.PUFF_MUSHROOM.get());
		this.tag(UGTags.Blocks.SMOGSTEM_LOGS, UGTags.Items.SMOGSTEM_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
		this.tag(UGTags.Blocks.WIGGLEWOOD_LOGS, UGTags.Items.WIGGLEWOOD_LOGS).add(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
		this.tag(UGTags.Blocks.GRONGLE_LOGS, UGTags.Items.GRONGLE_LOGS).add(UGBlocks.GRONGLE_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.GRONGLE_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get());
	}

	protected abstract TagAppender<Block, Block> tag(TagKey<Block> blockTag, TagKey<Item> itemTag);
}
