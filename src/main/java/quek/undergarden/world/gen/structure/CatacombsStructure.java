package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import quek.undergarden.Undergarden;

public class CatacombsStructure extends AbstractUndergardenStructure {

    public CatacombsStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public String getFeatureName() {
        return "undergarden:catacombs";
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {

        public Start(StructureFeature<NoneFeatureConfiguration> pFeature, ChunkPos pChunkPos, int pReferences, long pSeed) {
            super(pFeature, pChunkPos, pReferences, pSeed);
        }

        @Override
        public void generatePieces(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor level) {
            BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), 33, chunkPos.getMinBlockZ());

            JigsawPlacement.addPieces(
                    registryAccess,
                    new JigsawConfiguration(() -> registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(Undergarden.MODID, "catacombs/catacombs_entrance")), 100),
                    PoolElementStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this,
                    this.random,
                    false,
                    false,
                    level);

            this.pieces.forEach(piece -> piece.move(0, 1, 0));

            this.createBoundingBox();
        }
    }
}