package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import quek.undergarden.Undergarden;

public class UGConfiguredCarvers {

    public static final ConfiguredWorldCarver<CaveCarverConfiguration> UNDERGARDEN_CAVE = register("undergarden_cave", UGCarvers.UNDERGARDEN_CAVE.get().configured(new CaveCarverConfiguration(0.5F, UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top()), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), false, ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));

    private static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> register(String name, ConfiguredWorldCarver<WC> carver) {
        return Registry.register(BuiltinRegistries.CONFIGURED_CARVER, new ResourceLocation(Undergarden.MODID, name), carver);
    }

    public static void init() {}
}