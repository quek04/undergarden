package quek.undergarden.world.gen.structure;

    import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
    import net.minecraft.world.level.ChunkPos;
    import net.minecraft.world.level.LevelHeightAccessor;
    import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import quek.undergarden.Undergarden;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;

public class CatacombsStructure extends AbstractUndergardenStructure {

    public CatacombsStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return CatacombsStructure.Start::new;
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {

        public Start(StructureFeature<NoneFeatureConfiguration> pFeature, ChunkPos pChunkPos, int pReferences, long pSeed) {
            super(pFeature, pChunkPos, pReferences, pSeed);
        }

        @Override
        public void generatePieces(RegistryAccess pRegistryAccess, ChunkGenerator pChunkGenerator, StructureManager pStructureManager, ChunkPos pChunkPos, Biome pBiome, NoneFeatureConfiguration pConfig, LevelHeightAccessor pLevel) {
            BlockPos blockPos = new BlockPos(pChunkPos.getMinBlockX() * 16, 32, pChunkPos.getMinBlockZ() * 16);

            JigsawPlacement.addPieces(
                    pRegistryAccess,
                    new JigsawConfiguration(() -> pRegistryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(Undergarden.MODID, "catacombs/catacombs_entrance")), 100),
                    PoolElementStructurePiece::new,
                    pChunkGenerator,
                    pStructureManager,
                    blockPos,
                    this,
                    this.random,
                    false,
                    false,
                    pLevel);

            this.pieces.forEach(piece -> piece.move(0, 1, 0));

            this.createBoundingBox();
        }
    }
}