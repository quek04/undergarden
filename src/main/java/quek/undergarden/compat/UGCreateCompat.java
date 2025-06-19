package quek.undergarden.compat;

import com.simibubi.create.api.contraption.train.PortalTrackProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Portal;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDimensions;

public class UGCreateCompat {

	public static void init(IEventBus bus) {
		bus.addListener(FMLCommonSetupEvent.class, event -> event.enqueueWork(() -> {
			PortalTrackProvider p = (level, face) ->
				PortalTrackProvider.fromPortal(level, face, Level.OVERWORLD, UGDimensions.UNDERGARDEN_LEVEL, (Portal) UGBlocks.UNDERGARDEN_PORTAL.get());
			PortalTrackProvider.REGISTRY.register(UGBlocks.UNDERGARDEN_PORTAL.get(), p);
		}));
	}
}
