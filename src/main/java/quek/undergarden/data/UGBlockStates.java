package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.UGMod;
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
        torchBlock(UGBlocks.shard_torch, UGBlocks.shard_wall_torch);
        block(UGBlocks.depthrock);
        block(UGBlocks.deepsoil);
        crossBlock(UGBlocks.ashen_deepturf);
        crossBlock(UGBlocks.ditchbulb_plant);
        block(UGBlocks.coal_ore);
        block(UGBlocks.cloggrum_ore);
        block(UGBlocks.froststeel_ore);
        block(UGBlocks.utherium_ore);
        log(UGBlocks.smogstem_log, "smogstem_log");
        crossBlock(UGBlocks.smogstem_sapling);
        log(UGBlocks.wigglewood_log, "wigglewood_log");
        crossBlock(UGBlocks.wigglewood_sapling);
        block(UGBlocks.smogstem_planks);
        block(UGBlocks.wigglewood_planks);
        block(UGBlocks.smogstem_leaves);
        block(UGBlocks.wigglewood_leaves);
        crossBlock(UGBlocks.indigo_mushroom);
        crossBlock(UGBlocks.veil_mushroom);
        crossBlock(UGBlocks.ink_mushroom);
        crossBlock(UGBlocks.blood_mushroom);
        block(UGBlocks.depthrock_bricks);
        block(UGBlocks.cracked_depthrock_bricks);
        crossBlock(UGBlocks.glowing_kelp);
        crossBlock(UGBlocks.glowing_kelp_plant);
        block(UGBlocks.shiverstone);
        block(UGBlocks.shiverstone_bricks);
        block(UGBlocks.regalium_ore);
        block(UGBlocks.regalium_block);
        block(UGBlocks.tremblecrust);
        block(UGBlocks.tremblecrust_bricks);
        block(UGBlocks.otherside_utherium_ore);
        block(UGBlocks.loose_tremblecrust);
        block(UGBlocks.iron_ore);
        block(UGBlocks.gold_ore);
        block(UGBlocks.diamond_ore);
        crossBlock(UGBlocks.droopvine_top);
        block(UGBlocks.coarse_deepsoil);
        crossBlock(UGBlocks.gronglet);
        block(UGBlocks.grongle_cap);
        log(UGBlocks.grongle_stem, "grongle_stem");
        block(UGBlocks.grongle_planks);
        log(UGBlocks.stripped_smogstem_log, "stripped_smogstem_log");
        log(UGBlocks.stripped_wigglewood_log, "stripped_wigglewood_log");
        log(UGBlocks.stripped_grongle_stem, "stripped_grongle_stem");
        block(UGBlocks.cracked_shiverstone_bricks);
        block(UGBlocks.blood_mushroom_globule);
        crossBlock(UGBlocks.seeping_ink);
        crossBlock(UGBlocks.mushroom_veil);
        crossBlock(UGBlocks.mushroom_veil_top);
        block(UGBlocks.forgotten_block);
        block(UGBlocks.cloggrum_block);
        block(UGBlocks.froststeel_block);
        block(UGBlocks.utherium_block);
        block(UGBlocks.chiseled_depthrock_bricks);
        block(UGBlocks.chiseled_shiverstone_bricks);

        stairs(UGBlocks.depthrock_stairs, "depthrock");
        stairs(UGBlocks.depthrock_brick_stairs, "depthrock_bricks");
        stairs(UGBlocks.smogstem_stairs, "smogstem_planks");
        stairs(UGBlocks.wigglewood_stairs, "wigglewood_planks");
        stairs(UGBlocks.shiverstone_stairs, "shiverstone");
        stairs(UGBlocks.shiverstone_brick_stairs, "shiverstone_bricks");
        stairs(UGBlocks.grongle_stairs, "grongle_planks");

        slab(UGBlocks.depthrock_slab, UGBlocks.depthrock);
        slab(UGBlocks.depthrock_brick_slab, UGBlocks.depthrock_bricks);
        slab(UGBlocks.smogstem_slab, UGBlocks.smogstem_planks);
        slab(UGBlocks.wigglewood_slab, UGBlocks.wigglewood_planks);
        slab(UGBlocks.shiverstone_slab, UGBlocks.shiverstone);
        slab(UGBlocks.shiverstone_brick_slab, UGBlocks.shiverstone_bricks);
        slab(UGBlocks.grongle_slab, UGBlocks.grongle_planks);

        wallBlock(UGBlocks.depthrock_wall.get(), modLoc("block/depthrock"));
        wallBlock(UGBlocks.depthrock_brick_wall.get(), modLoc("block/depthrock_bricks"));
        wallBlock(UGBlocks.shiverstone_wall.get(), modLoc("block/shiverstone"));
        wallBlock(UGBlocks.shiverstone_brick_wall.get(), modLoc("block/shiverstone_bricks"));

        fence(UGBlocks.smogstem_fence, "smogstem_planks");
        fence(UGBlocks.wigglewood_fence, "wigglewood_planks");
        fence(UGBlocks.grongle_fence, "grongle_planks");

        fenceGateBlock(UGBlocks.smogstem_fence_gate.get(), modLoc("block/smogstem_planks"));
        fenceGateBlock(UGBlocks.wigglewood_fence_gate.get(), modLoc("block/wigglewood_planks"));
        fenceGateBlock(UGBlocks.grongle_fence_gate.get(), modLoc("block/grongle_planks"));

        door(UGBlocks.smogstem_door, "smogstem");
        door(UGBlocks.wigglewood_door, "wigglewood");
        door(UGBlocks.grongle_door, "grongle");

        trapdoor(UGBlocks.smogstem_trapdoor, "smogstem");
        trapdoor(UGBlocks.wigglewood_trapdoor, "wigglewood");
        trapdoor(UGBlocks.grongle_trapdoor, "grongle");
    }
}
