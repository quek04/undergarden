package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.data.provider.UGBlockstateProvider;
import quek.undergarden.registry.UGBlocks;

public class UGBlockStates extends UGBlockstateProvider {

    public UGBlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Block States";
    }

    @Override
    protected void registerStatesAndModels() {
        torchBlock(UGBlocks.SHARD_TORCH, UGBlocks.SHARD_WALL_TORCH);
        block(UGBlocks.DEPTHROCK);
        block(UGBlocks.DEEPSOIL);
        crossBlock(UGBlocks.ASHEN_DEEPTURF);
        block(UGBlocks.DEPTHROCK_COAL_ORE);
        block(UGBlocks.DEPTHROCK_CLOGGRUM_ORE);
        block(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE);
        block(UGBlocks.DEPTHROCK_UTHERIUM_ORE);
        log(UGBlocks.SMOGSTEM_LOG, "smogstem_log");
        crossBlock(UGBlocks.SMOGSTEM_SAPLING);
        log(UGBlocks.WIGGLEWOOD_LOG, "wigglewood_log");
        crossBlock(UGBlocks.WIGGLEWOOD_SAPLING);
        block(UGBlocks.SMOGSTEM_PLANKS);
        block(UGBlocks.WIGGLEWOOD_PLANKS);
        block(UGBlocks.SMOGSTEM_LEAVES);
        block(UGBlocks.WIGGLEWOOD_LEAVES);
        crossBlock(UGBlocks.INDIGO_MUSHROOM);
        crossBlock(UGBlocks.VEIL_MUSHROOM);
        crossBlock(UGBlocks.INK_MUSHROOM);
        crossBlock(UGBlocks.BLOOD_MUSHROOM);
        block(UGBlocks.DEPTHROCK_BRICKS);
        block(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
        crossBlock(UGBlocks.GLOWING_KELP);
        crossBlock(UGBlocks.GLOWING_KELP_PLANT);
        block(UGBlocks.SHIVERSTONE);
        block(UGBlocks.SHIVERSTONE_BRICKS);
        block(UGBlocks.DEPTHROCK_REGALIUM_ORE);
        block(UGBlocks.TREMBLECRUST);
        block(UGBlocks.TREMBLECRUST_BRICKS);
        block(UGBlocks.CRACKED_TREMBLECRUST_BRICKS);
        block(UGBlocks.TREMBLECRUST_UTHERIUM_ORE);
        block(UGBlocks.LOOSE_TREMBLECRUST);
        block(UGBlocks.DEPTHROCK_IRON_ORE);
        block(UGBlocks.DEPTHROCK_GOLD_ORE);
        block(UGBlocks.DEPTHROCK_DIAMOND_ORE);
        crossBlock(UGBlocks.DROOPVINE_TOP);
        block(UGBlocks.COARSE_DEEPSOIL);
        crossBlock(UGBlocks.GRONGLE_SAPLING);
        block(UGBlocks.GRONGLE_LEAVES);
        log(UGBlocks.GRONGLE_LOG, "grongle_log");
        block(UGBlocks.GRONGLE_PLANKS);
        log(UGBlocks.STRIPPED_SMOGSTEM_LOG, "stripped_smogstem_log");
        log(UGBlocks.STRIPPED_WIGGLEWOOD_LOG, "stripped_wigglewood_log");
        log(UGBlocks.STRIPPED_GRONGLE_LOG, "stripped_grongle_log");
        block(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
        block(UGBlocks.BLOOD_MUSHROOM_GLOBULE);
        crossBlock(UGBlocks.SEEPING_INK);
        crossBlock(UGBlocks.MUSHROOM_VEIL);
        crossBlock(UGBlocks.MUSHROOM_VEIL_TOP);
        block(UGBlocks.FORGOTTEN_BLOCK);
        block(UGBlocks.CLOGGRUM_BLOCK);
        block(UGBlocks.FROSTSTEEL_BLOCK);
        block(UGBlocks.UTHERIUM_BLOCK);
        block(UGBlocks.CHISELED_DEPTHROCK_BRICKS);
        block(UGBlocks.CHISELED_SHIVERSTONE_BRICKS);
        crossBlock(UGBlocks.FROZEN_DEEPTURF);
        block(UGBlocks.CHISELED_TREMBLECRUST_BRICKS);
        block(UGBlocks.SEDIMENT);
        block(UGBlocks.SEDIMENT_GLASS);
        crossBlock(UGBlocks.HANGING_GRONGLE_LEAVES);
        crossBlock(UGBlocks.HANGING_GRONGLE_LEAVES_TOP);
        block(UGBlocks.CLOGGRUM_TILES);
        block(UGBlocks.DEPTHROCK_TILES);
        carpet(UGBlocks.MOGMOSS_RUG);
        block(UGBlocks.SHIVERSTONE_COAL_ORE);
        block(UGBlocks.SHIVERSTONE_IRON_ORE);
        block(UGBlocks.SHIVERSTONE_DIAMOND_ORE);
        block(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE);
        block(UGBlocks.SHIVERSTONE_UTHERIUM_ORE);
        block(UGBlocks.SHIVERSTONE_REGALIUM_ORE);
        block(UGBlocks.RAW_CLOGGRUM_BLOCK);
        block(UGBlocks.RAW_FROSTSTEEL_BLOCK);

        stairs(UGBlocks.DEPTHROCK_STAIRS, UGBlocks.DEPTHROCK);
        stairs(UGBlocks.DEPTHROCK_BRICK_STAIRS, UGBlocks.DEPTHROCK_BRICKS);
        stairs(UGBlocks.SMOGSTEM_STAIRS, UGBlocks.SMOGSTEM_PLANKS);
        stairs(UGBlocks.WIGGLEWOOD_STAIRS, UGBlocks.WIGGLEWOOD_PLANKS);
        stairs(UGBlocks.SHIVERSTONE_STAIRS, UGBlocks.SHIVERSTONE);
        stairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS, UGBlocks.SHIVERSTONE_BRICKS);
        stairs(UGBlocks.GRONGLE_STAIRS, UGBlocks.GRONGLE_PLANKS);
        stairs(UGBlocks.TREMBLECRUST_STAIRS, UGBlocks.TREMBLECRUST);
        stairs(UGBlocks.TREMBLECRUST_BRICK_STAIRS, UGBlocks.TREMBLECRUST_BRICKS);
        stairs(UGBlocks.CLOGGRUM_TILE_STAIRS, UGBlocks.CLOGGRUM_TILES);
        stairs(UGBlocks.DEPTHROCK_TILE_STAIRS, UGBlocks.DEPTHROCK_TILES);

        slab(UGBlocks.DEPTHROCK_SLAB, UGBlocks.DEPTHROCK);
        slab(UGBlocks.DEPTHROCK_BRICK_SLAB, UGBlocks.DEPTHROCK_BRICKS);
        slab(UGBlocks.SMOGSTEM_SLAB, UGBlocks.SMOGSTEM_PLANKS);
        slab(UGBlocks.WIGGLEWOOD_SLAB, UGBlocks.WIGGLEWOOD_PLANKS);
        slab(UGBlocks.SHIVERSTONE_SLAB, UGBlocks.SHIVERSTONE);
        slab(UGBlocks.SHIVERSTONE_BRICK_SLAB, UGBlocks.SHIVERSTONE_BRICKS);
        slab(UGBlocks.GRONGLE_SLAB, UGBlocks.GRONGLE_PLANKS);
        slab(UGBlocks.TREMBLECRUST_SLAB, UGBlocks.TREMBLECRUST);
        slab(UGBlocks.TREMBLECRUST_BRICK_SLAB, UGBlocks.TREMBLECRUST_BRICKS);
        slab(UGBlocks.CLOGGRUM_TILE_SLAB, UGBlocks.CLOGGRUM_TILES);
        slab(UGBlocks.DEPTHROCK_TILE_SLAB, UGBlocks.DEPTHROCK_TILES);

        wallBlock(UGBlocks.DEPTHROCK_WALL.get(), modLoc("block/depthrock"));
        wallBlock(UGBlocks.DEPTHROCK_BRICK_WALL.get(), modLoc("block/depthrock_bricks"));
        wallBlock(UGBlocks.SHIVERSTONE_WALL.get(), modLoc("block/shiverstone"));
        wallBlock(UGBlocks.SHIVERSTONE_BRICK_WALL.get(), modLoc("block/shiverstone_bricks"));
        wallBlock(UGBlocks.TREMBLECRUST_WALL.get(), modLoc("block/tremblecrust"));
        wallBlock(UGBlocks.TREMBLECRUST_BRICK_WALL.get(), modLoc("block/tremblecrust_bricks"));

        fence(UGBlocks.SMOGSTEM_FENCE, UGBlocks.SMOGSTEM_PLANKS);
        fence(UGBlocks.WIGGLEWOOD_FENCE, UGBlocks.WIGGLEWOOD_PLANKS);
        fence(UGBlocks.GRONGLE_FENCE, UGBlocks.GRONGLE_PLANKS);

        fenceGateBlock(UGBlocks.SMOGSTEM_FENCE_GATE.get(), modLoc("block/smogstem_planks"));
        fenceGateBlock(UGBlocks.WIGGLEWOOD_FENCE_GATE.get(), modLoc("block/wigglewood_planks"));
        fenceGateBlock(UGBlocks.GRONGLE_FENCE_GATE.get(), modLoc("block/grongle_planks"));

        door(UGBlocks.SMOGSTEM_DOOR, "smogstem");
        door(UGBlocks.WIGGLEWOOD_DOOR, "wigglewood");
        door(UGBlocks.GRONGLE_DOOR, "grongle");

        trapdoor(UGBlocks.SMOGSTEM_TRAPDOOR, "smogstem");
        trapdoor(UGBlocks.WIGGLEWOOD_TRAPDOOR, "wigglewood");
        trapdoor(UGBlocks.GRONGLE_TRAPDOOR, "grongle");
    }
}