package quek.undergarden.registry;

import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.gen.carver.UndergardenCaveWorldCarver;

public class UndergardenWorldCarvers {

    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, UndergardenMod.MODID);

    public static final RegistryObject<WorldCarver<ProbabilityConfig>> UNDERGARDEN_CAVE = CARVERS.register(
            "undergarden_cave", () -> new UndergardenCaveWorldCarver(ProbabilityConfig::deserialize));
}
