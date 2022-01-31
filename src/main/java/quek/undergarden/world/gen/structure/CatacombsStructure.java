package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGEntityTypes;

import java.util.Collections;
import java.util.List;

public class CatacombsStructure extends StructureFeature<JigsawConfiguration> {

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> FORGOTTEN_SPAWNS = WeightedRandomList.create(new MobSpawnSettings.SpawnerData(UGEntityTypes.FORGOTTEN.get(), 100, 1, 1));

    public CatacombsStructure(Codec<JigsawConfiguration> codec) {
        super(codec, (context) -> {
            JigsawConfiguration catacombsConfig = new JigsawConfiguration(() -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(Undergarden.MODID, "catacombs/catacombs_entrance")), 50);
            PieceGeneratorSupplier.Context<JigsawConfiguration> catacombsContext = new PieceGeneratorSupplier.Context<>(context.chunkGenerator(), context.biomeSource(), context.seed(), context.chunkPos(), catacombsConfig, context.heightAccessor(), context.validBiome(), context.structureManager(), context.registryAccess());
            BlockPos catacombsPos = new BlockPos(context.chunkPos().getMinBlockX(), 34, context.chunkPos().getMinBlockZ());
            return JigsawPlacement.addPieces(catacombsContext, PoolElementStructurePiece::new, catacombsPos, false, false);
        });
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public String getFeatureName() {
        return "undergarden:catacombs";
    }

    @Override
    public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList(MobCategory category) {
        if(category == MobCategory.MONSTER) {
            return FORGOTTEN_SPAWNS.unwrap();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean getDefaultRestrictsSpawnsToInside() {
        return true;
    }
}