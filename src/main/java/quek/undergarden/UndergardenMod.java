package quek.undergarden;

import io.netty.buffer.Unpooled;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import quek.undergarden.client.ClientStuff;
import quek.undergarden.data.*;
import quek.undergarden.registry.*;
import quek.undergarden.world.gen.carver.*;

import java.util.UUID;

@Mod(UndergardenMod.MODID)
public class UndergardenMod {
	
	public static final String MODID = "undergarden";

	public static DimensionType undergarden_dimension;
	public static DimensionType otherside_dimension;

	public static final CreatureAttribute ROTSPAWN = new CreatureAttribute();

	public UndergardenMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		UndergardenEntities.ENTITIES.register(bus);
		UndergardenBlocks.BLOCKS.register(bus);
		UndergardenItems.ITEMS.register(bus);
		UndergardenDimensions.MOD_DIMENSIONS.register(bus);
		UndergardenBiomes.BIOMES.register(bus);
		UndergardenFeatures.FEATURES.register(bus);
		UndergardenWorldCarvers.CARVERS.register(bus);
		UndergardenEffects.EFFECTS.register(bus);
		UndergardenFluids.FLUIDS.register(bus);
	}

	public void setup(FMLCommonSetupEvent event) {
		UndergardenBiomes.addBiomeTypes();
		UndergardenBiomes.addBiomeFeatures();
		UndergardenEntities.spawnPlacements();
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

		@SubscribeEvent
		public static void registerModDimension(final RegisterDimensionsEvent event) {
			ResourceLocation undergarden = new ResourceLocation(UndergardenMod.MODID, "undergarden");
			ResourceLocation otherside = new ResourceLocation(UndergardenMod.MODID, "otherside");

			if (DimensionType.byName(undergarden) == null) {
				undergarden_dimension = DimensionManager.registerDimension(undergarden, UndergardenDimensions.UNDERGARDEN.get(), new PacketBuffer(Unpooled.buffer()), false);
				DimensionManager.keepLoaded(undergarden_dimension, false);
			} else {
				undergarden_dimension = DimensionType.byName(undergarden);
			}

			if (DimensionType.byName(otherside) == null) {
				otherside_dimension = DimensionManager.registerDimension(otherside, UndergardenDimensions.OTHERSIDE.get(), new PacketBuffer(Unpooled.buffer()), true);
				DimensionManager.keepLoaded(otherside_dimension, false);
			} else {
				otherside_dimension = DimensionType.byName(undergarden);
			}
		}

		@SubscribeEvent
		public static void registerWorldCarver(final RegistryEvent.Register<WorldCarver<?>> event) {
			event.getRegistry().register(new UndergardenCaveWorldCarver(ProbabilityConfig::deserialize));
		}

		@ObjectHolder("undergarden:undergarden_cave")
		public static UndergardenCaveWorldCarver UNDERGARDEN_CAVE;

	}
}
