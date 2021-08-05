package quek.undergarden.registry;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.potion.*;

public class UGEffects {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Undergarden.MODID);

    public static final RegistryObject<Effect> GOOEY = EFFECTS.register("gooey", GooeyEffect::new);
    public static final RegistryObject<Effect> BRITTLENESS = EFFECTS.register("brittleness", BrittlenessEffect::new);
    public static final RegistryObject<Effect> FEATHERWEIGHT = EFFECTS.register("featherweight", FeatherweightEffect::new);
    public static final RegistryObject<Effect> VIRULENT_RESISTANCE = EFFECTS.register("virulent_resistance", VirulentResistanceEffect::new);
}