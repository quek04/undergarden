package quek.undergarden.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class UGAdvancements extends AdvancementProvider {

    public UGAdvancements(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        new UndergardenAdvancements().accept(consumer);
        //TODO: new OthersideAdvancements
    }

    @Override
    public String getName() {
        return "Undergarden Advancements";
    }
}