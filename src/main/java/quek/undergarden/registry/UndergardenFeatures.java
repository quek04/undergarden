package quek.undergarden.registry;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.world.gen.feature.*;
import quek.undergarden.world.gen.structure.*;
import quek.undergarden.world.gen.structure.piece.*;

public class UndergardenFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, UndergardenMod.MODID);

    public static final RegistryObject<Feature<TreeFeatureConfig>> SMOGSTEM_TREE = FEATURES.register(
            "smogstem_tree", () -> new SmogstemFeature(TreeFeatureConfig::func_227338_a_));
    public static final RegistryObject<Feature<TreeFeatureConfig>> FANCY_SMOGSTEM_TREE = FEATURES.register(
            "fancy_smogstem", () -> new FancySmogstemFeature(TreeFeatureConfig::func_227338_a_));
    public static final RegistryObject<Feature<TreeFeatureConfig>> WIGGLEWOOD_TREE = FEATURES.register(
            "wigglewood_tree", () -> new WigglewoodFeature(TreeFeatureConfig::func_227338_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> GLOWING_KELP = FEATURES.register(
            "glowing_kelp", () -> new GlowingKelpFeature(NoFeatureConfig::deserialize));

    public static IStructurePieceType DEPTHROCK_RUIN_TYPE = DepthrockRuinPieces.Piece::new;

    public static final RegistryObject<Structure<NoFeatureConfig>> DEPTHROCK_RUIN = FEATURES.register(
            "depthrock_ruin", DepthrockRuinStructure::new);

}
