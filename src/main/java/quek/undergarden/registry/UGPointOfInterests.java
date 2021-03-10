package quek.undergarden.registry;

import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;

public class UGPointOfInterests {

    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Undergarden.MODID);

    public static final RegistryObject<PointOfInterestType> UNDERGARDEN_PORTAL = POI.register("undergarden_portal", () -> new PointOfInterestType("undergarden_portal", PointOfInterestType.getBlockStates(UGBlocks.UNDERGARDEN_PORTAL.get()), 0, 1));
}
