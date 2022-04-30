package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AllYLevelStructure extends StructureFeature<JigsawConfiguration> {

    public AllYLevelStructure(Codec<JigsawConfiguration> codec, PieceGeneratorSupplier<JigsawConfiguration> context) {
        super(codec, context);
    }

    protected static @NotNull Optional<PieceGenerator<JigsawConfiguration>> placement(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        WorldGenerationContext generationContext = new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor());
        int x = context.chunkPos().getMinBlockX() + random.nextInt(16);
        int z = context.chunkPos().getMinBlockZ() + random.nextInt(16);
        int seaLevel = context.chunkGenerator().getSeaLevel();
        int y = UniformHeight.of(VerticalAnchor.absolute(seaLevel), VerticalAnchor.belowTop(2)).sample(random, generationContext);
        NoiseColumn column = context.chunkGenerator().getBaseColumn(x, z, context.heightAccessor());
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(x, y, z);

        while (y > seaLevel) {
            BlockState state = column.getBlock(y);
            --y;
            BlockState state1 = column.getBlock(y);
            if (state.isAir() && state1.isFaceSturdy(EmptyBlockGetter.INSTANCE, mutablePos.setY(y), Direction.UP)) {
                break;
            }
        }

        if (y <= seaLevel) {
            return Optional.empty();
        } else {
            BlockPos pos = new BlockPos(x, y, z);
            return JigsawPlacement.addPieces(context, PoolElementStructurePiece::new, pos, false, false);
        }
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}