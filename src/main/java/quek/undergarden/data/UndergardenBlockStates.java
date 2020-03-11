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
        normalBlock(UndergardenBlocks.depthrock);
        normalBlock(UndergardenBlocks.cobbled_depthrock);
        normalBlock(UndergardenBlocks.deepsoil);
        grassBlock(UndergardenBlocks.deepturf, "deepsoil");
        normalBlock(UndergardenBlocks.coal_ore);
        normalBlock(UndergardenBlocks.cloggrum_ore);
        normalBlock(UndergardenBlocks.froststeel_ore);
        normalBlock(UndergardenBlocks.utherium_ore);
        woodBlock(UndergardenBlocks.smogstem_log, "smogstem_log");
        woodBlock(UndergardenBlocks.wigglewood_log, "wigglewood_log");
        normalBlock(UndergardenBlocks.smogstem_planks);
        normalBlock(UndergardenBlocks.wigglewood_planks);

    }
}
