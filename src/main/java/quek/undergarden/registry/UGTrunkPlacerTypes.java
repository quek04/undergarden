package quek.undergarden.registry;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import quek.undergarden.UGMod;
import quek.undergarden.world.gen.trunkplacer.*;

public class UGTrunkPlacerTypes {

    public static final TrunkPlacerType<SmogstemTrunkPlacer> SMOGSTEM_TRUNK_PLACER = register("smogstem_trunk_placer", SmogstemTrunkPlacer.CODEC);

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, new ResourceLocation(UGMod.MODID, name), new TrunkPlacerType<>(codec));
    }
}
