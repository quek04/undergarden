package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import quek.undergarden.Undergarden;

import net.minecraft.world.gen.feature.structure.Structure.IStartFactory;

public class CatacombsStructure extends AbstractUndergardenStructure {

    public CatacombsStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return CatacombsStructure.Start::new;
    }

    public static class Start extends AbstractUndergardenStructure.Start {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos yPos = getHighestLand(chunkGenerator);
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);

            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(Undergarden.MODID, "catacombs/catacombs_entrance")), 100),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.pieces,
                    this.random,
                    true,
                    false);

            this.pieces.forEach(piece -> piece.move(0, 1, 0));
            this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);

            this.calculateBoundingBox();

            if (yPos.getY() >= 250 || yPos.getY() <= 32) {
                this.moveInsideHeights(this.random, 39, 40);
            }
            else {
                this.moveInsideHeights(this.random, yPos.getY()-15, yPos.getY()-14);
            }
        }
    }
}