package quek.undergarden.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import quek.undergarden.Undergarden;
import quek.undergarden.datagen.assets.*;
import quek.undergarden.datagen.data.*;
import quek.undergarden.datagen.data.loot.UGLootTables;
import quek.undergarden.datagen.data.tags.*;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Undergarden.MODID)
public class UGDataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		generator.addProvider(true, new UGEquipmentAssetProvider(output));
		generator.addProvider(true, new UGLang(output));
		generator.addProvider(true, new UGModels(output));
		generator.addProvider(true, new UGParticleDescriptions(output));
		generator.addProvider(true, new UGSoundDefinitions(output));

		DatapackBuiltinEntriesProvider datapackProvider = new UGRegistryProvider(output, event.getLookupProvider());
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
		generator.addProvider(true, datapackProvider);

		generator.addProvider(true, new UGRecipeRunner(output, lookupProvider));
		generator.addProvider(true, new UGStructureUpdater("structure", output, event.getResourceManager(PackType.SERVER_DATA)));
		generator.addProvider(true, new UGAdvancements(output, datapackProvider.getRegistryProvider()));
		generator.addProvider(true, new UGDataMapsProvider(output, lookupProvider));

		generator.addProvider(true, new UGLootTables(output, lookupProvider));

		generator.addProvider(true, new UGBlockTags(output, lookupProvider));
		generator.addProvider(true, new UGItemTags(output, lookupProvider));
		generator.addProvider(true, new UGEntityTags(output, lookupProvider));
		generator.addProvider(true, new UGFluidTags(output, lookupProvider));
		generator.addProvider(true, new UGBiomeTags(output, lookupProvider));
		generator.addProvider(true, new UGDamageTypeTags(output, lookupProvider));
		generator.addProvider(true, new UGEnchantmentTags(output, lookupProvider));
		generator.addProvider(true, new UGStonebornTradeTags(output, lookupProvider));
		generator.addProvider(true, new UGTimelineTags(output, lookupProvider));
	}
}
