package quek.undergarden.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.UGMod;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;

public class UGBlockTags extends BlockTagsProvider {

    public UGBlockTags(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, UGMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Block Tags";
    }

    @Override
    protected void registerTags() {
        //undergarden
        tag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.BLOOD_MUSHROOM.get());
        tag(UGTags.Blocks.PORTAL_FRAME_BLOCKS).add(Blocks.STONE_BRICKS, UGBlocks.DEPTHROCK_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get());
        tag(UGTags.Blocks.SMOGSTEM_LOGS).add(UGBlocks.SMOGSTEM_LOG.get(), UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_WOOD.get(), UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
        tag(UGTags.Blocks.WIGGLEWOOD_LOGS).add(UGBlocks.WIGGLEWOOD_LOG.get(), UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_WOOD.get(), UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
        tag(UGTags.Blocks.GRONGLE_STEMS).add(UGBlocks.GRONGLE_STEM.get(), UGBlocks.STRIPPED_GRONGLE_STEM.get(), UGBlocks.GRONGLE_HYPHAE.get(), UGBlocks.STRIPPED_GRONGLE_HYPHAE.get());

        //vanilla
        tag(BlockTags.PLANKS).add(UGBlocks.SMOGSTEM_PLANKS.get(), UGBlocks.WIGGLEWOOD_PLANKS.get(), UGBlocks.GRONGLE_PLANKS.get());
        tag(BlockTags.STONE_BRICKS).add(UGBlocks.DEPTHROCK_BRICKS.get(), UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(), UGBlocks.CHISELED_DEPTHROCK_BRICKS.get(), UGBlocks.SHIVERSTONE_BRICKS.get(), UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(), UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get(), UGBlocks.TREMBLECRUST_BRICKS.get());
        tag(BlockTags.WOODEN_BUTTONS).add(UGBlocks.SMOGSTEM_BUTTON.get(), UGBlocks.WIGGLEWOOD_BUTTON.get(), UGBlocks.GRONGLE_BUTTON.get());
        tag(BlockTags.BUTTONS).add(UGBlocks.DEPTHROCK_BUTTON.get(), UGBlocks.SHIVERSTONE_BUTTON.get());
        tag(BlockTags.WOODEN_DOORS).add(UGBlocks.SMOGSTEM_DOOR.get(), UGBlocks.WIGGLEWOOD_DOOR.get(), UGBlocks.GRONGLE_DOOR.get());
        tag(BlockTags.WOODEN_STAIRS).add(UGBlocks.SMOGSTEM_STAIRS.get(), UGBlocks.WIGGLEWOOD_STAIRS.get(), UGBlocks.GRONGLE_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS).add(UGBlocks.SMOGSTEM_SLAB.get(), UGBlocks.WIGGLEWOOD_SLAB.get(), UGBlocks.GRONGLE_SLAB.get());
        tag(BlockTags.WOODEN_FENCES).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get());
        tag(BlockTags.SAPLINGS).add(UGBlocks.SMOGSTEM_SAPLING.get(), UGBlocks.WIGGLEWOOD_SAPLING.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS);
        tag(BlockTags.SMALL_FLOWERS).add(UGBlocks.SHIMMERWEED.get());
        tag(BlockTags.ENDERMAN_HOLDABLE).addTag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get(), UGBlocks.GLOOMGOURD.get(), UGBlocks.CARVED_GLOOMGOURD.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get(), UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get(), UGBlocks.GRONGLE_PRESSURE_PLATE.get());
        tag(BlockTags.STONE_PRESSURE_PLATES).add(UGBlocks.DEPTHROCK_PRESSURE_PLATE.get(), UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get());
        tag(BlockTags.WALLS).add(UGBlocks.DEPTHROCK_WALL.get(), UGBlocks.DEPTHROCK_BRICK_WALL.get(), UGBlocks.SHIVERSTONE_WALL.get(), UGBlocks.SHIVERSTONE_BRICK_WALL.get());
        tag(BlockTags.LEAVES).add(UGBlocks.SMOGSTEM_LEAVES.get(), UGBlocks.WIGGLEWOOD_LEAVES.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(UGBlocks.SMOGSTEM_TRAPDOOR.get(), UGBlocks.WIGGLEWOOD_TRAPDOOR.get(), UGBlocks.GRONGLE_TRAPDOOR.get());
        tag(BlockTags.BAMBOO_PLANTABLE_ON).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
        //TODO standing & wall signs
        tag(BlockTags.TALL_FLOWERS).add(UGBlocks.TALL_SHIMMERWEED.get());
        tag(BlockTags.CROPS).add(UGBlocks.GLOOMGOURD_STEM.get());
        tag(BlockTags.BEE_GROWABLES).add(UGBlocks.UNDERBEAN_BUSH.get(), UGBlocks.BLISTERBERRY_BUSH.get());
        tag(BlockTags.PORTALS).add(UGBlocks.UNDERGARDEN_PORTAL.get()); //TODO otherside portal
        tag(BlockTags.BEACON_BASE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get(), UGBlocks.FORGOTTEN_BLOCK.get());
        tag(BlockTags.WALL_POST_OVERRIDE).add(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get());
        tag(BlockTags.CLIMBABLE).add(UGBlocks.DROOPVINE.get(), UGBlocks.DROOPVINE_TOP.get());
        tag(BlockTags.GOLD_ORES).add(UGBlocks.GOLD_ORE.get());
        tag(BlockTags.NON_FLAMMABLE_WOOD).addTag(UGTags.Blocks.GRONGLE_STEMS).add(UGBlocks.GRONGLE_PLANKS.get(), UGBlocks.GRONGLE_SLAB.get(), UGBlocks.GRONGLE_PRESSURE_PLATE.get(), UGBlocks.GRONGLE_FENCE.get(), UGBlocks.GRONGLE_TRAPDOOR.get(), UGBlocks.GRONGLE_FENCE_GATE.get(), UGBlocks.GRONGLE_STAIRS.get(), UGBlocks.GRONGLE_BUTTON.get(), UGBlocks.GRONGLE_DOOR.get()); //TODO grongle sign
        tag(BlockTags.STRIDER_WARM_BLOCKS).add(UGBlocks.UTHERIUM_ORE.get(), UGBlocks.OTHERSIDE_UTHERIUM_ORE.get(), UGBlocks.UTHERIUM_BLOCK.get());
        tag(BlockTags.FENCE_GATES).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get());
        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(UGBlocks.DEEPTURF_BLOCK.get());

