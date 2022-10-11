package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
@SuppressWarnings("unused")
public class UGConfiguredCarvers {

    public static final DeferredRegister<ConfiguredWorldCarver<?>> CONFIGURED_CARVERS = DeferredRegister.create(Registry.CONFIGURED_CARVER_REGISTRY, Undergarden.MODID);

    public static final RegistryObject<ConfiguredWorldCarver<CaveCarverConfiguration>> UNDERGARDEN_CAVE = CONFIGURED_CARVERS.register("undergarden_cave", () -> UGCarvers.UNDERGARDEN_CAVE.get().configured(new CaveCarverConfiguration(0.5F, UniformHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.belowTop(1)), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), Registry.BLOCK.getOrCreateTag(UGTags.Blocks.UNDERGARDEN_CARVER_REPLACEABLES), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));
}