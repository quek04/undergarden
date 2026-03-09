package quek.undergarden;

import com.mojang.logging.LogUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.event.UndergardenClientEvents;
import quek.undergarden.event.UndergardenCommonEvents;
import quek.undergarden.registry.*;

@Mod(Undergarden.MODID)
public class Undergarden {

	public static final String MODID = "undergarden";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Undergarden(IEventBus bus, Dist dist, ModContainer container) {
		NeoForgeMod.enableMilkFluid();
		if (dist.isClient()) {
			UndergardenClientEvents.initClientEvents(bus);
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}

		UndergardenCommonEvents.initCommonEvents(bus);

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
			UGRecipeTypes.RECIPE_TYPES,
			UGDataComponents.COMPONENTS,
			StonebornJobs.JOBS,
			UGEntityDataSerializers.DATA_SERIALIZERS
		};

		for (DeferredRegister<?> register : registers) {
			register.register(bus);
		}

		container.registerConfig(ModConfig.Type.COMMON, UndergardenConfig.COMMON_SPEC);
		container.registerConfig(ModConfig.Type.CLIENT, UndergardenConfig.CLIENT_SPEC);
	}

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name);
	}

	@Nullable
	public static RegistryAccess registryAccessStatic() {
		final MinecraftServer currentServer = ServerLifecycleHooks.getCurrentServer();
		if (currentServer != null)
			return currentServer.registryAccess();
		else
			return UndergardenClient.registryAccess();
	}
}