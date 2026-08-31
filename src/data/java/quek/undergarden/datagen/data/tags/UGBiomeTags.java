package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBiomes;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGBiomeTags extends BiomeTagsProvider {

	public UGBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, Undergarden.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//undergarden
		tag(UGTags.Biomes.IS_UNDERGARDEN).add(UGBiomes.ANCIENT_SEA, UGBiomes.BARREN_ABYSS, UGBiomes.BLOOD_MUSHROOM_BOG, UGBiomes.DEAD_SEA, UGBiomes.DENSE_FOREST, UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.GRONGLEGROWTH, UGBiomes.ICY_SEA, UGBiomes.INDIGO_MUSHROOM_BOG, UGBiomes.INK_MUSHROOM_BOG, UGBiomes.SMOGSTEM_FOREST, UGBiomes.SMOG_SPIRES, UGBiomes.VEIL_MUSHROOM_BOG, UGBiomes.WIGGLEWOOD_FOREST).addTag(UGTags.Biomes.IS_DEPTHS_BIOME);
		tag(UGTags.Biomes.IS_OTHERSIDE).add(UGBiomes.FIELDS_OF_SORROW, UGBiomes.MOUNTAINS_OF_MADNESS);
		tag(UGTags.Biomes.IS_DEPTHS_BIOME).add(UGBiomes.DEPTHS, UGBiomes.INFECTED_DEPTHS, UGBiomes.PUFF_MUSHROOM_FOREST, UGBiomes.ROGDORIUM_GROVE);
		tag(UGTags.Biomes.HAS_CATACOMBS).add(UGBiomes.ANCIENT_SEA, UGBiomes.DENSE_FOREST, UGBiomes.FORGOTTEN_FIELD, UGBiomes.GRONGLEGROWTH, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST);
		tag(UGTags.Biomes.HAS_FORGOTTEN_VESTIGE).add(UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.DENSE_FOREST, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST, UGBiomes.GRONGLEGROWTH, UGBiomes.BARREN_ABYSS);
		tag(UGTags.Biomes.HAS_DENIZEN_CAMP).add(UGBiomes.DEPTHS);
		tag(UGTags.Biomes.HAS_DEPLETED_MINE).addTag(UGTags.Biomes.IS_DEPTHS_BIOME);

		//undergarden common
		tag(UGTags.Biomes.PRIMARY_WOOD_TYPE_WIGGLEWOOD).add(UGBiomes.WIGGLEWOOD_FOREST, UGBiomes.DENSE_FOREST);
		tag(UGTags.Biomes.PRIMARY_WOOD_TYPE_SMOGSTEM).add(UGBiomes.SMOGSTEM_FOREST, UGBiomes.DENSE_FOREST, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.INDIGO_MUSHROOM_BOG, UGBiomes.ROGDORIUM_GROVE, UGBiomes.FORGOTTEN_FIELD);
		tag(UGTags.Biomes.PRIMARY_WOOD_TYPE_GRONGLE).add(UGBiomes.GRONGLEGROWTH);
		tag(UGTags.Biomes.PRIMARY_WOOD_TYPE_WHISPERWOOD).add(UGBiomes.FIELDS_OF_SORROW);

		//vanilla
		tag(BiomeTags.WATER_ON_MAP_OUTLINES).add(UGBiomes.ANCIENT_SEA, UGBiomes.DEAD_SEA, UGBiomes.ICY_SEA);
		tag(BiomeTags.WITHOUT_ZOMBIE_SIEGES).addTag(UGTags.Biomes.IS_UNDERGARDEN).addTag(UGTags.Biomes.IS_OTHERSIDE);
		tag(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS).addTag(UGTags.Biomes.IS_UNDERGARDEN).addTag(UGTags.Biomes.IS_OTHERSIDE);
		tag(BiomeTags.SPAWNS_COLD_VARIANT_FROGS).add(UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.ICY_SEA, UGBiomes.BARREN_ABYSS);
		tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(UGBiomes.GRONGLEGROWTH, UGBiomes.SMOG_SPIRES).addTag(UGTags.Biomes.IS_DEPTHS_BIOME);
		tag(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS).add(UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.ICY_SEA, UGBiomes.BARREN_ABYSS);
		tag(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS).add(UGBiomes.GRONGLEGROWTH, UGBiomes.SMOG_SPIRES).addTag(UGTags.Biomes.IS_DEPTHS_BIOME);
		tag(BiomeTags.SPAWNS_WHITE_RABBITS).add(UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.ICY_SEA);
		tag(BiomeTags.POLAR_BEARS_SPAWN_ON_ALTERNATE_BLOCKS).add(UGBiomes.ICY_SEA);
		tag(BiomeTags.SPAWNS_SNOW_FOXES).add(UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.ICY_SEA);

		//neoforge
		tag(Tags.Biomes.PRIMARY_WOOD_TYPE).addTag(UGTags.Biomes.PRIMARY_WOOD_TYPE_WIGGLEWOOD).addTag(UGTags.Biomes.PRIMARY_WOOD_TYPE_SMOGSTEM).addTag(UGTags.Biomes.PRIMARY_WOOD_TYPE_GRONGLE).addTag(UGTags.Biomes.PRIMARY_WOOD_TYPE_WHISPERWOOD);
	}
}