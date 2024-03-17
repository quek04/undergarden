package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBiomes;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGBiomeTags extends BiomeTagsProvider {

	public UGBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, future, Undergarden.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//undergarden
		tag(UGTags.Biomes.IS_UNDERGARDEN).add(UGBiomes.ANCIENT_SEA, UGBiomes.BARREN_ABYSS, UGBiomes.BLOOD_MUSHROOM_BOG, UGBiomes.DEAD_SEA, UGBiomes.DENSE_FOREST, UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.GRONGLEGROWTH, UGBiomes.ICY_SEA, UGBiomes.INDIGO_MUSHROOM_BOG, UGBiomes.INK_MUSHROOM_BOG, UGBiomes.SMOGSTEM_FOREST, UGBiomes.SMOG_SPIRES, UGBiomes.VEIL_MUSHROOM_BOG, UGBiomes.WIGGLEWOOD_FOREST);
		tag(UGTags.Biomes.HAS_CATACOMBS).add(UGBiomes.ANCIENT_SEA, UGBiomes.DENSE_FOREST, UGBiomes.FORGOTTEN_FIELD, UGBiomes.GRONGLEGROWTH, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST);
		tag(UGTags.Biomes.HAS_FORGOTTEN_VESTIGE).add(UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.DENSE_FOREST, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST, UGBiomes.GRONGLEGROWTH, UGBiomes.BARREN_ABYSS);

		//vanilla
		tag(BiomeTags.WITHOUT_ZOMBIE_SIEGES).addTag(UGTags.Biomes.IS_UNDERGARDEN);
		tag(BiomeTags.WITHOUT_PATROL_SPAWNS).addTag(UGTags.Biomes.IS_UNDERGARDEN);
		tag(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS).addTag(UGTags.Biomes.IS_UNDERGARDEN);
        /*tag(BiomeTags.IS_FOREST).add(UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST, UGBiomes.DENSE_FOREST, UGBiomes.GRONGLEGROWTH);

        //forge
        tag(Tags.Biomes.IS_COLD).add(UGBiomes.FROSTFIELDS, UGBiomes.ICY_SEA);
        tag(Tags.Biomes.IS_SPARSE).add(UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.BARREN_ABYSS);
        tag(Tags.Biomes.IS_DENSE).add(UGBiomes.DENSE_FOREST, UGBiomes.GRONGLEGROWTH);
        tag(Tags.Biomes.IS_DRY).add(UGBiomes.BARREN_ABYSS, UGBiomes.SMOG_SPIRES);
        tag(Tags.Biomes.IS_DEAD).add(UGBiomes.BARREN_ABYSS, UGBiomes.SMOG_SPIRES, UGBiomes.DEAD_SEA);
        tag(Tags.Biomes.IS_WATER).add(UGBiomes.ANCIENT_SEA, UGBiomes.ICY_SEA, UGBiomes.DEAD_SEA);
        tag(Tags.Biomes.IS_PLAINS).add(UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS);
        tag(Tags.Biomes.IS_SWAMP).add(UGBiomes.BLOOD_MUSHROOM_BOG, UGBiomes.INDIGO_MUSHROOM_BOG, UGBiomes.INK_MUSHROOM_BOG, UGBiomes.VEIL_MUSHROOM_BOG);
        tag(Tags.Biomes.IS_WASTELAND).add(UGBiomes.BARREN_ABYSS, UGBiomes.DEAD_SEA);*/
	}

	@Override
	public String getName() {
		return "Undergarden Biome Tags";
	}
}
