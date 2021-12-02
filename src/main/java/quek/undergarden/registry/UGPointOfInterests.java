package quek.undergarden.registry;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;

public class UGPointOfInterests {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Undergarden.MODID);

    public static final RegistryObject<PoiType> UNDERGARDEN_PORTAL = POI.register("undergarden_portal", () -> new PoiType("undergarden_portal", PoiType.getBlockStates(UGBlocks.UNDERGARDEN_PORTAL.get()), 0, 1));
}
