package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.CatacombsStructure;
import quek.undergarden.world.gen.structure.ForgottenRuinStructure;

public class UGStructures {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Undergarden.MODID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> CATACOMBS = STRUCTURES.register("catacombs", () -> new CatacombsStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> FORGOTTEN_RUIN = STRUCTURES.register("forgotten_ruin", () -> new ForgottenRuinStructure(JigsawConfiguration.CODEC));

    public static final ResourceKey<ConfiguredStructureFeature<?, ?>> CATACOMBS_KEY = ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(Undergarden.MODID, "catacombs"));
}