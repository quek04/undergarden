package quek.undergarden.datagen.data.tags;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

public abstract class UGBlockItemTags {

	public void run() {
		//undergarden
		this.tag(UGTags.Blocks.SMOGSTEM_LOGS, UGTags.Items.SMOGSTEM_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
		this.tag(UGTags.Blocks.WIGGLEWOOD_LOGS, UGTags.Items.WIGGLEWOOD_LOGS).add(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
		this.tag(UGTags.Blocks.GRONGLE_LOGS, UGTags.Items.GRONGLE_LOGS).add(UGBlocks.GRONGLE_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.GRONGLE_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get());
		this.tag(UGTags.Blocks.WHISPERWOOD_LOGS, UGTags.Items.WHISPERWOOD_LOGS).add(UGBlocks.WHISPERWOOD_LOG.get(), UGBlocks.STRIPPED_WHISPERWOOD_LOG.get(), UGBlocks.WHISPERWOOD_WOOD.get(), UGBlocks.STRIPPED_WHISPERWOOD_WOOD.get());

		//undergarden common
		this.tag(UGTags.Blocks.BARS_CLOGGRUM, UGTags.Items.BARS_CLOGGRUM).add(UGBlocks.CLOGGRUM_BARS.get());

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

		//neoforge
		this.tag(Tags.Blocks.BARS, Tags.Items.BARS).addTag(UGTags.Blocks.BARS_CLOGGRUM);
		this.tag(Tags.Blocks.COBBLESTONES, Tags.Items.COBBLESTONES).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get(), UGBlocks.DREADROCK.get());
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get(), UGBlocks.ANCIENT_ROOT_FENCE_GATE.get());
		this.tag(Tags.Blocks.FLOWERS_TALL, Tags.Items.FLOWERS_TALL).add(UGBlocks.TALL_SHIMMERWEED.get());
		this.tag(Tags.Blocks.GLASS_BLOCKS_COLORLESS, Tags.Items.GLASS_BLOCKS_COLORLESS).add(UGBlocks.SEDIMENT_GLASS.get());
		this.tag(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
		this.tag(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR).addTag(UGTags.Blocks.ORES_CLOGGRUM).addTag(UGTags.Blocks.ORES_FROSTSTEEL).addTag(UGTags.Blocks.ORES_UTHERIUM).addTag(UGTags.Blocks.ORES_REGALIUM).addTag(UGTags.Blocks.ORES_ROGDORIUM).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
		this.tag(Tags.Blocks.ORE_RATES_SPARSE, Tags.Items.ORE_RATES_SPARSE).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get(), UGBlocks.DEPTHROCK_GOLD_ORE.get());
		this.tag(Tags.Blocks.ORES, Tags.Items.ORES).addTag(UGTags.Blocks.ORES_CLOGGRUM).addTag(UGTags.Blocks.ORES_FROSTSTEEL).addTag(UGTags.Blocks.ORES_UTHERIUM).addTag(UGTags.Blocks.ORES_REGALIUM).addTag(UGTags.Blocks.ORES_ROGDORIUM);
		this.tag(Tags.Blocks.ORES_COAL, Tags.Items.ORES_COAL).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get());
		this.tag(Tags.Blocks.ORES_DIAMOND, Tags.Items.ORES_DIAMOND).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
		this.tag(Tags.Blocks.ORES_GOLD, Tags.Items.ORES_GOLD).add(UGBlocks.DEPTHROCK_GOLD_ORE.get());
		this.tag(Tags.Blocks.ORES_IRON, Tags.Items.ORES_IRON).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get());
		this.tag(Tags.Blocks.PUMPKINS_NORMAL, Tags.Items.PUMPKINS_NORMAL).add(UGBlocks.GLOOMGOURD.get());
		this.tag(Tags.Blocks.PUMPKINS_CARVED, Tags.Items.PUMPKINS_CARVED).add(UGBlocks.CARVED_GLOOMGOURD.get());
		this.tag(Tags.Blocks.PUMPKINS_JACK_O_LANTERNS, Tags.Items.PUMPKINS_JACK_O_LANTERNS).add(UGBlocks.GLOOM_O_LANTERN.get(), UGBlocks.SHARD_O_LANTERN.get());
		this.tag(Tags.Blocks.SANDS_COLORLESS, Tags.Items.SANDS_COLORLESS).add(UGBlocks.SEDIMENT.get());
		this.tag(Tags.Blocks.STONES, Tags.Items.STONES).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get(), UGBlocks.DREADROCK.get());
		this.tag(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS).addTag(UGTags.Blocks.STORAGE_BLOCKS_CLOGGRUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_RAW_CLOGGRUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL).addTag(UGTags.Blocks.STORAGE_BLOCKS_RAW_FROSTSTEEL).addTag(UGTags.Blocks.STORAGE_BLOCKS_UTHERIUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_REGALIUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_ROGDORIUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_FORGOTTEN_METAL);
		this.tag(Tags.Blocks.NATURAL_LOGS, Tags.Items.NATURAL_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.GRONGLE_LOG.get(), UGBlocks.WHISPERWOOD_LOG.get());
		this.tag(Tags.Blocks.NATURAL_WOODS, Tags.Items.NATURAL_WOODS).add(UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.GRONGLE_WOOD.get(), UGBlocks.WHISPERWOOD_WOOD.get());
		this.tag(Tags.Blocks.STRIPPED_LOGS, Tags.Items.STRIPPED_LOGS).add(UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.STRIPPED_WHISPERWOOD_LOG.get());
		this.tag(Tags.Blocks.STRIPPED_WOODS, Tags.Items.STRIPPED_WOODS).add(UGBlocks.STRIPPED_SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get(), UGBlocks.STRIPPED_WHISPERWOOD_WOOD.get());

		//vanilla
		this.tag(BlockTags.PLANKS, ItemTags.PLANKS).add(UGBlocks.WIGGLEWOOD_PLANKS.get(), UGBlocks.SMOGSTEM_PLANKS.get(), UGBlocks.GRONGLE_PLANKS.get(), UGBlocks.ANCIENT_ROOT_PLANKS.get(), UGBlocks.WHISPERWOOD_PLANKS.get());
		this.tag(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS).add(UGBlocks.WIGGLEWOOD_BUTTON.get(), UGBlocks.SMOGSTEM_BUTTON.get(), UGBlocks.GRONGLE_BUTTON.get(), UGBlocks.ANCIENT_ROOT_BUTTON.get());
		this.tag(BlockTags.STONE_BUTTONS, ItemTags.STONE_BUTTONS).add(UGBlocks.DEPTHROCK_BUTTON.get(), UGBlocks.SHIVERSTONE_BUTTON.get(), UGBlocks.TREMBLECRUST_BUTTON.get(), UGBlocks.DREADROCK_BUTTON.get());
		this.tag(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS).add(UGBlocks.MOGMOSS_RUG.get(), UGBlocks.BLUE_MOGMOSS_RUG.get());
		this.tag(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS).add(UGBlocks.SMOGSTEM_DOOR.get(), UGBlocks.WIGGLEWOOD_DOOR.get(), UGBlocks.GRONGLE_DOOR.get(), UGBlocks.ANCIENT_ROOT_DOOR.get());
		this.tag(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS).add(UGBlocks.SMOGSTEM_STAIRS.get(), UGBlocks.WIGGLEWOOD_STAIRS.get(), UGBlocks.GRONGLE_STAIRS.get(), UGBlocks.ANCIENT_ROOT_STAIRS.get());
		this.tag(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS).add(UGBlocks.SMOGSTEM_SLAB.get(), UGBlocks.WIGGLEWOOD_SLAB.get(), UGBlocks.GRONGLE_SLAB.get(), UGBlocks.ANCIENT_ROOT_SLAB.get());
		this.tag(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get(), UGBlocks.ANCIENT_ROOT_FENCE.get());
		this.tag(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get(), UGBlocks.ANCIENT_ROOT_FENCE_GATE.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES).add(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get(), UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get(), UGBlocks.GRONGLE_PRESSURE_PLATE.get(), UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE.get());
		this.tag(BlockTags.SAPLINGS, ItemTags.SAPLINGS).add(UGBlocks.SMOGSTEM_SAPLING.get(), UGBlocks.WIGGLEWOOD_SAPLING.get(), UGBlocks.GRONGLE_SAPLING.get(), UGBlocks.DEAD_WHISPERWOOD_SAPLING.get());
		this.tag(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS).addTag(UGTags.Blocks.GRONGLE_LOGS).addTag(UGTags.Blocks.WHISPERWOOD_LOGS).add(UGBlocks.ANCIENT_ROOT.get(), UGBlocks.ROGDORIC_ANCIENT_ROOT.get());
		this.tag(BlockTags.SAND, ItemTags.SAND).add(UGBlocks.SEDIMENT.get());
		this.tag(BlockTags.SLABS, ItemTags.SLABS).add(UGBlocks.DEPTHROCK_SLAB.get(), UGBlocks.POLISHED_DEPTHROCK_SLAB.get(), UGBlocks.DEPTHROCK_BRICK_SLAB.get(), UGBlocks.DEPTHROCK_TILE_SLAB.get(), UGBlocks.SHIVERSTONE_SLAB.get(), UGBlocks.SHIVERSTONE_BRICK_SLAB.get(), UGBlocks.TREMBLECRUST_SLAB.get(), UGBlocks.TREMBLECRUST_BRICK_SLAB.get(), UGBlocks.CLOGGRUM_TILE_SLAB.get(), UGBlocks.DREADROCK_SLAB.get(), UGBlocks.DREADROCK_BRICK_SLAB.get(), UGBlocks.SEDIMENT_STONE_SLAB.get(), UGBlocks.POLISHED_SEDIMENT_STONE_SLAB.get(), UGBlocks.SEDIMENT_STONE_BRICK_SLAB.get());
		this.tag(BlockTags.WALLS, ItemTags.WALLS).add(UGBlocks.DEPTHROCK_WALL.get(), UGBlocks.POLISHED_DEPTHROCK_WALL.get(), UGBlocks.DEPTHROCK_BRICK_WALL.get(), UGBlocks.SHIVERSTONE_WALL.get(), UGBlocks.SHIVERSTONE_BRICK_WALL.get(), UGBlocks.TREMBLECRUST_WALL.get(), UGBlocks.TREMBLECRUST_BRICK_WALL.get(), UGBlocks.DREADROCK_WALL.get(), UGBlocks.DREADROCK_BRICK_WALL.get(), UGBlocks.SEDIMENT_STONE_WALL.get(), UGBlocks.POLISHED_SEDIMENT_STONE_WALL.get(), UGBlocks.SEDIMENT_STONE_BRICK_WALL.get());
		this.tag(BlockTags.STAIRS, ItemTags.STAIRS).add(UGBlocks.DEPTHROCK_STAIRS.get(), UGBlocks.POLISHED_DEPTHROCK_STAIRS.get(), UGBlocks.DEPTHROCK_BRICK_STAIRS.get(), UGBlocks.DEPTHROCK_TILE_STAIRS.get(), UGBlocks.SHIVERSTONE_STAIRS.get(), UGBlocks.SHIVERSTONE_BRICK_STAIRS.get(), UGBlocks.TREMBLECRUST_STAIRS.get(), UGBlocks.TREMBLECRUST_BRICK_STAIRS.get(), UGBlocks.CLOGGRUM_TILE_STAIRS.get(), UGBlocks.DREADROCK_STAIRS.get(), UGBlocks.DREADROCK_BRICK_STAIRS.get(), UGBlocks.SEDIMENT_STONE_STAIRS.get(), UGBlocks.POLISHED_SEDIMENT_STONE_STAIRS.get(), UGBlocks.SEDIMENT_STONE_BRICK_STAIRS.get());
		this.tag(BlockTags.LEAVES, ItemTags.LEAVES).add(UGBlocks.SMOGSTEM_LEAVES.get(), UGBlocks.WIGGLEWOOD_LEAVES.get(), UGBlocks.GRONGLE_LEAVES.get(), UGBlocks.DEAD_WHISPERWOOD_LEAVES.get());
		this.tag(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS).add(UGBlocks.SMOGSTEM_TRAPDOOR.get(), UGBlocks.WIGGLEWOOD_TRAPDOOR.get(), UGBlocks.GRONGLE_TRAPDOOR.get(), UGBlocks.ANCIENT_ROOT_TRAPDOOR.get());
		this.tag(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS).add(UGBlocks.SHIMMERWEED.get(), UGBlocks.AMOROUS_BRISTLE.get(), UGBlocks.MISERABELL.get(), UGBlocks.BUTTERBUNCH.get());
		this.tag(BlockTags.FLOWERS, ItemTags.FLOWERS).add(UGBlocks.TALL_SHIMMERWEED.get());
		this.tag(BlockTags.BEDS, ItemTags.BEDS).add(UGBlocks.DEPTHROCK_BED.get());
		this.tag(BlockTags.COAL_ORES, ItemTags.COAL_ORES).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get());
		this.tag(BlockTags.IRON_ORES, ItemTags.IRON_ORES).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get());
		this.tag(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES).add(UGBlocks.DEPTHROCK_GOLD_ORE.get());
		this.tag(BlockTags.DIAMOND_ORES, ItemTags.DIAMOND_ORES).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
		this.tag(BlockTags.GRASS_BLOCKS, ItemTags.GRASS_BLOCKS).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get());
		this.tag(BlockTags.DIRT, ItemTags.DIRT).add(UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		this.tag(BlockTags.LANTERNS, ItemTags.LANTERNS).add(UGBlocks.CLOGGRUM_LANTERN.get());
		this.tag(BlockTags.BARS, ItemTags.BARS).add(UGBlocks.CLOGGRUM_BARS.get());
		this.tag(BlockTags.STANDING_SIGNS, ItemTags.SIGNS).add(UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.GRONGLE_SIGN.get(), UGBlocks.ANCIENT_ROOT_SIGN.get());
		this.tag(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS).add(UGBlocks.SMOGSTEM_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(), UGBlocks.GRONGLE_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_HANGING_SIGN.get());
		this.tag(BlockTags.BEE_ATTRACTIVE, ItemTags.BEE_FOOD).add(UGBlocks.AMOROUS_BRISTLE.get(), UGBlocks.MISERABELL.get(), UGBlocks.BUTTERBUNCH.get());
	}

	protected abstract TagAppender<Block, Block> tag(TagKey<Block> blockTag, TagKey<Item> itemTag);
}