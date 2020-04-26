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
        grassBlock(UndergardenBlocks.deepturf_block, "deepsoil");
        crossBlock(UndergardenBlocks.tall_deepturf);
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
        normalBlock(UndergardenBlocks.undergarden_portal);
    }
}
