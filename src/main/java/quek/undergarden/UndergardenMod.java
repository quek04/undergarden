package quek.undergarden;

import net.minecraft.data.DataGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import org.apache.commons.lang3.tuple.Pair;
import quek.undergarden.client.ClientStuff;
import quek.undergarden.data.*;
import quek.undergarden.registry.*;

import java.util.UUID;

@Mod(UndergardenMod.MODID)
public class UndergardenMod {
	
	public static final String MODID = "undergarden";

	public UndergardenMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		UndergardenEntities.ENTITIES.register(bus);
		UndergardenBlocks.BLOCKS.register(bus);
		UndergardenItems.ITEMS.register(bus);
		//UndergardenDimensions.MOD_DIMENSIONS.register(bus);
		//UndergardenBiomes.BIOMES.register(bus);
		UndergardenFeatures.FEATURES.register(bus);
		UndergardenWorldCarvers.CARVERS.register(bus);
		UndergardenEffects.EFFECTS.register(bus);
		UndergardenFluids.FLUIDS.register(bus);

		final Pair<UndergardenConfig.CommonConfig, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(UndergardenConfig.CommonConfig::new);
		final Pair<UndergardenConfig.ClientConfig, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(UndergardenConfig.ClientConfig::new);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPairCommon.getRight());
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairClient.getRight());
	}

	public void setup(FMLCommonSetupEvent event) {
		//UndergardenBiomes.addBiomeTypes();
		//UndergardenBiomes.addBiomeFeatures();
		UndergardenEntities.spawnPlacements();
		UndergardenEntities.entityAttributes();
	}

	public void clientSetup(FMLClientSetupEvent event) {
		ClientStuff.registerBlockRenderers();
		ClientStuff.registerEntityRenderers();
		ClientStuff.registerBlockColors();
		ClientStuff.registerItemColors();
	}


	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();

		if(event.includeClient()) {
			generator.addProvider(new UndergardenBlockStates(generator, event.getExistingFileHelper()));
			generator.addProvider(new UndergardenItemModels(generator, event.getExistingFileHelper()));
			generator.addProvider(new UndergardenLang(generator));
		}
		if(event.includeServer()) {
			generator.addProvider(new UndergardenRecipes(generator));
			generator.addProvider(new UndergardenLootTables(generator));
		}

	}

	@Mod.EventBusSubscriber(modid = MODID)
	public static class ForgeEventBus {

		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public static void renderPlayerEvent(RenderPlayerEvent event) {
			if(event.getEntity() instanceof PlayerEntity && UUID.fromString("353a859b-ba16-4e6a-8f63-9a8c79ab0071").equals(event.getEntity().getUniqueID())) {
				//event.getMatrixStack().scale(.5F, .5F, .5F);
			}
			if(event.getEntity() instanceof PlayerEntity && UUID.fromString("925e5f40-b7d2-4614-8491-c1bc13d8223d").equals(event.getEntity().getUniqueID())) {
				//event.getMatrixStack().scale(1.5F, 1F, 1F);
			}
		}

		/*
		@SubscribeEvent
		public static void registerDimensions(final RegisterDimensionsEvent event) {
			ResourceLocation undergarden = new ResourceLocation(UndergardenMod.MODID, "undergarden");
			ResourceLocation otherside = new ResourceLocation(UndergardenMod.MODID, "otherside");

			if (DimensionType.byName(undergarden) == null) {
				UndergardenDimensions.undergarden_dimension = DimensionManager.registerDimension(undergarden, UndergardenDimensions.UNDERGARDEN.get(), new PacketBuffer(Unpooled.buffer()), false);
				DimensionManager.keepLoaded(UndergardenDimensions.undergarden_dimension, false);
			} else {
				UndergardenDimensions.undergarden_dimension = DimensionType.byName(undergarden);
			}

			if (DimensionType.byName(otherside) == null) {
				UndergardenDimensions.otherside_dimension = DimensionManager.registerDimension(otherside, UndergardenDimensions.OTHERSIDE.get(), new PacketBuffer(Unpooled.buffer()), true);
				DimensionManager.keepLoaded(UndergardenDimensions.otherside_dimension, false);
			} else {
				UndergardenDimensions.otherside_dimension = DimensionType.byName(otherside);
			}
		}
		*/

	}
}
