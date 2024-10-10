package quek.undergarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import quek.undergarden.datamap.BiomeLethality;
import quek.undergarden.registry.UGBiomes;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDataMaps;
import quek.undergarden.registry.UGItems;

import java.util.concurrent.CompletableFuture;

public class UGDataMapsProvider extends DataMapProvider {

	public UGDataMapsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void gather() {
		var compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);
		this.addCompostable(compostables, UGItems.DROOPFRUIT, 0.3F);
		this.addCompostable(compostables, UGItems.UNDERBEANS, 0.3F);
		this.addCompostable(compostables, UGItems.BLISTERBERRY, 0.3F);
		this.addCompostable(compostables, UGItems.ROTTEN_BLISTERBERRY, 0.3F);
		this.addCompostable(compostables, UGItems.GLOOMGOURD_SEEDS, 0.3F);
		this.addCompostable(compostables, UGItems.GLITTERKELP, 0.3F);
		this.addCompostable(compostables, UGBlocks.SMOGSTEM_LEAVES, 0.3F);
		this.addCompostable(compostables, UGBlocks.WIGGLEWOOD_LEAVES, 0.3F);
		this.addCompostable(compostables, UGBlocks.GRONGLE_LEAVES, 0.3F);
		this.addCompostable(compostables, UGBlocks.SMOGSTEM_SAPLING, 0.3F);
		this.addCompostable(compostables, UGBlocks.WIGGLEWOOD_SAPLING, 0.3F);
		this.addCompostable(compostables, UGBlocks.GRONGLE_SAPLING, 0.3F);
		this.addCompostable(compostables, UGBlocks.DEEPTURF, 0.3F);
		this.addCompostable(compostables, UGBlocks.SHIMMERWEED, 0.3F);
		this.addCompostable(compostables, UGItems.DITCHBULB, 0.3F);
		this.addCompostable(compostables, UGItems.DITCHBULB_PASTE, 0.3F);
		this.addCompostable(compostables, UGItems.BLOOD_GLOBULE, 0.5F);
		this.addCompostable(compostables, UGBlocks.TALL_DEEPTURF, 0.5F);
		this.addCompostable(compostables, UGBlocks.TALL_SHIMMERWEED, 0.5F);
		this.addCompostable(compostables, UGBlocks.HANGING_GRONGLE_LEAVES, 0.5F);
		this.addCompostable(compostables, UGBlocks.MUSHROOM_VEIL, 0.5F);
		this.addCompostable(compostables, UGBlocks.SEEPING_INK, 0.5F);
		this.addCompostable(compostables, UGBlocks.AMOROUS_BRISTLE, 0.65F);
		this.addCompostable(compostables, UGBlocks.BUTTERBUNCH, 0.65F);
		this.addCompostable(compostables, UGBlocks.MISERABELL, 0.65F);
		this.addCompostable(compostables, UGBlocks.INDIGO_MUSHROOM, 0.65F);
		this.addCompostable(compostables, UGBlocks.VEIL_MUSHROOM, 0.65F);
		this.addCompostable(compostables, UGBlocks.INK_MUSHROOM, 0.65F);
		this.addCompostable(compostables, UGBlocks.INDIGO_MUSHROOM, 0.65F);
		this.addCompostable(compostables, UGBlocks.GLOOMGOURD, 0.65F);
		this.addCompostable(compostables, UGBlocks.CARVED_GLOOMGOURD, 0.65F);
		this.addCompostable(compostables, UGBlocks.INDIGO_MUSHROOM_STEM, 0.65F);
		this.addCompostable(compostables, UGBlocks.VEIL_MUSHROOM_STEM, 0.65F);
		this.addCompostable(compostables, UGBlocks.BLOOD_MUSHROOM_STEM, 0.65F);
		this.addCompostable(compostables, UGBlocks.INDIGO_MUSHROOM_CAP, 0.85F);
		this.addCompostable(compostables, UGBlocks.VEIL_MUSHROOM_CAP, 0.85F);
		this.addCompostable(compostables, UGBlocks.INK_MUSHROOM_CAP, 0.85F);
		this.addCompostable(compostables, UGBlocks.BLOOD_MUSHROOM_CAP, 0.85F);
		this.addCompostable(compostables, UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP, 0.85F);
		this.addCompostable(compostables, UGItems.GLOOMGOURD_PIE, 1.0F);

		var fuels = this.builder(NeoForgeDataMaps.FURNACE_FUELS);
		fuels.add(UGItems.DITCHBULB_PASTE, new FurnaceFuel(800), false);

		var lethalities = this.builder(UGDataMaps.BIOME_LETHALITY);
		lethalities.add(UGBiomes.DEPTHS, new BiomeLethality(0.01F), false);
		lethalities.add(UGBiomes.INFECTED_DEPTHS, new BiomeLethality(0.025F), false);
		lethalities.add(UGBiomes.PUFF_MUSHROOM_FOREST, new BiomeLethality(0.01F), false);
	}

	private void addCompostable(DataMapProvider.Builder<Compostable, Item> compostableBuilder, ItemLike item, float chance) {
		compostableBuilder.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
	}
}
