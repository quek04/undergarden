package quek.undergarden;

import io.netty.buffer.Unpooled;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.data.UndergardenBlockStates;
import quek.undergarden.data.UndergardenItemModels;
import quek.undergarden.data.UndergardenLootTables;
import quek.undergarden.data.UndergardenRecipes;
import quek.undergarden.data.provider.UndergardenRecipeProvider;
import quek.undergarden.entity.render.*;
import quek.undergarden.registry.*;

import java.util.function.Consumer;

@Mod(Undergarden.MODID)
public class Undergarden {
	
	public static final String MODID = "undergarden";
	
	public static final Logger LOGGER = LogManager.getLogger(Undergarden.class);

	public Undergarden() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);


		UndergardenBlocks.BLOCKS.register(bus);
		UndergardenItems.ITEMS.register(bus);
		UndergardenEntities.ENTITIES.register(bus);
		UndergardenDimensions.MOD_DIMENSIONS.register(bus);
		UndergardenDimensions.BIOME_PROVIDER_TYPES.register(bus);
		UndergardenDimensions.CHUNK_GENERATOR_TYPES.register(bus);
	}

	public void setup(FMLCommonSetupEvent event) {
		DimensionManager.registerOrGetDimension(new ResourceLocation(MODID, "undergarden_dimension"), UndergardenDimensions.UNDERGARDEN_DIMENSION.get(), new PacketBuffer(Unpooled.buffer()), false);
	}

	public void clientSetup(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotwalker, RotwalkerRender::new);
		RenderingRegistry.registerEntityRenderingHandler(UndergardenEntities.rotbeast, RotbeastRender::new);
	}


	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		generator.addProvider(new UndergardenRecipes(generator));
		//generator.addProvider(new UndergardenLootTables(generator));
		generator.addProvider(new UndergardenBlockStates(generator, event.getExistingFileHelper()));
		generator.addProvider(new UndergardenItemModels(generator, event.getExistingFileHelper()));
	}

}
