package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class UGBlockTags extends IntrinsicHolderTagsProvider<Block> {

	public UGBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Undergarden.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//undergarden
		tag(UGTags.Blocks.BASE_STONE_UNDERGARDEN).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES).add(UGBlocks.DEPTHROCK.get());
		tag(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES).add(UGBlocks.SHIVERSTONE.get());
		tag(UGTags.Blocks.DREADROCK_ORE_REPLACEABLES).add(UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES).add(UGBlocks.TREMBLECRUST.get(), UGBlocks.LOOSE_TREMBLECRUST.get());
		tag(UGTags.Blocks.UNDERGARDEN_CARVER_REPLACEABLES).addTag(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES).addTag(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES).addTag(BlockTags.DIRT).addTag(UGTags.Blocks.ORES_CLOGGRUM).addTag(UGTags.Blocks.ORES_FROSTSTEEL).addTag(UGTags.Blocks.ORES_UTHERIUM).addTag(UGTags.Blocks.ORES_REGALIUM).add(UGBlocks.SEDIMENT.get()).add(Blocks.POWDER_SNOW);
		tag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.INK_MUSHROOM.get(), UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.BLOOD_MUSHROOM.get(), UGBlocks.PUFF_MUSHROOM.get());
		tag(UGTags.Blocks.UTHERIC_INFECTION_BLOCKS).add(UGBlocks.UTHERIUM_GROWTH.get());
		tag(UGTags.Blocks.PORTAL_FRAME_BLOCKS).add(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_TILES, Blocks.POLISHED_DEEPSLATE, Blocks.REINFORCED_DEEPSLATE, UGBlocks.DEPTHROCK_BRICKS.get(), UGBlocks.DEPTHROCK_TILES.get(), UGBlocks.POLISHED_DEPTHROCK.get(), UGBlocks.SHIVERSTONE_BRICKS.get());
		tag(UGTags.Blocks.SMOGSTEM_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
		tag(UGTags.Blocks.WIGGLEWOOD_LOGS).add(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
		tag(UGTags.Blocks.GRONGLE_LOGS).add(UGBlocks.GRONGLE_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.GRONGLE_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get());
		tag(UGTags.Blocks.MUNCHER_BREAKABLES).addTag(Tags.Blocks.STONES).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER).addTag(UGTags.Blocks.BASE_STONE_UNDERGARDEN);
		tag(UGTags.Blocks.SCINTLING_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(UGTags.Blocks.SMOG_MOG_SPAWNABLE_ON).addTag(BlockTags.ANIMALS_SPAWNABLE_ON).add(UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.COARSE_DEEPSOIL.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get());

		//undergarden common
		tag(UGTags.Blocks.ORES_CLOGGRUM).add(UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get());
		tag(UGTags.Blocks.ORES_FROSTSTEEL).add(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get());
		tag(UGTags.Blocks.ORES_UTHERIUM).add(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get());
		tag(UGTags.Blocks.ORES_REGALIUM).add(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get());
		tag(UGTags.Blocks.ORES_ROGDORIUM).add(UGBlocks.DREADROCK_ROGDORIUM_ORE.get());

		tag(UGTags.Blocks.STORAGE_BLOCKS_CLOGGRUM).add(UGBlocks.CLOGGRUM_BLOCK.get());
		tag(UGTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL).add(UGBlocks.FROSTSTEEL_BLOCK.get());
		tag(UGTags.Blocks.STORAGE_BLOCKS_UTHERIUM).add(UGBlocks.UTHERIUM_BLOCK.get());
		tag(UGTags.Blocks.STORAGE_BLOCKS_REGALIUM).add(UGBlocks.REGALIUM_BLOCK.get());
		tag(UGTags.Blocks.STORAGE_BLOCKS_ROGDORIUM).add(UGBlocks.ROGDORIUM_BLOCK.get());
		tag(UGTags.Blocks.STORAGE_BLOCKS_FORGOTTEN_METAL).add(UGBlocks.FORGOTTEN_BLOCK.get());

		tag(UGTags.Blocks.STORAGE_BLOCKS_RAW_CLOGGRUM).add(UGBlocks.RAW_CLOGGRUM_BLOCK.get());
		tag(UGTags.Blocks.STORAGE_BLOCKS_RAW_FROSTSTEEL).add(UGBlocks.RAW_FROSTSTEEL_BLOCK.get());

		tag(UGTags.Blocks.DEPTHROCK_GROUND).add(UGBlocks.DEPTHROCK.get());
		tag(UGTags.Blocks.DEPTHROCK_ORES).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.DEPTHROCK_GOLD_ORE.get(), UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.DEPTHROCK_REGALIUM_ORE.get());

		tag(UGTags.Blocks.SHIVERSTONE_GROUND).add(UGBlocks.SHIVERSTONE.get());
		tag(UGTags.Blocks.SHIVERSTONE_ORES).add(UGBlocks.SHIVERSTONE_COAL_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get());

		tag(UGTags.Blocks.DREADROCK_GROUND).add(UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.DREADROCK_ORES).add(UGBlocks.DREADROCK_ROGDORIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get());

		tag(UGTags.Blocks.TREMBLECRUST_GROUND).add(UGBlocks.TREMBLECRUST.get());
		tag(UGTags.Blocks.TREMBLECRUST_ORES).add(UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get());

		//vanilla
		tag(BlockTags.PLANKS).add(UGBlocks.SMOGSTEM_PLANKS.get(), UGBlocks.WIGGLEWOOD_PLANKS.get(), UGBlocks.GRONGLE_PLANKS.get(), UGBlocks.ANCIENT_ROOT_PLANKS.get());
		tag(BlockTags.WOODEN_BUTTONS).add(UGBlocks.SMOGSTEM_BUTTON.get(), UGBlocks.WIGGLEWOOD_BUTTON.get(), UGBlocks.GRONGLE_BUTTON.get(), UGBlocks.ANCIENT_ROOT_BUTTON.get());
		tag(BlockTags.BUTTONS).add(UGBlocks.DEPTHROCK_BUTTON.get(), UGBlocks.SHIVERSTONE_BUTTON.get(), UGBlocks.SMOGSTEM_BUTTON.get(), UGBlocks.WIGGLEWOOD_BUTTON.get(), UGBlocks.GRONGLE_BUTTON.get(), UGBlocks.ANCIENT_ROOT_BUTTON.get());
		tag(BlockTags.WOODEN_DOORS).add(UGBlocks.SMOGSTEM_DOOR.get(), UGBlocks.WIGGLEWOOD_DOOR.get(), UGBlocks.GRONGLE_DOOR.get(), UGBlocks.ANCIENT_ROOT_DOOR.get());
		tag(BlockTags.WOODEN_STAIRS).add(UGBlocks.SMOGSTEM_STAIRS.get(), UGBlocks.WIGGLEWOOD_STAIRS.get(), UGBlocks.GRONGLE_STAIRS.get(), UGBlocks.ANCIENT_ROOT_STAIRS.get());
		tag(BlockTags.WOODEN_SLABS).add(UGBlocks.SMOGSTEM_SLAB.get(), UGBlocks.WIGGLEWOOD_SLAB.get(), UGBlocks.GRONGLE_SLAB.get(), UGBlocks.ANCIENT_ROOT_SLAB.get());
		tag(BlockTags.WOODEN_FENCES).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get(), UGBlocks.ANCIENT_ROOT_FENCE.get());
		tag(BlockTags.SAPLINGS).add(UGBlocks.SMOGSTEM_SAPLING.get(), UGBlocks.WIGGLEWOOD_SAPLING.get(), UGBlocks.GRONGLE_SAPLING.get());
		tag(BlockTags.LOGS_THAT_BURN).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS).addTag(UGTags.Blocks.GRONGLE_LOGS).add(UGBlocks.ANCIENT_ROOT.get());
		tag(BlockTags.LOGS).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS).addTag(UGTags.Blocks.GRONGLE_LOGS).add(UGBlocks.ANCIENT_ROOT.get());
		tag(BlockTags.SMALL_FLOWERS).add(UGBlocks.SHIMMERWEED.get(), UGBlocks.AMOROUS_BRISTLE.get(), UGBlocks.MISERABELL.get(), UGBlocks.BUTTERBUNCH.get());
		tag(BlockTags.TALL_FLOWERS).add(UGBlocks.TALL_SHIMMERWEED.get());
		tag(BlockTags.ENDERMAN_HOLDABLE).addTag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get(), UGBlocks.GLOOMGOURD.get(), UGBlocks.CARVED_GLOOMGOURD.get());
		tag(BlockTags.WOODEN_PRESSURE_PLATES).add(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get(), UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get(), UGBlocks.GRONGLE_PRESSURE_PLATE.get(), UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE.get());
		tag(BlockTags.STONE_PRESSURE_PLATES).add(UGBlocks.DEPTHROCK_PRESSURE_PLATE.get(), UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get());
		tag(BlockTags.WALLS).add(UGBlocks.DEPTHROCK_WALL.get(), UGBlocks.POLISHED_DEPTHROCK_WALL.get(), UGBlocks.DEPTHROCK_BRICK_WALL.get(), UGBlocks.SHIVERSTONE_WALL.get(), UGBlocks.SHIVERSTONE_BRICK_WALL.get(), UGBlocks.TREMBLECRUST_WALL.get(), UGBlocks.TREMBLECRUST_BRICK_WALL.get());
		tag(BlockTags.LEAVES).add(UGBlocks.SMOGSTEM_LEAVES.get(), UGBlocks.WIGGLEWOOD_LEAVES.get(), UGBlocks.GRONGLE_LEAVES.get());
		tag(BlockTags.WOODEN_TRAPDOORS).add(UGBlocks.SMOGSTEM_TRAPDOOR.get(), UGBlocks.WIGGLEWOOD_TRAPDOOR.get(), UGBlocks.GRONGLE_TRAPDOOR.get(), UGBlocks.ANCIENT_ROOT_TRAPDOOR.get());
		tag(BlockTags.BAMBOO_PLANTABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(BlockTags.STANDING_SIGNS).add(UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.GRONGLE_SIGN.get(), UGBlocks.ANCIENT_ROOT_SIGN.get());
		tag(BlockTags.WALL_SIGNS).add(UGBlocks.SMOGSTEM_WALL_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_SIGN.get());
		tag(BlockTags.CEILING_HANGING_SIGNS).add(UGBlocks.SMOGSTEM_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(), UGBlocks.GRONGLE_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_HANGING_SIGN.get());
		tag(BlockTags.WALL_HANGING_SIGNS).add(UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(), UGBlocks.GRONGLE_WALL_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN.get());
		tag(BlockTags.CROPS).add(UGBlocks.GLOOMGOURD_STEM.get());
		tag(BlockTags.BEE_GROWABLES).add(UGBlocks.UNDERBEAN_BUSH.get(), UGBlocks.BLISTERBERRY_BUSH.get());
		tag(BlockTags.PORTALS).add(UGBlocks.UNDERGARDEN_PORTAL.get()); //TODO otherside portal
		tag(BlockTags.BEACON_BASE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get(), UGBlocks.FORGOTTEN_BLOCK.get());
		tag(BlockTags.WALL_POST_OVERRIDE).add(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get());
		tag(BlockTags.CLIMBABLE).add(UGBlocks.DROOPVINE_PLANT.get(), UGBlocks.DROOPVINE.get());
		tag(BlockTags.STRIDER_WARM_BLOCKS).add(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.UTHERIUM_GROWTH.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get());
		tag(BlockTags.FENCE_GATES).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get(), UGBlocks.ANCIENT_ROOT_FENCE_GATE.get());
		tag(BlockTags.MUSHROOM_GROW_BLOCK).addTag(UGTags.Blocks.BASE_STONE_UNDERGARDEN).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(BlockTags.FLOWER_POTS).add(UGBlocks.POTTED_SMOGSTEM_SAPLING.get(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get(), UGBlocks.POTTED_SHIMMERWEED.get(), UGBlocks.POTTED_INDIGO_MUSHROOM.get(), UGBlocks.POTTED_VEIL_MUSHROOM.get(), UGBlocks.POTTED_INK_MUSHROOM.get(), UGBlocks.POTTED_BLOOD_MUSHROOM.get(), UGBlocks.POTTED_PUFF_MUSHROOM.get(), UGBlocks.POTTED_GRONGLE_SAPLING.get(), UGBlocks.POTTED_AMOROUS_BRISTLE.get(), UGBlocks.POTTED_MISERABELL.get(), UGBlocks.POTTED_BUTTERBUNCH.get());
		tag(BlockTags.WOOL_CARPETS).add(UGBlocks.MOGMOSS_RUG.get(), UGBlocks.BLUE_MOGMOSS_RUG.get());
		tag(BlockTags.COAL_ORES).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get());
		tag(BlockTags.IRON_ORES).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get());
		tag(BlockTags.GOLD_ORES).add(UGBlocks.DEPTHROCK_GOLD_ORE.get());
		tag(BlockTags.DIAMOND_ORES).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
		tag(BlockTags.DIRT).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.PARROTS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.RABBITS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.FOXES_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.WOLVES_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.IMPERMEABLE).add(UGBlocks.SEDIMENT_GLASS.get());
		tag(BlockTags.CAULDRONS).add(UGBlocks.VIRULENT_MIX_CAULDRON.get());
		tag(BlockTags.MAINTAINS_FARMLAND).add(UGBlocks.GLOOMGOURD_STEM.get(), UGBlocks.GLOOMGOURD_STEM_ATTACHED.get());

		//common
		tag(Tags.Blocks.ORES).addTag(UGTags.Blocks.ORES_CLOGGRUM).addTag(UGTags.Blocks.ORES_FROSTSTEEL).addTag(UGTags.Blocks.ORES_UTHERIUM).addTag(UGTags.Blocks.ORES_REGALIUM).addTag(UGTags.Blocks.ORES_ROGDORIUM);
		tag(Tags.Blocks.ORE_RATES_SINGULAR).addTag(UGTags.Blocks.ORES_CLOGGRUM).addTag(UGTags.Blocks.ORES_FROSTSTEEL).addTag(UGTags.Blocks.ORES_UTHERIUM).addTag(UGTags.Blocks.ORES_REGALIUM).addTag(UGTags.Blocks.ORES_ROGDORIUM);
		tag(Tags.Blocks.ORE_RATES_SPARSE).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get(), UGBlocks.DEPTHROCK_GOLD_ORE.get());
		tag(Tags.Blocks.STORAGE_BLOCKS).addTag(UGTags.Blocks.STORAGE_BLOCKS_CLOGGRUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL).addTag(UGTags.Blocks.STORAGE_BLOCKS_UTHERIUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_REGALIUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_ROGDORIUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_RAW_CLOGGRUM).addTag(UGTags.Blocks.STORAGE_BLOCKS_RAW_FROSTSTEEL).addTag(UGTags.Blocks.STORAGE_BLOCKS_FORGOTTEN_METAL);
		tag(Tags.Blocks.COBBLESTONES).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get(), UGBlocks.DREADROCK.get());
		tag(Tags.Blocks.FENCE_GATES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get(), UGBlocks.ANCIENT_ROOT_FENCE_GATE.get());
		tag(Tags.Blocks.FENCES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get(), UGBlocks.ANCIENT_ROOT_FENCE.get());
		tag(Tags.Blocks.ORES_COAL).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get());
		tag(Tags.Blocks.ORES_IRON).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get());
		tag(Tags.Blocks.ORES_GOLD).add(UGBlocks.DEPTHROCK_GOLD_ORE.get());
		tag(Tags.Blocks.ORES_DIAMOND).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
		tag(Tags.Blocks.STONES).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get(), UGBlocks.DREADROCK.get());
		tag(Tags.Blocks.GLASS_BLOCKS).add(UGBlocks.SEDIMENT_GLASS.get());
		tag(Tags.Blocks.GLASS_BLOCKS_COLORLESS).add(UGBlocks.SEDIMENT_GLASS.get());
		//tag(Tags.Blocks.GLASS_SILICA).add(UGBlocks.SEDIMENT_GLASS.get());
		tag(Tags.Blocks.GLASS_PANES).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
		tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
		tag(Tags.Blocks.SANDS).add(UGBlocks.SEDIMENT.get());
		tag(Tags.Blocks.SANDS_COLORLESS).add(UGBlocks.SEDIMENT.get());

		//huge mineables lists!
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				UGBlocks.DEPTHROCK.get(),
				UGBlocks.POLISHED_DEPTHROCK.get(),
				UGBlocks.DEPTHROCK_BRICKS.get(),
				UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(),
				UGBlocks.CHISELED_DEPTHROCK_BRICKS.get(),
				UGBlocks.DEPTHROCK_TILES.get(),
				UGBlocks.DEPTHROCK_STAIRS.get(),
				UGBlocks.POLISHED_DEPTHROCK_STAIRS.get(),
				UGBlocks.DEPTHROCK_BRICK_STAIRS.get(),
				UGBlocks.DEPTHROCK_TILE_STAIRS.get(),
				UGBlocks.DEPTHROCK_SLAB.get(),
				UGBlocks.POLISHED_DEPTHROCK_SLAB.get(),
				UGBlocks.DEPTHROCK_BRICK_SLAB.get(),
				UGBlocks.DEPTHROCK_TILE_SLAB.get(),
				UGBlocks.DEPTHROCK_WALL.get(),
				UGBlocks.POLISHED_DEPTHROCK_WALL.get(),
				UGBlocks.DEPTHROCK_BRICK_WALL.get(),
				UGBlocks.DEPTHROCK_BUTTON.get(),
				UGBlocks.DEPTHROCK_PRESSURE_PLATE.get(),
				UGBlocks.SHIVERSTONE.get(),
				UGBlocks.SHIVERSTONE_BRICKS.get(),
				UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(),
				UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get(),
				UGBlocks.SHIVERSTONE_STAIRS.get(),
				UGBlocks.SHIVERSTONE_BRICK_STAIRS.get(),
				UGBlocks.SHIVERSTONE_SLAB.get(),
				UGBlocks.SHIVERSTONE_BRICK_SLAB.get(),
				UGBlocks.SHIVERSTONE_WALL.get(),
				UGBlocks.SHIVERSTONE_BRICK_WALL.get(),
				UGBlocks.SHIVERSTONE_BUTTON.get(),
				UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get(),
				UGBlocks.DREADROCK.get(),
				UGBlocks.TREMBLECRUST.get(),
				UGBlocks.LOOSE_TREMBLECRUST.get(),
				UGBlocks.TREMBLECRUST_BRICKS.get(),
				UGBlocks.CRACKED_TREMBLECRUST_BRICKS.get(),
				UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get(),
				UGBlocks.TREMBLECRUST_STAIRS.get(),
				UGBlocks.TREMBLECRUST_BRICK_STAIRS.get(),
				UGBlocks.TREMBLECRUST_SLAB.get(),
				UGBlocks.TREMBLECRUST_BRICK_SLAB.get(),
				UGBlocks.TREMBLECRUST_WALL.get(),
				UGBlocks.TREMBLECRUST_BRICK_WALL.get(),
				UGBlocks.TREMBLECRUST_BUTTON.get(),
				UGBlocks.TREMBLECRUST_PRESSURE_PLATE.get(),
				UGBlocks.DEPTHROCK_COAL_ORE.get(),
				UGBlocks.SHIVERSTONE_COAL_ORE.get(),
				UGBlocks.DEPTHROCK_IRON_ORE.get(),
				UGBlocks.SHIVERSTONE_IRON_ORE.get(),
				UGBlocks.DEPTHROCK_GOLD_ORE.get(),
				UGBlocks.DEPTHROCK_DIAMOND_ORE.get(),
				UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(),
				UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(),
				UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(),
				UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(),
				UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(),
				UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(),
				UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(),
				UGBlocks.DEPTHROCK_REGALIUM_ORE.get(),
				UGBlocks.SHIVERSTONE_REGALIUM_ORE.get(),
				UGBlocks.DREADROCK_ROGDORIUM_ORE.get(),
				UGBlocks.RAW_CLOGGRUM_BLOCK.get(),
				UGBlocks.RAW_FROSTSTEEL_BLOCK.get(),
				UGBlocks.CLOGGRUM_BLOCK.get(),
				UGBlocks.FROSTSTEEL_BLOCK.get(),
				UGBlocks.UTHERIUM_BLOCK.get(),
				UGBlocks.REGALIUM_BLOCK.get(),
				UGBlocks.ROGDORIUM_BLOCK.get(),
				UGBlocks.FORGOTTEN_BLOCK.get(),
				UGBlocks.SMOG_VENT.get(),
				UGBlocks.CLOGGRUM_BARS.get(),
				UGBlocks.CLOGGRUM_TILES.get(),
				UGBlocks.CLOGGRUM_TILE_STAIRS.get(),
				UGBlocks.CLOGGRUM_TILE_SLAB.get(),
				UGBlocks.DEPTHROCK_BED.get(),
				UGBlocks.CLOGGRUM_LANTERN.get(),
				UGBlocks.VIRULENT_MIX_CAULDRON.get(),
				UGBlocks.UTHERIUM_GROWTH.get(),
				UGBlocks.DREADROCK_UTHERIUM_ORE.get()
		);
		tag(BlockTags.MINEABLE_WITH_AXE).add(
				UGBlocks.GLOOMGOURD.get(),
				UGBlocks.CARVED_GLOOMGOURD.get(),
				UGBlocks.GLOOM_O_LANTERN.get(),
				UGBlocks.SHARD_O_LANTERN.get(),
				UGBlocks.BOOMGOURD.get(),
				UGBlocks.GLOOMGOURD_STEM.get(),
				UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(),
				UGBlocks.INDIGO_MUSHROOM_CAP.get(),
				UGBlocks.INDIGO_MUSHROOM_STEM.get(),
				UGBlocks.VEIL_MUSHROOM_CAP.get(),
				UGBlocks.VEIL_MUSHROOM_STEM.get(),
				UGBlocks.INK_MUSHROOM_CAP.get(),
				UGBlocks.BLOOD_MUSHROOM_CAP.get(),
				UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(),
				UGBlocks.BLOOD_MUSHROOM_STEM.get(),
				UGBlocks.SMOGSTEM_LOG.get(),
				UGBlocks.STRIPPED_SMOGSTEM_LOG.get(),
				UGBlocks.SMOGSTEM_WOOD.get(),
				UGBlocks.STRIPPED_SMOGSTEM_WOOD.get(),
				UGBlocks.SMOGSTEM_PLANKS.get(),
				UGBlocks.SMOGSTEM_STAIRS.get(),
				UGBlocks.SMOGSTEM_SLAB.get(),
				UGBlocks.SMOGSTEM_FENCE.get(),
				UGBlocks.SMOGSTEM_FENCE_GATE.get(),
				UGBlocks.SMOGSTEM_DOOR.get(),
				UGBlocks.SMOGSTEM_TRAPDOOR.get(),
				UGBlocks.SMOGSTEM_BUTTON.get(),
				UGBlocks.SMOGSTEM_PRESSURE_PLATE.get(),
				UGBlocks.SMOGSTEM_SIGN.get(),
				UGBlocks.SMOGSTEM_WALL_SIGN.get(),
				UGBlocks.WIGGLEWOOD_LOG.get(),
				UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(),
				UGBlocks.WIGGLEWOOD_WOOD.get(),
				UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get(),
				UGBlocks.WIGGLEWOOD_PLANKS.get(),
				UGBlocks.WIGGLEWOOD_STAIRS.get(),
				UGBlocks.WIGGLEWOOD_SLAB.get(),
				UGBlocks.WIGGLEWOOD_FENCE.get(),
				UGBlocks.WIGGLEWOOD_FENCE_GATE.get(),
				UGBlocks.WIGGLEWOOD_DOOR.get(),
				UGBlocks.WIGGLEWOOD_TRAPDOOR.get(),
				UGBlocks.WIGGLEWOOD_BUTTON.get(),
				UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get(),
				UGBlocks.WIGGLEWOOD_SIGN.get(),
				UGBlocks.WIGGLEWOOD_WALL_SIGN.get(),
				UGBlocks.GRONGLE_LOG.get(),
				UGBlocks.STRIPPED_GRONGLE_LOG.get(),
				UGBlocks.GRONGLE_WOOD.get(),
				UGBlocks.STRIPPED_GRONGLE_WOOD.get(),
				UGBlocks.GRONGLE_PLANKS.get(),
				UGBlocks.GRONGLE_STAIRS.get(),
				UGBlocks.GRONGLE_SLAB.get(),
				UGBlocks.GRONGLE_FENCE.get(),
				UGBlocks.GRONGLE_FENCE_GATE.get(),
				UGBlocks.GRONGLE_DOOR.get(),
				UGBlocks.GRONGLE_TRAPDOOR.get(),
				UGBlocks.GRONGLE_BUTTON.get(),
				UGBlocks.GRONGLE_PRESSURE_PLATE.get(),
				UGBlocks.GRONGLE_SIGN.get(),
				UGBlocks.GRONGLE_WALL_SIGN.get(),
				UGBlocks.ANCIENT_ROOT.get(),
				UGBlocks.DENIZEN_TOTEM.get(),
				UGBlocks.ANCIENT_ROOT_PLANKS.get(),
				UGBlocks.ANCIENT_ROOT_STAIRS.get(),
				UGBlocks.ANCIENT_ROOT_SLAB.get(),
				UGBlocks.ANCIENT_ROOT_FENCE.get(),
				UGBlocks.ANCIENT_ROOT_FENCE_GATE.get(),
				UGBlocks.ANCIENT_ROOT_DOOR.get(),
				UGBlocks.ANCIENT_ROOT_TRAPDOOR.get(),
				UGBlocks.ANCIENT_ROOT_BUTTON.get(),
				UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE.get(),
				UGBlocks.ANCIENT_ROOT_SIGN.get(),
				UGBlocks.ANCIENT_ROOT_WALL_SIGN.get()
		);
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
				UGBlocks.DEEPTURF_BLOCK.get(),
				UGBlocks.ASHEN_DEEPTURF_BLOCK.get(),
				UGBlocks.FROZEN_DEEPTURF_BLOCK.get(),
				UGBlocks.DEEPSOIL.get(),
				UGBlocks.COARSE_DEEPSOIL.get(),
				UGBlocks.DEEPSOIL_FARMLAND.get(),
				UGBlocks.GOO.get(),
				UGBlocks.GOO_BLOCK.get(),
				UGBlocks.SEDIMENT.get()
		);
		tag(BlockTags.MINEABLE_WITH_HOE).add(
				UGBlocks.DROOPVINE.get(),
				UGBlocks.DROOPVINE_PLANT.get(),
				UGBlocks.SMOGSTEM_LEAVES.get(),
				UGBlocks.WIGGLEWOOD_LEAVES.get(),
				UGBlocks.GRONGLE_LEAVES.get(),
				UGBlocks.HANGING_GRONGLE_LEAVES.get()
		);
		tag(BlockTags.NEEDS_STONE_TOOL).add(
				UGBlocks.DEPTHROCK_IRON_ORE.get(),
				UGBlocks.SHIVERSTONE_IRON_ORE.get(),
				UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(),
				UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(),
				UGBlocks.RAW_CLOGGRUM_BLOCK.get(),
				UGBlocks.CLOGGRUM_BLOCK.get(),
				UGBlocks.CLOGGRUM_BARS.get(),
				UGBlocks.CLOGGRUM_TILES.get(),
				UGBlocks.CLOGGRUM_TILE_STAIRS.get(),
				UGBlocks.CLOGGRUM_TILE_SLAB.get()
		);
		tag(BlockTags.NEEDS_IRON_TOOL).add(
				UGBlocks.DEPTHROCK_GOLD_ORE.get(),
				UGBlocks.DEPTHROCK_DIAMOND_ORE.get(),
				UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(),
				UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(),
				UGBlocks.DEPTHROCK_REGALIUM_ORE.get(),
				UGBlocks.SHIVERSTONE_REGALIUM_ORE.get(),
				UGBlocks.RAW_FROSTSTEEL_BLOCK.get(),
				UGBlocks.FROSTSTEEL_BLOCK.get(),
				UGBlocks.REGALIUM_BLOCK.get(),
				UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(),
				UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(),
				UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(),
				UGBlocks.UTHERIUM_BLOCK.get(),
				UGBlocks.FORGOTTEN_BLOCK.get()
		);
	}
}