package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import quek.undergarden.UndergardenMod;
import quek.undergarden.data.provider.UndergardenBlockStateProvider;
import quek.undergarden.registry.UndergardenBlocks;

public class UndergardenBlockStates extends UndergardenBlockStateProvider {

    public UndergardenBlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Block States";
    }

    @Override
    protected void registerStatesAndModels() {
        torchBlock(UndergardenBlocks.smogstem_torch, UndergardenBlocks.smogstem_wall_torch);
        torchBlock(UndergardenBlocks.shard_torch, UndergardenBlocks.shard_wall_torch);
        normalBlock(UndergardenBlocks.depthrock);
        normalBlock(UndergardenBlocks.deepsoil);
        crossBlock(UndergardenBlocks.ashen_tall_deepturf);
        crossBlock(UndergardenBlocks.ditchbulb_plant);
        normalBlock(UndergardenBlocks.coal_ore);
        normalBlock(UndergardenBlocks.cloggrum_ore);
        normalBlock(UndergardenBlocks.froststeel_ore);
        normalBlock(UndergardenBlocks.utherium_ore);
        woodBlock(UndergardenBlocks.smogstem_log, "smogstem_log");
        crossBlock(UndergardenBlocks.smogstem_sapling);
        woodBlock(UndergardenBlocks.wigglewood_log, "wigglewood_log");
        crossBlock(UndergardenBlocks.wigglewood_sapling);
        normalBlock(UndergardenBlocks.smogstem_planks);
        normalBlock(UndergardenBlocks.wigglewood_planks);
        normalBlock(UndergardenBlocks.smogstem_leaves);
        normalBlock(UndergardenBlocks.wigglewood_leaves);
        crossBlock(UndergardenBlocks.indigo_mushroom);
        crossBlock(UndergardenBlocks.veil_mushroom);
        crossBlock(UndergardenBlocks.ink_mushroom);
        crossBlock(UndergardenBlocks.blood_mushroom);
        normalBlock(UndergardenBlocks.depthrock_bricks);
        normalBlock(UndergardenBlocks.cracked_depthrock_bricks);
        crossBlock(UndergardenBlocks.glowing_kelp);
        crossBlock(UndergardenBlocks.glowing_kelp_plant);
        normalBlock(UndergardenBlocks.shiverstone);
        normalBlock(UndergardenBlocks.shiverstone_bricks);
        normalBlock(UndergardenBlocks.regalium_ore);
        normalBlock(UndergardenBlocks.regalium_block);
        normalBlock(UndergardenBlocks.tremblecrust);
        normalBlock(UndergardenBlocks.tremblecrust_bricks);
        normalBlock(UndergardenBlocks.otherside_utherium_ore);
        normalBlock(UndergardenBlocks.loose_tremblecrust);
        normalBlock(UndergardenBlocks.iron_ore);
        normalBlock(UndergardenBlocks.gold_ore);
        normalBlock(UndergardenBlocks.diamond_ore);
        crossBlock(UndergardenBlocks.droopvine_top);

        normalBlock(UndergardenBlocks.cloggrum_block);
        normalBlock(UndergardenBlocks.froststeel_block);
        normalBlock(UndergardenBlocks.utherium_block);

        stairs(UndergardenBlocks.depthrock_stairs, "depthrock");
        stairs(UndergardenBlocks.depthrock_brick_stairs, "depthrock_bricks");
        stairs(UndergardenBlocks.smogstem_stairs, "smogstem_planks");
        stairs(UndergardenBlocks.wigglewood_stairs, "wigglewood_planks");
        stairs(UndergardenBlocks.shiverstone_stairs, "shiverstone");
        stairs(UndergardenBlocks.shiverstone_brick_stairs, "shiverstone_bricks");

        slab(UndergardenBlocks.depthrock_slab, UndergardenBlocks.depthrock);
        slab(UndergardenBlocks.depthrock_brick_slab, UndergardenBlocks.depthrock_bricks);
        slab(UndergardenBlocks.smogstem_slab, UndergardenBlocks.smogstem_planks);
        slab(UndergardenBlocks.wigglewood_slab, UndergardenBlocks.wigglewood_planks);
        slab(UndergardenBlocks.shiverstone_slab, UndergardenBlocks.shiverstone);
        slab(UndergardenBlocks.shiverstone_brick_slab, UndergardenBlocks.shiverstone_bricks);

        wallBlock(UndergardenBlocks.depthrock_brick_wall.get(), new ResourceLocation(UndergardenMod.MODID, "block/depthrock_bricks"));
        wallBlock(UndergardenBlocks.shiverstone_brick_wall.get(), new ResourceLocation(UndergardenMod.MODID, "block/shiverstone_bricks"));

        fence(UndergardenBlocks.smogstem_fence, "smogstem_planks");
        fence(UndergardenBlocks.wigglewood_fence, "wigglewood_planks");

        fenceGateBlock(UndergardenBlocks.smogstem_fence_gate.get(), new ResourceLocation(UndergardenMod.MODID, "block/smogstem_planks"));
        fenceGateBlock(UndergardenBlocks.wigglewood_fence_gate.get(), new ResourceLocation(UndergardenMod.MODID, "block/wigglewood_planks"));

        door(UndergardenBlocks.smogstem_door, "smogstem");
        door(UndergardenBlocks.wigglewood_door, "wigglewood");

        trapdoor(UndergardenBlocks.smogstem_trapdoor, "smogstem");
        trapdoor(UndergardenBlocks.wigglewood_trapdoor, "wigglewood");
    }
}
