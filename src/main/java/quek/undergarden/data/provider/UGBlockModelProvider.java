package quek.undergarden.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;

public abstract class UGBlockModelProvider extends BlockModelProvider {

    public UGBlockModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Undergarden.MODID, helper);
    }
}