package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;

public class UGBlockTags extends BlockTagsProvider {

    public UGBlockTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Undergarden.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Block Tags";
    }

    @Override
    protected void addTags() {
        //undergarden
        tag(UGTags.Blocks.BASE_STONE_UNDERGARDEN).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get());
        tag(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES).add(UGBlocks.DEPTHROCK.get());
        tag(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES).add(UGBlocks.SHIVERSTONE.get());
        tag(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES).add(UGBlocks.TREMBLECRUST.get(), UGBlocks.LOOSE_TREMBLECRUST.get());
        tag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.BLOOD_MUSHROOM.get());
        tag(UGTags.Blocks.PORTAL_FRAME_BLOCKS).add(Blocks.STONE_BRICKS, Blocks.DEEPSLATE_BRICKS, UGBlocks.DEPTHROCK_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get()).addTag(BlockTags.STONE_BRICKS);
        tag(UGTags.Blocks.SMOGSTEM_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
        tag(UGTags.Blocks.WIGGLEWOOD_LOGS).add(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
        tag(UGTags.Blocks.GRONGLE_LOGS).add(UGBlocks.GRONGLE_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.GRONGLE_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get());
        tag(UGTags.Blocks.MUNCHER_BREAKABLES).addTag(Tags.Blocks.STONE).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER).addTag(UGTags.Blocks.BASE_STONE_UNDERGARDEN);

        //undergarden forge
        tag(UGTags.Blocks.ORES_CLOGGRUM).add(UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get());
        tag(UGTags.Blocks.ORES_FROSTSTEEL).add(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get());
        tag(UGTags.Blocks.ORES_UTHERIUM).add(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get());
        tag(UGTags.Blocks.ORES_REGALIUM).add(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get());

        tag(UGTags.Blocks.STORAGE_BLOCKS_CLOGGRUM).add(UGBlocks.CLOGGRUM_BLOCK.get());
        tag(UGTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL).add(UGBlocks.FROSTSTEEL_BLOCK.get());
        tag(UGTags.Blocks.STORAGE_BLOCKS_UTHERIUM).add(UGBlocks.UTHERIUM_BLOCK.get());
        tag(UGTags.Blocks.STORAGE_BLOCKS_REGALIUM).add(UGBlocks.REGALIUM_BLOCK.get());
        tag(UGTags.Blocks.STORAGE_BLOCKS_FORGOTTEN_METAL).add(UGBlocks.FORGOTTEN_BLOCK.get());

        tag(UGTags.Blocks.STORAGE_BLOCKS_RAW_CLOGGRUM).add(UGBlocks.RAW_CLOGGRUM_BLOCK.get());
        tag(UGTags.Blocks.STORAGE_BLOCKS_RAW_FROSTSTEEL).add(UGBlocks.RAW_FROSTSTEEL_BLOCK.get());

        tag(UGTags.Blocks.DEPTHROCK_GROUND).add(UGBlocks.DEPTHROCK.get());
        tag(UGTags.Blocks.DEPTHROCK_ORES).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.DEPTHROCK_GOLD_ORE.get(), UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.DEPTHROCK_REGALIUM_ORE.get());

        tag(UGTags.Blocks.SHIVERSTONE_GROUND).add(UGBlocks.SHIVERSTONE.get());
        tag(UGTags.Blocks.SHIVERSTONE_ORES).add(UGBlocks.SHIVERSTONE_COAL_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(), UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_REGALIUM_ORE.get());

        tag(UGTags.Blocks.TREMBLECRUST_GROUND).add(UGBlocks.TREMBLECRUST.get());
        tag(UGTags.Blocks.TREMBLECRUST_ORES).add(UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get());

        //vanilla
        tag(BlockTags.PLANKS).add(UGBlocks.SMOGSTEM_PLANKS.get(), UGBlocks.WIGGLEWOOD_PLANKS.get(), UGBlocks.GRONGLE_PLANKS.get());
        tag(BlockTags.WOODEN_BUTTONS).add(UGBlocks.SMOGSTEM_BUTTON.get(), UGBlocks.WIGGLEWOOD_BUTTON.get(), UGBlocks.GRONGLE_BUTTON.get());
        tag(BlockTags.BUTTONS).add(UGBlocks.DEPTHROCK_BUTTON.get(), UGBlocks.SHIVERSTONE_BUTTON.get(), UGBlocks.SMOGSTEM_BUTTON.get(), UGBlocks.WIGGLEWOOD_BUTTON.get(), UGBlocks.GRONGLE_BUTTON.get());
        tag(BlockTags.WOODEN_DOORS).add(UGBlocks.SMOGSTEM_DOOR.get(), UGBlocks.WIGGLEWOOD_DOOR.get(), UGBlocks.GRONGLE_DOOR.get());
        tag(BlockTags.WOODEN_STAIRS).add(UGBlocks.SMOGSTEM_STAIRS.get(), UGBlocks.WIGGLEWOOD_STAIRS.get(), UGBlocks.GRONGLE_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS).add(UGBlocks.SMOGSTEM_SLAB.get(), UGBlocks.WIGGLEWOOD_SLAB.get(), UGBlocks.GRONGLE_SLAB.get());
        tag(BlockTags.WOODEN_FENCES).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get());
        tag(BlockTags.SAPLINGS).add(UGBlocks.SMOGSTEM_SAPLING.get(), UGBlocks.WIGGLEWOOD_SAPLING.get(), UGBlocks.GRONGLE_SAPLING.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS).addTag(UGTags.Blocks.GRONGLE_LOGS);
        tag(BlockTags.LOGS).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS).addTag(UGTags.Blocks.GRONGLE_LOGS);
        tag(BlockTags.SMALL_FLOWERS).add(UGBlocks.SHIMMERWEED.get());
        tag(BlockTags.ENDERMAN_HOLDABLE).addTag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get(), UGBlocks.GLOOMGOURD.get(), UGBlocks.CARVED_GLOOMGOURD.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get(), UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get(), UGBlocks.GRONGLE_PRESSURE_PLATE.get());
        tag(BlockTags.STONE_PRESSURE_PLATES).add(UGBlocks.DEPTHROCK_PRESSURE_PLATE.get(), UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get());
        tag(BlockTags.WALLS).add(UGBlocks.DEPTHROCK_WALL.get(), UGBlocks.POLISHED_DEPTHROCK_WALL.get(), UGBlocks.DEPTHROCK_BRICK_WALL.get(), UGBlocks.SHIVERSTONE_WALL.get(), UGBlocks.SHIVERSTONE_BRICK_WALL.get(), UGBlocks.TREMBLECRUST_WALL.get(), UGBlocks.TREMBLECRUST_BRICK_WALL.get());
        tag(BlockTags.LEAVES).add(UGBlocks.SMOGSTEM_LEAVES.get(), UGBlocks.WIGGLEWOOD_LEAVES.get(), UGBlocks.GRONGLE_LEAVES.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(UGBlocks.SMOGSTEM_TRAPDOOR.get(), UGBlocks.WIGGLEWOOD_TRAPDOOR.get(), UGBlocks.GRONGLE_TRAPDOOR.get());
        tag(BlockTags.BAMBOO_PLANTABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
        tag(BlockTags.STANDING_SIGNS).add(UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.GRONGLE_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(UGBlocks.SMOGSTEM_WALL_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get());
        tag(BlockTags.SIGNS).add(UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.GRONGLE_SIGN.get(), UGBlocks.SMOGSTEM_WALL_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get());
        tag(BlockTags.TALL_FLOWERS).add(UGBlocks.TALL_SHIMMERWEED.get());
        tag(BlockTags.CROPS).add(UGBlocks.GLOOMGOURD_STEM.get());
        tag(BlockTags.BEE_GROWABLES).add(UGBlocks.UNDERBEAN_BUSH.get(), UGBlocks.BLISTERBERRY_BUSH.get());
        tag(BlockTags.PORTALS).add(UGBlocks.UNDERGARDEN_PORTAL.get()); //TODO otherside portal
        tag(BlockTags.BEACON_BASE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get(), UGBlocks.FORGOTTEN_BLOCK.get());
        tag(BlockTags.WALL_POST_OVERRIDE).add(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get());
        tag(BlockTags.CLIMBABLE).add(UGBlocks.DROOPVINE_PLANT.get(), UGBlocks.DROOPVINE.get());
        tag(BlockTags.STRIDER_WARM_BLOCKS).add(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), UGBlocks.UTHERIUM_BLOCK.get());
        tag(BlockTags.FENCE_GATES).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get());
        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(UGBlocks.DEEPTURF_BLOCK.get());
        tag(BlockTags.FLOWER_POTS).add(UGBlocks.POTTED_SMOGSTEM_SAPLING.get(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get(), UGBlocks.POTTED_SHIMMERWEED.get(), UGBlocks.POTTED_INDIGO_MUSHROOM.get(), UGBlocks.POTTED_VEIL_MUSHROOM.get(), UGBlocks.POTTED_INK_MUSHROOM.get(), UGBlocks.POTTED_BLOOD_MUSHROOM.get(), UGBlocks.POTTED_GRONGLE_SAPLING.get());
        tag(BlockTags.CARPETS).add(UGBlocks.MOGMOSS_RUG.get());
        tag(BlockTags.COAL_ORES).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get());
        tag(BlockTags.IRON_ORES).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get());
        tag(BlockTags.GOLD_ORES).add(UGBlocks.DEPTHROCK_GOLD_ORE.get());
        tag(BlockTags.DIAMOND_ORES).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
        tag(BlockTags.DIRT).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
        tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
        tag(BlockTags.PARROTS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
        tag(BlockTags.RABBITS_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
        tag(BlockTags.FOXES_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
        tag(BlockTags.WOLVES_SPAWNABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get());
        tag(BlockTags.IMPERMEABLE).add(UGBlocks.SEDIMENT_GLASS.get());

        //forge
        tag(Tags.Blocks.ORES).addTags(UGTags.Blocks.ORES_CLOGGRUM, UGTags.Blocks.ORES_FROSTSTEEL, UGTags.Blocks.ORES_UTHERIUM, UGTags.Blocks.ORES_REGALIUM);
        tag(Tags.Blocks.ORE_RATES_SINGULAR).addTags(UGTags.Blocks.ORES_CLOGGRUM, UGTags.Blocks.ORES_FROSTSTEEL, UGTags.Blocks.ORES_UTHERIUM, UGTags.Blocks.ORES_REGALIUM);
        tag(Tags.Blocks.ORE_RATES_SPARSE).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get(), UGBlocks.DEPTHROCK_GOLD_ORE.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(UGTags.Blocks.STORAGE_BLOCKS_CLOGGRUM, UGTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL, UGTags.Blocks.STORAGE_BLOCKS_UTHERIUM, UGTags.Blocks.STORAGE_BLOCKS_REGALIUM, UGTags.Blocks.STORAGE_BLOCKS_RAW_CLOGGRUM, UGTags.Blocks.STORAGE_BLOCKS_RAW_FROSTSTEEL, UGTags.Blocks.STORAGE_BLOCKS_FORGOTTEN_METAL);
        tag(Tags.Blocks.COBBLESTONE).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get());
        tag(Tags.Blocks.FENCES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get());
        tag(Tags.Blocks.ORES_COAL).add(UGBlocks.DEPTHROCK_COAL_ORE.get(), UGBlocks.SHIVERSTONE_COAL_ORE.get());
        tag(Tags.Blocks.ORES_IRON).add(UGBlocks.DEPTHROCK_IRON_ORE.get(), UGBlocks.SHIVERSTONE_IRON_ORE.get());
        tag(Tags.Blocks.ORES_GOLD).add(UGBlocks.DEPTHROCK_GOLD_ORE.get());
        tag(Tags.Blocks.ORES_DIAMOND).add(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), UGBlocks.SHIVERSTONE_DIAMOND_ORE.get());
        tag(Tags.Blocks.STONE).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get());
        tag(Tags.Blocks.GLASS).add(UGBlocks.SEDIMENT_GLASS.get());
        tag(Tags.Blocks.GLASS_COLORLESS).add(UGBlocks.SEDIMENT_GLASS.get());
        tag(Tags.Blocks.GLASS_SILICA).add(UGBlocks.SEDIMENT_GLASS.get());
        tag(Tags.Blocks.GLASS_PANES).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
        tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
        tag(Tags.Blocks.SAND).add(UGBlocks.SEDIMENT.get());
        tag(Tags.Blocks.SAND_COLORLESS).add(UGBlocks.SEDIMENT.get());

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
                UGBlocks.RAW_CLOGGRUM_BLOCK.get(),
                UGBlocks.RAW_FROSTSTEEL_BLOCK.get(),
                UGBlocks.CLOGGRUM_BLOCK.get(),
                UGBlocks.FROSTSTEEL_BLOCK.get(),
                UGBlocks.UTHERIUM_BLOCK.get(),
                UGBlocks.REGALIUM_BLOCK.get(),
                UGBlocks.FORGOTTEN_BLOCK.get(),
                UGBlocks.SMOG_VENT.get(),
                UGBlocks.CLOGGRUM_BARS.get(),
                UGBlocks.CLOGGRUM_TILES.get(),
                UGBlocks.CLOGGRUM_TILE_STAIRS.get(),
                UGBlocks.CLOGGRUM_TILE_SLAB.get(),
                UGBlocks.DEPTHROCK_BED.get(),
                UGBlocks.CLOGGRUM_LANTERN.get()
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
                UGBlocks.INDIGO_MUSHROOM_STALK.get(),
                UGBlocks.VEIL_MUSHROOM_CAP.get(),
                UGBlocks.VEIL_MUSHROOM_STALK.get(),
                UGBlocks.INK_MUSHROOM_CAP.get(),
                UGBlocks.BLOOD_MUSHROOM_CAP.get(),
                UGBlocks.BLOOD_MUSHROOM_GLOBULE.get(),
                UGBlocks.BLOOD_MUSHROOM_STALK.get(),
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
                UGBlocks.GRONGLE_WALL_SIGN.get()
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