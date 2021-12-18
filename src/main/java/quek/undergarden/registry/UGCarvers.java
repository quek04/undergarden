package quek.undergarden.registry;

import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.carver.UGCaveWorldCarver;

public class UGCarvers {

    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Undergarden.MODID);

    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> UNDERGARDEN_CAVE = CARVERS.register("undergarden_cave", () -> new UGCaveWorldCarver(CaveCarverConfiguration.CODEC));
}