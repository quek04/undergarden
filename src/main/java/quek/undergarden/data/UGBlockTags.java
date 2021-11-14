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
        tag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.BLOOD_MUSHROOM.get());
        tag(UGTags.Blocks.PORTAL_FRAME_BLOCKS).add(Blocks.STONE_BRICKS, UGBlocks.DEPTHROCK_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get()).addTag(BlockTags.STONE_BRICKS);
        tag(UGTags.Blocks.SMOGSTEM_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
        tag(UGTags.Blocks.WIGGLEWOOD_LOGS).add(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
        tag(UGTags.Blocks.GRONGLE_LOGS).add(UGBlocks.GRONGLE_LOG.get(), UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.GRONGLE_WOOD.get(), UGBlocks.STRIPPED_GRONGLE_WOOD.get());
        tag(UGTags.Blocks.MUNCHER_BREAKABLES).addTag(Tags.Blocks.STONE).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER).addTag(UGTags.Blocks.BASE_STONE_UNDERGARDEN);

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
        tag(BlockTags.WALLS).add(UGBlocks.DEPTHROCK_WALL.get(), UGBlocks.DEPTHROCK_BRICK_WALL.get(), UGBlocks.SHIVERSTONE_WALL.get(), UGBlocks.SHIVERSTONE_BRICK_WALL.get(), UGBlocks.TREMBLECRUST_WALL.get(), UGBlocks.TREMBLECRUST_BRICK_WALL.get());
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
        tag(BlockTags.CLIMBABLE).add(UGBlocks.DROOPVINE.get(), UGBlocks.DROOPVINE_TOP.get());
        tag(BlockTags.GOLD_ORES).add(UGBlocks.GOLD_ORE.get());
        tag(BlockTags.STRIDER_WARM_BLOCKS).add(UGBlocks.UTHERIUM_ORE.get(), UGBlocks.OTHERSIDE_UTHERIUM_ORE.get(), UGBlocks.UTHERIUM_BLOCK.get());
        tag(BlockTags.FENCE_GATES).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get());
        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(UGBlocks.DEEPTURF_BLOCK.get());
        tag(BlockTags.FLOWER_POTS).add(UGBlocks.POTTED_SMOGSTEM_SAPLING.get(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get(), UGBlocks.POTTED_SHIMMERWEED.get(), UGBlocks.POTTED_INDIGO_MUSHROOM.get(), UGBlocks.POTTED_VEIL_MUSHROOM.get(), UGBlocks.POTTED_INK_MUSHROOM.get(), UGBlocks.POTTED_BLOOD_MUSHROOM.get(), UGBlocks.POTTED_GRONGLE_SAPLING.get());
        tag(BlockTags.CARPETS).add(UGBlocks.MOGMOSS_RUG.get());

        //forge
        tag(Tags.Blocks.COBBLESTONE).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get());
        tag(Tags.Blocks.DIRT).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get());
        tag(Tags.Blocks.FENCES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get());
        tag(Tags.Blocks.ORES).add(UGBlocks.CLOGGRUM_ORE.get(), UGBlocks.FROSTSTEEL_ORE.get(), UGBlocks.UTHERIUM_ORE.get(), UGBlocks.REGALIUM_ORE.get(), UGBlocks.OTHERSIDE_UTHERIUM_ORE.get());
        tag(Tags.Blocks.ORES_COAL).add(UGBlocks.COAL_ORE.get());
        tag(Tags.Blocks.ORES_DIAMOND).add(UGBlocks.DIAMOND_ORE.get());
        tag(Tags.Blocks.ORES_IRON).add(UGBlocks.IRON_ORE.get());
        tag(Tags.Blocks.STONE).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.TREMBLECRUST.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get(), UGBlocks.FORGOTTEN_BLOCK.get());
        tag(Tags.Blocks.GLASS).add(UGBlocks.SEDIMENT_GLASS.get());
        tag(Tags.Blocks.GLASS_COLORLESS).add(UGBlocks.SEDIMENT_GLASS.get());
        tag(Tags.Blocks.GLASS_PANES).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
        tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(UGBlocks.SEDIMENT_GLASS_PANE.get());
    }
}