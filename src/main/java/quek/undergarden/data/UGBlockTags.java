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
        tag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.indigo_mushroom.get(), UGBlocks.indigo_mushroom.get(), UGBlocks.veil_mushroom.get(), UGBlocks.blood_mushroom.get());
        tag(UGTags.Blocks.PORTAL_FRAME_BLOCKS).add(Blocks.STONE_BRICKS, UGBlocks.depthrock_bricks.get(), UGBlocks.shiverstone_bricks.get());
        tag(UGTags.Blocks.SMOGSTEM_LOGS).add(UGBlocks.smogstem_log.get(), UGBlocks.stripped_smogstem_log.get(), UGBlocks.smogstem_wood.get(), UGBlocks.stripped_smogstem_wood.get());
        tag(UGTags.Blocks.WIGGLEWOOD_LOGS).add(UGBlocks.wigglewood_log.get(), UGBlocks.stripped_wigglewood_log.get(), UGBlocks.wigglewood_wood.get(), UGBlocks.stripped_wigglewood_wood.get());
        tag(UGTags.Blocks.GRONGLE_STEMS).add(UGBlocks.grongle_stem.get(), UGBlocks.stripped_grongle_stem.get(), UGBlocks.grongle_hyphae.get(), UGBlocks.stripped_grongle_hyphae.get());

        //vanilla
        tag(BlockTags.PLANKS).add(UGBlocks.smogstem_planks.get(), UGBlocks.wigglewood_planks.get(), UGBlocks.grongle_planks.get());
        tag(BlockTags.STONE_BRICKS).add(UGBlocks.depthrock_bricks.get(), UGBlocks.cracked_depthrock_bricks.get(), UGBlocks.chiseled_depthrock_bricks.get(), UGBlocks.shiverstone_bricks.get(), UGBlocks.cracked_shiverstone_bricks.get(), UGBlocks.chiseled_shiverstone_bricks.get(), UGBlocks.tremblecrust_bricks.get());
        tag(BlockTags.WOODEN_BUTTONS).add(UGBlocks.smogstem_button.get(), UGBlocks.wigglewood_button.get(), UGBlocks.grongle_button.get());
        tag(BlockTags.BUTTONS).add(UGBlocks.depthrock_button.get(), UGBlocks.shiverstone_button.get());
        tag(BlockTags.WOODEN_DOORS).add(UGBlocks.smogstem_door.get(), UGBlocks.wigglewood_door.get(), UGBlocks.grongle_door.get());
        tag(BlockTags.WOODEN_STAIRS).add(UGBlocks.smogstem_stairs.get(), UGBlocks.wigglewood_stairs.get(), UGBlocks.grongle_stairs.get());
        tag(BlockTags.WOODEN_SLABS).add(UGBlocks.smogstem_slab.get(), UGBlocks.wigglewood_slab.get(), UGBlocks.grongle_slab.get());
        tag(BlockTags.WOODEN_FENCES).add(UGBlocks.smogstem_fence.get(), UGBlocks.wigglewood_fence.get(), UGBlocks.grongle_fence.get());
        tag(BlockTags.SAPLINGS).add(UGBlocks.smogstem_sapling.get(), UGBlocks.wigglewood_sapling.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(UGTags.Blocks.SMOGSTEM_LOGS).addTag(UGTags.Blocks.WIGGLEWOOD_LOGS);
        tag(BlockTags.SMALL_FLOWERS).add(UGBlocks.shimmerweed.get());
        tag(BlockTags.ENDERMAN_HOLDABLE).addTag(UGTags.Blocks.MUSHROOMS).add(UGBlocks.deepturf_block.get(), UGBlocks.deepsoil.get(), UGBlocks.coarse_deepsoil.get(), UGBlocks.gloomgourd.get(), UGBlocks.carved_gloomgourd.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(UGBlocks.smogstem_pressure_plate.get(), UGBlocks.wigglewood_pressure_plate.get(), UGBlocks.grongle_pressure_plate.get());
        tag(BlockTags.STONE_PRESSURE_PLATES).add(UGBlocks.depthrock_pressure_plate.get(), UGBlocks.shiverstone_pressure_plate.get());
        tag(BlockTags.WALLS).add(UGBlocks.depthrock_wall.get(), UGBlocks.depthrock_brick_wall.get(), UGBlocks.shiverstone_wall.get(), UGBlocks.shiverstone_brick_wall.get());
        tag(BlockTags.LEAVES).add(UGBlocks.smogstem_leaves.get(), UGBlocks.wigglewood_leaves.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(UGBlocks.smogstem_trapdoor.get(), UGBlocks.wigglewood_trapdoor.get(), UGBlocks.grongle_trapdoor.get());
        tag(BlockTags.BAMBOO_PLANTABLE_ON).add(UGBlocks.deepturf_block.get(), UGBlocks.deepsoil.get(), UGBlocks.coarse_deepsoil.get());
        //TODO standing & wall signs
        tag(BlockTags.TALL_FLOWERS).add(UGBlocks.tall_shimmerweed.get());
        tag(BlockTags.CROPS).add(UGBlocks.gloomgourd_stem.get());
        tag(BlockTags.BEE_GROWABLES).add(UGBlocks.underbean_bush.get(), UGBlocks.blisterberry_bush.get());
        tag(BlockTags.PORTALS).add(UGBlocks.undergarden_portal.get()); //TODO otherside portal
        tag(BlockTags.BEACON_BASE_BLOCKS).add(UGBlocks.cloggrum_block.get(), UGBlocks.froststeel_block.get(), UGBlocks.utherium_block.get(), UGBlocks.regalium_block.get(), UGBlocks.forgotten_block.get());
        tag(BlockTags.WALL_POST_OVERRIDE).add(UGBlocks.shard_torch.get(), UGBlocks.shard_wall_torch.get());
        tag(BlockTags.CLIMBABLE).add(UGBlocks.droopvine.get(), UGBlocks.droopvine_top.get());
        tag(BlockTags.GOLD_ORES).add(UGBlocks.gold_ore.get());
        tag(BlockTags.NON_FLAMMABLE_WOOD).addTag(UGTags.Blocks.GRONGLE_STEMS).add(UGBlocks.grongle_planks.get(), UGBlocks.grongle_slab.get(), UGBlocks.grongle_pressure_plate.get(), UGBlocks.grongle_fence.get(), UGBlocks.grongle_trapdoor.get(), UGBlocks.grongle_fence_gate.get(), UGBlocks.grongle_stairs.get(), UGBlocks.grongle_button.get(), UGBlocks.grongle_door.get()); //TODO grongle sign
        tag(BlockTags.STRIDER_WARM_BLOCKS).add(UGBlocks.utherium_ore.get(), UGBlocks.otherside_utherium_ore.get(), UGBlocks.utherium_block.get());
        tag(BlockTags.FENCE_GATES).add(UGBlocks.smogstem_fence_gate.get(), UGBlocks.wigglewood_fence_gate.get(), UGBlocks.grongle_fence_gate.get());
        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(UGBlocks.deepturf_block.get());

        //forge
        tag(Tags.Blocks.COBBLESTONE).add(UGBlocks.depthrock.get(), UGBlocks.shiverstone.get());
        tag(Tags.Blocks.DIRT).add(UGBlocks.deepturf_block.get(), UGBlocks.deepsoil.get(), UGBlocks.coarse_deepsoil.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(UGBlocks.smogstem_fence_gate.get(), UGBlocks.wigglewood_fence_gate.get(), UGBlocks.grongle_fence_gate.get());
        tag(Tags.Blocks.FENCES_WOODEN).add(UGBlocks.smogstem_fence.get(), UGBlocks.wigglewood_fence.get(), UGBlocks.grongle_fence.get());
        tag(Tags.Blocks.ORES).add(UGBlocks.cloggrum_ore.get(), UGBlocks.froststeel_ore.get(), UGBlocks.utherium_ore.get(), UGBlocks.regalium_ore.get());
        tag(Tags.Blocks.ORES_COAL).add(UGBlocks.coal_ore.get());
        tag(Tags.Blocks.ORES_DIAMOND).add(UGBlocks.diamond_ore.get());
        tag(Tags.Blocks.ORES_IRON).add(UGBlocks.iron_ore.get());
        tag(Tags.Blocks.STONE).add(UGBlocks.depthrock.get(), UGBlocks.shiverstone.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).add(UGBlocks.cloggrum_block.get(), UGBlocks.froststeel_block.get(), UGBlocks.utherium_block.get(), UGBlocks.regalium_block.get(), UGBlocks.forgotten_block.get());
    }

    private TagsProvider.Builder<Block> tag(ITag.INamedTag<Block> tag) {
        return this.getOrCreateBuilder(tag);
    }
}
