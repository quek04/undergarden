package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
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
        normalBlock(UndergardenBlocks.depthrock);
        normalBlock(UndergardenBlocks.cobbled_depthrock);
        normalBlock(UndergardenBlocks.deepsoil);
        crossBlock(UndergardenBlocks.tall_deepturf);
        crossBlock(UndergardenBlocks.ashen_tall_deepturf);
        crossBlock(UndergardenBlocks.shimmerweed);
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

        normalBlock(UndergardenBlocks.cloggrum_block);
        normalBlock(UndergardenBlocks.froststeel_block);
        normalBlock(UndergardenBlocks.utherium_block);

        stairs(UndergardenBlocks.depthrock_stairs, "depthrock");
        stairs(UndergardenBlocks.cobbled_depthrock_stairs, "cobbled_depthrock");
        stairs(UndergardenBlocks.depthrock_brick_stairs, "depthrock_bricks");
        stairs(UndergardenBlocks.smogstem_stairs, "smogstem_planks");
        stairs(UndergardenBlocks.wigglewood_stairs, "wigglewood_planks");

        slab(UndergardenBlocks.depthrock_slab, UndergardenBlocks.depthrock);
        slab(UndergardenBlocks.cobbled_depthrock_slab, UndergardenBlocks.cobbled_depthrock);
        slab(UndergardenBlocks.depthrock_brick_slab, UndergardenBlocks.depthrock_bricks);
        slab(UndergardenBlocks.smogstem_slab, UndergardenBlocks.smogstem_planks);
        slab(UndergardenBlocks.wigglewood_slab, UndergardenBlocks.wigglewood_planks);

        wall(UndergardenBlocks.cobbled_depthrock_wall, "cobbled_depthrock");
        wallColumn(UndergardenBlocks.cobbled_depthrock_wall, "cobbled_depthrock");
        wall(UndergardenBlocks.depthrock_brick_wall, "depthrock_bricks");
        wallColumn(UndergardenBlocks.depthrock_brick_wall, "depthrock_bricks");

        fence(UndergardenBlocks.smogstem_fence, "smogstem_planks");
        fenceColumn(UndergardenBlocks.smogstem_fence, "smogstem_planks");
        fence(UndergardenBlocks.wigglewood_fence, "wigglewood_planks");
        fenceColumn(UndergardenBlocks.wigglewood_fence, "wigglewood_planks");

        door(UndergardenBlocks.smogstem_door, "smogstem");
        door(UndergardenBlocks.wigglewood_door, "wigglewood");

        trapdoor(UndergardenBlocks.smogstem_trapdoor, "smogstem");
        trapdoor(UndergardenBlocks.wigglewood_trapdoor, "wigglewood");
    }
}
