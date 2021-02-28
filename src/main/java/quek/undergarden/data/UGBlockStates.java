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
        block(UGBlocks.COAL_ORE);
        block(UGBlocks.CLOGGRUM_ORE);
        block(UGBlocks.FROSTSTEEL_ORE);
        block(UGBlocks.UTHERIUM_ORE);
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
        block(UGBlocks.REGALIUM_ORE);
        block(UGBlocks.REGALIUM_BLOCK);
        block(UGBlocks.TREMBLECRUST);
        block(UGBlocks.TREMBLECRUST_BRICKS);
        block(UGBlocks.CRACKED_TREMBLECRUST_BRICKS);
        block(UGBlocks.OTHERSIDE_UTHERIUM_ORE);
        block(UGBlocks.LOOSE_TREMBLECRUST);
        block(UGBlocks.IRON_ORE);
        block(UGBlocks.GOLD_ORE);
        block(UGBlocks.DIAMOND_ORE);
        crossBlock(UGBlocks.DROOPVINE_TOP);
        block(UGBlocks.COARSE_DEEPSOIL);
        crossBlock(UGBlocks.GRONGLET);
        block(UGBlocks.GRONGLE_CAP);
        log(UGBlocks.GRONGLE_STEM, "grongle_stem");
        block(UGBlocks.GRONGLE_PLANKS);
        log(UGBlocks.STRIPPED_SMOGSTEM_LOG, "stripped_smogstem_log");
        log(UGBlocks.STRIPPED_WIGGLEWOOD_LOG, "stripped_wigglewood_log");
        log(UGBlocks.STRIPPED_GRONGLE_STEM, "stripped_grongle_stem");
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

        stairs(UGBlocks.DEPTHROCK_STAIRS, "depthrock");
        stairs(UGBlocks.DEPTHROCK_BRICK_STAIRS, "depthrock_bricks");
        stairs(UGBlocks.SMOGSTEM_STAIRS, "smogstem_planks");
        stairs(UGBlocks.WIGGLEWOOD_STAIRS, "wigglewood_planks");
        stairs(UGBlocks.SHIVERSTONE_STAIRS, "shiverstone");
        stairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS, "shiverstone_bricks");
        stairs(UGBlocks.GRONGLE_STAIRS, "grongle_planks");

        slab(UGBlocks.DEPTHROCK_SLAB, UGBlocks.DEPTHROCK);
        slab(UGBlocks.DEPTHROCK_BRICK_SLAB, UGBlocks.DEPTHROCK_BRICKS);
        slab(UGBlocks.SMOGSTEM_SLAB, UGBlocks.SMOGSTEM_PLANKS);
        slab(UGBlocks.WIGGLEWOOD_SLAB, UGBlocks.WIGGLEWOOD_PLANKS);
        slab(UGBlocks.SHIVERSTONE_SLAB, UGBlocks.SHIVERSTONE);
        slab(UGBlocks.SHIVERSTONE_BRICK_SLAB, UGBlocks.SHIVERSTONE_BRICKS);
        slab(UGBlocks.GRONGLE_SLAB, UGBlocks.GRONGLE_PLANKS);

        wallBlock(UGBlocks.DEPTHROCK_WALL.get(), modLoc("block/depthrock"));
        wallBlock(UGBlocks.DEPTHROCK_BRICK_WALL.get(), modLoc("block/depthrock_bricks"));
        wallBlock(UGBlocks.SHIVERSTONE_WALL.get(), modLoc("block/shiverstone"));
        wallBlock(UGBlocks.SHIVERSTONE_BRICK_WALL.get(), modLoc("block/shiverstone_bricks"));

        fence(UGBlocks.SMOGSTEM_FENCE, "smogstem_planks");
        fence(UGBlocks.WIGGLEWOOD_FENCE, "wigglewood_planks");
        fence(UGBlocks.GRONGLE_FENCE, "grongle_planks");

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
