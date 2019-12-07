package quek.undergarden;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.*;

@Mod(Undergarden.MODID)
public class Undergarden {
	
	public static final String MODID = "undergarden";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public Undergarden() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		BlockRegistry.BLOCKS.register(bus);
		ItemRegistry.ITEMS.register(bus);
	}

	public void setup(FMLCommonSetupEvent event) {

	}

}
