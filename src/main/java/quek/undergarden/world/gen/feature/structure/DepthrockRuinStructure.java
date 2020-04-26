package quek.undergarden.world.gen.feature.structure;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IglooPieces;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenFeatures;
import quek.undergarden.world.gen.feature.structure.piece.DepthrockRuinPieces;

import java.util.Random;
import java.util.function.Function;

public class DepthrockRuinStructure extends ScatteredStructure<NoFeatureConfig> {

    public DepthrockRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    protected int getSeedModifier() {
        return 1973594165;
    }

    /*
    @Override
    public boolean func_225558_a_(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
        ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);

        //Checks to see if current chunk is valid to spawn in.
        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            //Checks if the biome can spawn this structure.
            if (generatorIn.hasStructure(biomeIn, this)) {
                return true;
            }
        }

        return false;
    }

     */

    @Override
    public IStartFactory getStartFactory() {
        return DepthrockRuinStructure.Start::new;
    }

    @Override
    public String getStructureName() {
        return UndergardenMod.MODID + ":depthrock_ruin";
    }

    @Override
    public int getSize() {
        return 0;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i225806_1_, int p_i225806_2_, int p_i225806_3_, MutableBoundingBox p_i225806_4_, int p_i225806_5_, long p_i225806_6_) {
            super(p_i225806_1_, p_i225806_2_, p_i225806_3_, p_i225806_4_, p_i225806_5_, p_i225806_6_);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            NoFeatureConfig nofeatureconfig = generator.getStructureConfig(biomeIn, UndergardenFeatures.DEPTHROCK_RUIN.get());
            int i = chunkX * 16;
            int j = chunkZ * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            DepthrockRuinPieces.start(templateManagerIn, blockpos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
