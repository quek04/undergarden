package quek.undergarden.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import quek.undergarden.Undergarden;
import quek.undergarden.datagen.assets.UGBlockStates;
import quek.undergarden.datagen.assets.UGItemModels;
import quek.undergarden.datagen.assets.UGLang;
import quek.undergarden.datagen.assets.UGSoundDefinitions;
import quek.undergarden.datagen.data.*;
import quek.undergarden.datagen.data.loot.UGLootTables;
import quek.undergarden.datagen.data.tags.*;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Undergarden.MODID)
public class UGDataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeClient(), new UGBlockStates(output, helper));
		generator.addProvider(event.includeClient(), new UGItemModels(output, helper));
		generator.addProvider(event.includeClient(), new UGLang(output));
		generator.addProvider(event.includeClient(), new UGSoundDefinitions(output, helper));

		DatapackBuiltinEntriesProvider datapackProvider = new UGRegistries(output, event.getLookupProvider());
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
		generator.addProvider(event.includeServer(), datapackProvider);

		generator.addProvider(event.includeServer(), new UGRecipes(output, lookupProvider));
		generator.addProvider(event.includeServer(), new UGStructureUpdater("structures", output, helper));
		generator.addProvider(event.includeServer(), new UGAdvancements(output, datapackProvider.getRegistryProvider(), helper));
		generator.addProvider(event.includeServer(), new UGDataMapsProvider(output, lookupProvider));

		generator.addProvider(event.includeServer(), new UGLootTables(output, lookupProvider));

		UGBlockTags blockTags = new UGBlockTags(output, lookupProvider, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new UGItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new UGEntityTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGFluidTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGBiomeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGDamageTypeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeClient(), new UGEnchantmentTags(output, lookupProvider, helper));
	}
}
