package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBiomes;
import quek.undergarden.registry.UGTags;

public class UGBiomeTags extends BiomeTagsProvider {

    public UGBiomeTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Undergarden.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(UGTags.Biomes.HAS_CATACOMBS).add(UGBiomes.ANCIENT_SEA);
        this.tag(UGTags.Biomes.HAS_FORGOTTEN_RUIN).add(UGBiomes.FORGOTTEN_FIELD, UGBiomes.DENSE_FOREST, UGBiomes.GRONGLEGROWTH, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST, UGBiomes.BARREN_ABYSS);
    }
}