        //forge
        tag(Tags.Blocks.COBBLESTONE).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get());
        tag(Tags.Blocks.DIRT).add(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.DEEPSOIL.get(), UGBlocks.COARSE_DEEPSOIL.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE_GATE.get(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), UGBlocks.GRONGLE_FENCE_GATE.get());
        tag(Tags.Blocks.FENCES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE.get(), UGBlocks.WIGGLEWOOD_FENCE.get(), UGBlocks.GRONGLE_FENCE.get());
        tag(Tags.Blocks.ORES).add(UGBlocks.CLOGGRUM_ORE.get(), UGBlocks.FROSTSTEEL_ORE.get(), UGBlocks.UTHERIUM_ORE.get(), UGBlocks.REGALIUM_ORE.get());
        tag(Tags.Blocks.ORES_COAL).add(UGBlocks.COAL_ORE.get());
        tag(Tags.Blocks.ORES_DIAMOND).add(UGBlocks.DIAMOND_ORE.get());
        tag(Tags.Blocks.ORES_IRON).add(UGBlocks.IRON_ORE.get());
        tag(Tags.Blocks.STONE).add(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get(), UGBlocks.FORGOTTEN_BLOCK.get());
    }

    private TagsProvider.Builder<Block> tag(ITag.INamedTag<Block> tag) {
        return this.getOrCreateBuilder(tag);
    }
}
