package quek.undergarden;

import com.mojang.logging.LogUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
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
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.client.event.UGClientRegistrationEvents;
import quek.undergarden.event.UndergardenCommonEvents;
import quek.undergarden.registry.*;
import quek.undergarden.registry.custom.UGHitEffects;

@Mod(Undergarden.MODID)
public class Undergarden {

	public static final String MODID = "undergarden";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Undergarden(IEventBus bus, Dist dist, ModContainer container) {
		NeoForgeMod.enableMilkFluid();
		if (dist.isClient()) {
			UGClientRegistrationEvents.init(bus);
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
			UGEntityTypes.ENTITY_TYPES,
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
			UGMenuTypes.MENU_TYPES,
			UGRecipeSerializers.RECIPE_SERIALIZERS,
			UGRecipeTypes.RECIPE_TYPES,
			UGDataComponents.COMPONENTS,
			UGRecipeDisplays.RECIPE_DISPLAYS,
			UGSlotDisplays.SLOTS,
			UGRecipeBookCategories.RECIPE_BOOK_CATEGORIES,
			UGConsumeEffects.CONSUME_EFFECTS,
			UGHitEffects.HIT_EFFECTS
		};

		for (DeferredRegister<?> register : registers) {
			register.register(bus);
		}

		container.registerConfig(ModConfig.Type.COMMON, UndergardenConfig.COMMON_SPEC);
		container.registerConfig(ModConfig.Type.CLIENT, UndergardenConfig.CLIENT_SPEC);
		container.registerConfig(ModConfig.Type.SERVER, UndergardenConfig.SERVER_SPEC);
	}

	public static Identifier prefix(String name) {
		return Identifier.fromNamespaceAndPath(MODID, name);
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