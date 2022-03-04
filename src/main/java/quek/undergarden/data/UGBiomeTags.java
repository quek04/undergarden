package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import quek.undergarden.registry.UGBiomes;
import quek.undergarden.registry.UGTags;

public class UGBiomeTags extends BiomeTagsProvider {

    public UGBiomeTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public String getName() {
        return "Undergarden Biome Tags";
    }

    @Override
    protected void addTags() {
        tag(UGTags.Biomes.HAS_CATACOMBS).add(UGBiomes.ANCIENT_SEA);
    }
}
