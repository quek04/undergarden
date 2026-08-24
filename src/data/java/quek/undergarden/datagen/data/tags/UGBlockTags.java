package quek.undergarden.datagen.data.tags;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGBlockTags extends BlockTagsProvider {

	public UGBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, Undergarden.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//shared block and item tags
		new UGBlockItemTags() {
			@Override
			protected TagAppender<Block, Block> tag(TagKey<Block> blockTag, TagKey<Item> itemTag) {
				return UGBlockTags.this.tag(blockTag);
			}
		}.run();

		//undergarden
		tag(UGTags.Blocks.BASE_STONE_UNDERGARDEN).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.BASE_STONE_OTHERSIDE).add(UGBlocks.TREMBLECRUST.get(), UGBlocks.LOOSE_TREMBLECRUST.get());
		tag(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES).add(UGBlocks.DEPTHROCK.get());
		tag(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES).add(UGBlocks.SHIVERSTONE.get());
		tag(UGTags.Blocks.DREADROCK_ORE_REPLACEABLES).add(UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES).add(UGBlocks.TREMBLECRUST.get(), UGBlocks.LOOSE_TREMBLECRUST.get(), UGBlocks.DEAD_WISPYGRASS_BLOCK.get());
		tag(UGTags.Blocks.UNDERGARDEN_CARVER_REPLACEABLES)
			.addTag(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES)
			.addTag(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES)
			.addTag(UGTags.Blocks.DREADROCK_ORE_REPLACEABLES)
			.addTag(BlockTags.DIRT)
			.addTag(UGTags.Blocks.ORES_CLOGGRUM)
			.addTag(UGTags.Blocks.ORES_FROSTSTEEL)
			.addTag(UGTags.Blocks.ORES_UTHERIUM)
			.addTag(UGTags.Blocks.ORES_REGALIUM)
			.addTag(UGTags.Blocks.ORES_ROGDORIUM)
			.add(
				UGBlocks.SEDIMENT.get(),
				UGBlocks.SEDIMENT_STONE.get(),
				Blocks.POWDER_SNOW
			);
		tag(UGTags.Blocks.OTHERSIDE_CARVER_REPLACEABLES).addTag(UGTags.Blocks.BASE_STONE_OTHERSIDE).add(UGBlocks.DEAD_WISPYGRASS_BLOCK.get());
		tag(UGTags.Blocks.PORTAL_FRAME_BLOCKS).add(
			Blocks.STONE_BRICKS,
			Blocks.MOSSY_STONE_BRICKS,
			Blocks.CRACKED_STONE_BRICKS,
			Blocks.CHISELED_STONE_BRICKS,
			Blocks.DEEPSLATE_BRICKS,
			Blocks.CRACKED_DEEPSLATE_BRICKS,
			Blocks.DEEPSLATE_TILES,
			Blocks.CRACKED_DEEPSLATE_TILES,
			Blocks.POLISHED_DEEPSLATE,
			Blocks.REINFORCED_DEEPSLATE,
			UGBlocks.POLISHED_DEPTHROCK.get(),
			UGBlocks.DEPTHROCK_BRICKS.get(),
			UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(),
			UGBlocks.CHISELED_DEPTHROCK_BRICKS.get(),
			UGBlocks.DEPTHROCK_TILES.get(),
			UGBlocks.SHIVERSTONE_BRICKS.get(),
			UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(),
			UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get(),
			UGBlocks.POLISHED_SEDIMENT_STONE.get(),
			UGBlocks.SEDIMENT_STONE_BRICKS.get(),
			UGBlocks.CHISELED_SEDIMENT_STONE.get()
		);
		tag(UGTags.Blocks.MUNCHER_BREAKABLES).addTag(Tags.Blocks.STONES).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER).addTag(UGTags.Blocks.BASE_STONE_UNDERGARDEN);
		tag(UGTags.Blocks.SCINTLING_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(UGTags.Blocks.SMOG_MOG_SPAWNABLE_ON).addTag(BlockTags.ANIMALS_SPAWNABLE_ON).add(UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.COARSE_DEEPSOIL.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get());
		tag(UGTags.Blocks.GREATER_DWELLER_SPAWNABLE_ON).addTag(BlockTags.ANIMALS_SPAWNABLE_ON).add(UGBlocks.DREADROCK.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(UGTags.Blocks.SUPPORTS_THORNREED).addTag(BlockTags.SUPPORTS_SUGAR_CANE);
		tag(UGTags.Blocks.SUPPORTS_THORNREED_ADJACENTLY).add(Blocks.FROSTED_ICE);
		tag(UGTags.Blocks.SUPPORTS_TWISTYBUSH).addTag(BlockTags.SUPPORTS_VEGETATION).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.SUPPORTS_WISPYGRASS).addTag(BlockTags.SUPPORTS_VEGETATION).add(UGBlocks.DEAD_WISPYGRASS_BLOCK.get());
		tag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL).add(
			UGBlocks.DREADROCK.get(),
			UGBlocks.DREADROCK_BRICKS.get(),
			UGBlocks.DREADROCK_STAIRS.get(),
			UGBlocks.DREADROCK_BRICK_STAIRS.get(),
			UGBlocks.DREADROCK_SLAB.get(),
			UGBlocks.DREADROCK_BRICK_SLAB.get(),
			UGBlocks.DREADROCK_WALL.get(),
			UGBlocks.DREADROCK_BRICK_WALL.get(),
			UGBlocks.DREADROCK_UTHERIUM_ORE.get(),
			UGBlocks.DREADROCK_ROGDORIUM_ORE.get(),
			UGBlocks.ROGDORIC_ANCIENT_ROOT.get()
		);

		tag(UGTags.Blocks.INCORRECT_FOR_FORGOTTEN_TOOL);

		//undergarden common
		tag(UGTags.Blocks.DEPTHROCK_GROUND).add(UGBlocks.DEPTHROCK.get());
		tag(UGTags.Blocks.DEPTHROCK_ORES).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.DEPTHROCK_GOLD_ORE.get(), UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.DEPTHROCK_REGALIUM_ORE.get());

		tag(UGTags.Blocks.SHIVERSTONE_GROUND).add(UGBlocks.SHIVERSTONE.get());
		tag(UGTags.Blocks.SHIVERSTONE_ORES).add(UGBlocks.SHIVERSTONE_COAL_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get());

		tag(UGTags.Blocks.DREADROCK_GROUND).add(UGBlocks.DREADROCK.get());
		tag(UGTags.Blocks.DREADROCK_ORES).add(UGBlocks.DREADROCK_ROGDORIUM_ORE.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get());

		tag(UGTags.Blocks.TREMBLECRUST_GROUND).add(UGBlocks.TREMBLECRUST.get());
		tag(UGTags.Blocks.TREMBLECRUST_ORES).add(UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get());

		//neoforge
		//yeah, this is literally the only tag we use that doesn't have an item counterpart
		tag(Tags.Blocks.VILLAGER_FARMLANDS).add(UGBlocks.DEEPSOIL_FARMLAND.get());

		//vanilla
		//tags are ordered the same way they are in VanillaBlockTagsProvider to hopefully reduce the pain of finding new tags they add
		tag(BlockTags.ENDERMAN_HOLDABLE).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get(), UGBlocks.GLOOMGOURD.get(), UGBlocks.CARVED_GLOOMGOURD.get(), UGBlocks.INK_MUSHROOM.get(), UGBlocks.BLOOD_MUSHROOM.get(), UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.PUFF_MUSHROOM.get(), UGBlocks.BOOMGOURD.get());
		tag(BlockTags.FLOWER_POTS).add(UGBlocks.POTTED_SMOGSTEM_SAPLING.get(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get(), UGBlocks.POTTED_SHIMMERWEED.get(), UGBlocks.POTTED_INDIGO_MUSHROOM.get(), UGBlocks.POTTED_VEIL_MUSHROOM.get(), UGBlocks.POTTED_INK_MUSHROOM.get(), UGBlocks.POTTED_BLOOD_MUSHROOM.get(), UGBlocks.POTTED_PUFF_MUSHROOM.get(), UGBlocks.POTTED_GRONGLE_SAPLING.get(), UGBlocks.POTTED_AMOROUS_BRISTLE.get(), UGBlocks.POTTED_MISERABELL.get(), UGBlocks.POTTED_BUTTERBUNCH.get());
		tag(BlockTags.STONE_PRESSURE_PLATES).add(UGBlocks.DEPTHROCK_PRESSURE_PLATE.get(), UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get(), UGBlocks.TREMBLECRUST_PRESSURE_PLATE.get(), UGBlocks.DREADROCK_PRESSURE_PLATE.get());
		tag(BlockTags.VALID_SPAWN).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.IMPERMEABLE).add(UGBlocks.SEDIMENT_GLASS.get());
		tag(BlockTags.WALL_SIGNS).add(UGBlocks.SMOGSTEM_WALL_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_SIGN.get());
		tag(BlockTags.WALL_HANGING_SIGNS).add(UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(), UGBlocks.GRONGLE_WALL_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN.get());
		tag(BlockTags.CROPS).add(UGBlocks.GLOOMGOURD_STEM.get());
		tag(BlockTags.BEE_GROWABLES).add(UGBlocks.UNDERBEAN_BUSH.get(), UGBlocks.BLISTERBERRY_BUSH.get(), UGBlocks.DROOPVINE_PLANT.get(), UGBlocks.DROOPVINE.get());
		tag(BlockTags.PORTALS).add(UGBlocks.UNDERGARDEN_PORTAL.get()); //TODO otherside portal
		tag(BlockTags.BEACON_BASE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get(), UGBlocks.FORGOTTEN_BLOCK.get());
		tag(BlockTags.WALL_POST_OVERRIDE).add(UGBlocks.SHARD_TORCH.get());
		tag(BlockTags.CLIMBABLE).add(UGBlocks.DROOPVINE_PLANT.get(), UGBlocks.DROOPVINE.get(), UGBlocks.CLOGGRUM_LADDER.get());
		tag(BlockTags.FALL_DAMAGE_RESETTING).add(UGBlocks.BLISTERBERRY_BUSH.get());
		tag(BlockTags.STRIDER_WARM_BLOCKS).add(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.UTHERIUM_GROWTH.get(), UGBlocks.DREADROCK_UTHERIUM_ORE.get());
		tag(BlockTags.EDIBLE_FOR_SHEEP).add(UGBlocks.DEEPTURF.get());
		tag(BlockTags.CAN_GLIDE_THROUGH).add(UGBlocks.DROOPVINE_PLANT.get(), UGBlocks.DROOPVINE.get(), UGBlocks.HANGING_GRONGLE_LEAVES.get(), UGBlocks.SEEPING_INK.get());
		tag(BlockTags.CAULDRONS).add(UGBlocks.VIRULENT_MIX_CAULDRON.get());
		tag(BlockTags.HUGE_BROWN_MUSHROOM_CAN_PLACE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(BlockTags.HUGE_RED_MUSHROOM_CAN_PLACE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(BlockTags.MINEABLE_WITH_AXE).add(
			UGBlocks.GLOOMGOURD.get(),
			UGBlocks.CARVED_GLOOMGOURD.get(),
			UGBlocks.GLOOM_O_LANTERN.get(),
			UGBlocks.SHARD_O_LANTERN.get(),
			UGBlocks.BOOMGOURD.get(),
			UGBlocks.INDIGO_MUSHROOM_CAP.get(),
			UGBlocks.INDIGO_MUSHROOM_STEM.get(),
			UGBlocks.VEIL_MUSHROOM_CAP.get(),
			UGBlocks.VEIL_MUSHROOM_STEM.get(),
			UGBlocks.INK_MUSHROOM_CAP.get(),
			UGBlocks.BLOOD_MUSHROOM_CAP.get(),
			UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(),
			UGBlocks.BLOOD_MUSHROOM_STEM.get(),
			UGBlocks.DENIZEN_TOTEM.get()
		);
		tag(BlockTags.MINEABLE_WITH_HOE).add(UGBlocks.HANGING_GRONGLE_LEAVES.get());
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
			UGBlocks.DEPTHROCK_PRESSURE_PLATE.get(),
			UGBlocks.SHIVERSTONE.get(),
			UGBlocks.SHIVERSTONE_BRICKS.get(),
			UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(),
			UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get(),
			UGBlocks.SHIVERSTONE_STAIRS.get(),
			UGBlocks.SHIVERSTONE_BRICK_STAIRS.get(),
			UGBlocks.SHIVERSTONE_SLAB.get(),
			UGBlocks.SHIVERSTONE_BRICK_SLAB.get(),
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
			UGBlocks.CLOGGRUM_TILES.get(),
			UGBlocks.CLOGGRUM_TILE_STAIRS.get(),
			UGBlocks.CLOGGRUM_TILE_SLAB.get(),
			UGBlocks.CLOGGRUM_PILLAR.get(),
			UGBlocks.CLOGGRUM_GRATE.get(),
			UGBlocks.CLOGGRUM_LADDER.get(),
			UGBlocks.DEPTHROCK_BED.get(),
			UGBlocks.VIRULENT_MIX_CAULDRON.get(),
			UGBlocks.UTHERIUM_GROWTH.get(),
			UGBlocks.DREADROCK_UTHERIUM_ORE.get(),
			UGBlocks.DREADROCK_BRICKS.get(),
			UGBlocks.DREADROCK_SLAB.get(),
			UGBlocks.DREADROCK_BRICK_SLAB.get(),
			UGBlocks.DREADROCK_STAIRS.get(),
			UGBlocks.DREADROCK_BRICK_STAIRS.get(),
			UGBlocks.DREADROCK_PRESSURE_PLATE.get(),
			UGBlocks.INFUSER.get(),
			UGBlocks.SEDIMENT_STONE.get(),
			UGBlocks.POLISHED_SEDIMENT_STONE.get(),
			UGBlocks.SEDIMENT_STONE_BRICKS.get(),
			UGBlocks.CHISELED_SEDIMENT_STONE.get(),
			UGBlocks.SEDIMENT_STONE_STAIRS.get(),
			UGBlocks.POLISHED_SEDIMENT_STONE_STAIRS.get(),
			UGBlocks.SEDIMENT_STONE_BRICK_STAIRS.get(),
			UGBlocks.SEDIMENT_STONE_SLAB.get(),
			UGBlocks.POLISHED_SEDIMENT_STONE_SLAB.get(),
			UGBlocks.SEDIMENT_STONE_BRICK_SLAB.get()
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
		tag(BlockTags.SWORD_EFFICIENT).add(UGBlocks.SEEPING_INK.get(), UGBlocks.HANGING_GRONGLE_LEAVES.get(), UGBlocks.GLOOMGOURD.get(), UGBlocks.CARVED_GLOOMGOURD.get(), UGBlocks.GLOOM_O_LANTERN.get(), UGBlocks.SHARD_O_LANTERN.get());
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
			UGBlocks.CLOGGRUM_TILE_SLAB.get(),
			UGBlocks.CLOGGRUM_PILLAR.get(),
			UGBlocks.CLOGGRUM_GRATE.get(),
			UGBlocks.CLOGGRUM_LADDER.get()
		);
		tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.INCORRECT_FOR_COPPER_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(UGTags.Blocks.NEEDS_FORGOTTEN_TOOL);
		tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.PARROTS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.RABBITS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.FOXES_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.WOLVES_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
		tag(BlockTags.CONVERTABLE_TO_MUD).add(UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
		tag(BlockTags.REPLACEABLE_BY_TREES).add(
			UGBlocks.DEEPTURF.get(),
			UGBlocks.TALL_DEEPTURF.get(),
			UGBlocks.ASHEN_DEEPTURF.get(),
			UGBlocks.FROZEN_DEEPTURF.get(),
			UGBlocks.TWISTYBUSH.get(),
			UGBlocks.SHIMMERWEED.get(),
			UGBlocks.TALL_SHIMMERWEED.get(),
			UGBlocks.SEEPING_INK.get(),
			UGBlocks.HANGING_GRONGLE_LEAVES.get());
		tag(BlockTags.REPLACEABLE_BY_MUSHROOMS).add(
			UGBlocks.DEEPTURF.get(),
			UGBlocks.TALL_DEEPTURF.get(),
			UGBlocks.ASHEN_DEEPTURF.get(),
			UGBlocks.FROZEN_DEEPTURF.get(),
			UGBlocks.TWISTYBUSH.get(),
			UGBlocks.SHIMMERWEED.get(),
			UGBlocks.TALL_SHIMMERWEED.get(),
			UGBlocks.SEEPING_INK.get(),
			UGBlocks.HANGING_GRONGLE_LEAVES.get(),
			UGBlocks.INK_MUSHROOM.get(),
			UGBlocks.BLOOD_MUSHROOM.get(),
			UGBlocks.INDIGO_MUSHROOM.get(),
			UGBlocks.VEIL_MUSHROOM.get(),
			UGBlocks.PUFF_MUSHROOM.get(),
			UGBlocks.BLOOD_MUSHROOM_CAP.get(),
			UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(),
			UGBlocks.VEIL_MUSHROOM_CAP.get(),
			UGBlocks.INDIGO_MUSHROOM_CAP.get(),
			UGBlocks.INK_MUSHROOM_CAP.get(),
			UGBlocks.PUFF_MUSHROOM_CAP.get());
		tag(BlockTags.SUPPORTS_VEGETATION).add(UGBlocks.DEEPSOIL_FARMLAND.get());
		tag(BlockTags.SUPPORTS_CROPS).add(UGBlocks.DEEPSOIL_FARMLAND.get());
		tag(BlockTags.SUPPORTS_BIG_DRIPLEAF).add(UGBlocks.DEEPSOIL_FARMLAND.get());
		tag(BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER).add(UGBlocks.DEEPSOIL_FARMLAND.get());
		tag(BlockTags.GROWS_CROPS).add(UGBlocks.DEEPSOIL_FARMLAND.get());
		this.tag(BlockTags.REPLACEABLE).addAll(provider.lookupOrThrow(Registries.BLOCK).listElements().filter(ref -> ref.getKey().identifier().getNamespace().equals(Undergarden.MODID)).map(Holder.Reference::value).filter(b -> b.defaultBlockState().canBeReplaced()));
		tag(BlockTags.MAINTAINS_FARMLAND).add(UGBlocks.GLOOMGOURD_STEM.get(), UGBlocks.GLOOMGOURD_STEM_ATTACHED.get());
		tag(BlockTags.HAPPY_GHAST_AVOIDS).add(UGBlocks.BLISTERBERRY_BUSH.get());
	}
}