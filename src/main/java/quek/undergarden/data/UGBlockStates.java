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
        torchBlock(UGBlocks.smogstem_torch, UGBlocks.smogstem_wall_torch);
        torchBlock(UGBlocks.shard_torch, UGBlocks.shard_wall_torch);
        normalBlock(UGBlocks.depthrock);
        normalBlock(UGBlocks.deepsoil);
        crossBlock(UGBlocks.ashen_deepturf);
        crossBlock(UGBlocks.ditchbulb_plant);
        normalBlock(UGBlocks.coal_ore);
        normalBlock(UGBlocks.cloggrum_ore);
        normalBlock(UGBlocks.froststeel_ore);
        normalBlock(UGBlocks.utherium_ore);
        woodBlock(UGBlocks.smogstem_log, "smogstem_log");
        crossBlock(UGBlocks.smogstem_sapling);
        woodBlock(UGBlocks.wigglewood_log, "wigglewood_log");
        crossBlock(UGBlocks.wigglewood_sapling);
        normalBlock(UGBlocks.smogstem_planks);
        normalBlock(UGBlocks.wigglewood_planks);
        normalBlock(UGBlocks.smogstem_leaves);
        normalBlock(UGBlocks.wigglewood_leaves);
        crossBlock(UGBlocks.indigo_mushroom);
        crossBlock(UGBlocks.veil_mushroom);
        crossBlock(UGBlocks.ink_mushroom);
        crossBlock(UGBlocks.blood_mushroom);
        normalBlock(UGBlocks.depthrock_bricks);
        normalBlock(UGBlocks.cracked_depthrock_bricks);
        crossBlock(UGBlocks.glowing_kelp);
        crossBlock(UGBlocks.glowing_kelp_plant);
        normalBlock(UGBlocks.shiverstone);
        normalBlock(UGBlocks.shiverstone_bricks);
        normalBlock(UGBlocks.regalium_ore);
        normalBlock(UGBlocks.regalium_block);
        normalBlock(UGBlocks.tremblecrust);
        normalBlock(UGBlocks.tremblecrust_bricks);
        normalBlock(UGBlocks.otherside_utherium_ore);
        normalBlock(UGBlocks.loose_tremblecrust);
        normalBlock(UGBlocks.iron_ore);
        normalBlock(UGBlocks.gold_ore);
        normalBlock(UGBlocks.diamond_ore);
        crossBlock(UGBlocks.droopvine_top);
        normalBlock(UGBlocks.coarse_deepsoil);
        crossBlock(UGBlocks.gronglet);
        normalBlock(UGBlocks.grongle_cap);
        woodBlock(UGBlocks.grongle_stem, "grongle_stem");
        normalBlock(UGBlocks.grongle_planks);
        woodBlock(UGBlocks.stripped_smogstem_log, "stripped_smogstem_log");
        woodBlock(UGBlocks.stripped_wigglewood_log, "stripped_wigglewood_log");
        woodBlock(UGBlocks.stripped_grongle_stem, "stripped_grongle_stem");
        normalBlock(UGBlocks.cracked_shiverstone_bricks);

        normalBlock(UGBlocks.cloggrum_block);
        normalBlock(UGBlocks.froststeel_block);
        normalBlock(UGBlocks.utherium_block);

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

        wallBlock(UGBlocks.depthrock_wall.get(), new ResourceLocation(UGMod.MODID, "block/depthrock"));
        wallBlock(UGBlocks.depthrock_brick_wall.get(), new ResourceLocation(UGMod.MODID, "block/depthrock_bricks"));
        wallBlock(UGBlocks.shiverstone_wall.get(), new ResourceLocation(UGMod.MODID, "block/shiverstone"));
        wallBlock(UGBlocks.shiverstone_brick_wall.get(), new ResourceLocation(UGMod.MODID, "block/shiverstone_bricks"));

        fence(UGBlocks.smogstem_fence, "smogstem_planks");
        fence(UGBlocks.wigglewood_fence, "wigglewood_planks");
        fence(UGBlocks.grongle_fence, "grongle_planks");

        fenceGateBlock(UGBlocks.smogstem_fence_gate.get(), new ResourceLocation(UGMod.MODID, "block/smogstem_planks"));
        fenceGateBlock(UGBlocks.wigglewood_fence_gate.get(), new ResourceLocation(UGMod.MODID, "block/wigglewood_planks"));
        fenceGateBlock(UGBlocks.grongle_fence_gate.get(), new ResourceLocation(UGMod.MODID, "block/grongle_planks"));

        door(UGBlocks.smogstem_door, "smogstem");
        door(UGBlocks.wigglewood_door, "wigglewood");
        door(UGBlocks.grongle_door, "grongle");

        trapdoor(UGBlocks.smogstem_trapdoor, "smogstem");
        trapdoor(UGBlocks.wigglewood_trapdoor, "wigglewood");
        trapdoor(UGBlocks.grongle_trapdoor, "grongle");
    }
}
