package quek.undergarden.registry;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.potion.*;

public class UGEffects {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, UGMod.MODID);

    public static final RegistryObject<Effect> gooey = EFFECTS.register("gooey", GooeyEffect::new);
}
