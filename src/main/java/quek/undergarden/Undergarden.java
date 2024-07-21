package quek.undergarden;

import com.mojang.logging.LogUtils;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.data.*;
import quek.undergarden.event.UndergardenClientEvents;
import quek.undergarden.event.UndergardenCommonEvents;
import quek.undergarden.network.CreateCritParticlePacket;
import quek.undergarden.network.UndergardenPortalSoundPacket;
import quek.undergarden.network.UthericInfectionPacket;
import quek.undergarden.registry.*;
import quek.undergarden.world.gen.UGNoiseBasedChunkGenerator;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Mod(Undergarden.MODID)
public class Undergarden {

	public static final String MODID = "undergarden";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Undergarden(IEventBus bus, Dist dist, ModContainer container) {

		if (dist.isClient()) {
			UndergardenClientEvents.initClientEvents(bus);
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}

		UndergardenCommonEvents.initCommonEvents(bus);
		bus.addListener(this::gatherData);
		bus.addListener(this::registerPackets);
		bus.addListener((Consumer<RegisterEvent>) event -> {
			if (event.getRegistry() == BuiltInRegistries.CHUNK_GENERATOR) {
				Registry.register(BuiltInRegistries.CHUNK_GENERATOR, ResourceLocation.fromNamespaceAndPath(MODID, "noise"), UGNoiseBasedChunkGenerator.CODEC);
			}
		});

		DeferredRegister<?>[] registers = {
			UGAttachments.ATTACHMENTS,
			UGBlockEntities.BLOCK_ENTITIES,
			UGBlocks.BLOCKS,
			UGCarvers.CARVERS,
			UGCreativeModeTabs.TABS,
			UGCriteria.CRITERIA,
			UGEffects.EFFECTS,
			UGEntityTypes.ENTITIES,
			UGFeatures.FEATURES,
			UGFluids.FLUIDS,
			UGFluids.TYPES,
			UGFoliagePlacers.FOLIAGE_PLACERS,
			UGItems.ITEMS,
			UGParticleTypes.PARTICLES,
			UGPointOfInterests.POI,
			UGPotions.POTIONS,
			UGSoundEvents.SOUNDS,
			UGStructures.STRUCTURES,
			UGTreeDecoratorTypes.TREE_DECORATORS,
			UGTrunkPlacerTypes.TRUNK_PLACERS,
			UGArmorMaterials.ARMOR_MATERIALS,
			UGMenuTypes.MENU_TYPES,
			UGRecipeSerializers.RECIPE_SERIALIZERS,
			UGRecipeTypes.RECIPE_TYPES
		};

		for (DeferredRegister<?> register : registers) {
			register.register(bus);
		}

		container.registerConfig(ModConfig.Type.COMMON, UndergardenConfig.COMMON_SPEC);
		container.registerConfig(ModConfig.Type.CLIENT, UndergardenConfig.CLIENT_SPEC);
	}

	public void registerPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
		registrar.playToClient(CreateCritParticlePacket.TYPE, CreateCritParticlePacket.STREAM_CODEC, CreateCritParticlePacket::handle);
		registrar.playToClient(UndergardenPortalSoundPacket.TYPE, UndergardenPortalSoundPacket.STREAM_CODEC, UndergardenPortalSoundPacket::handle);
		registrar.playToClient(UthericInfectionPacket.TYPE, UthericInfectionPacket.STREAM_CODEC, UthericInfectionPacket::handle);
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeClient(), new UGBlockStates(output, helper));
		generator.addProvider(event.includeClient(), new UGItemModels(output, helper));
		generator.addProvider(event.includeClient(), new UGLang(output));
		generator.addProvider(event.includeClient(), new UGSoundDefinitions(output, helper));

		DatapackBuiltinEntriesProvider datapackProvider = new UGRegistries(output, provider);
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
		generator.addProvider(event.includeServer(), datapackProvider);
		generator.addProvider(event.includeServer(), new UGRecipes(output, provider));
		generator.addProvider(event.includeServer(), new UGLootTables(output, provider));
		UGBlockTags blockTags = new UGBlockTags(output, provider, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new UGItemTags(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new UGEntityTags(output, provider, helper));
		generator.addProvider(event.includeServer(), new UGAdvancements(output, datapackProvider.getRegistryProvider(), helper));
		generator.addProvider(event.includeServer(), new UGFluidTags(output, provider, helper));
		generator.addProvider(event.includeServer(), new UGBiomeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGDamageTypeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UGStructureUpdater("structures", output, helper));
		generator.addProvider(event.includeServer(), new UGDataMaps(output, lookupProvider));
		generator.addProvider(event.includeClient(), new UGEnchantmentTags(output, datapackProvider.getRegistryProvider(), helper));

		generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
				Component.literal("Undergarden resources"),
				DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
				Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));

	}

	public static RegistryAccess registryAccessStatic() {
		final MinecraftServer currentServer = ServerLifecycleHooks.getCurrentServer();
		if(currentServer != null)
			return currentServer.registryAccess();
		else
			return UndergardenClient.registryAccess();
	}
}