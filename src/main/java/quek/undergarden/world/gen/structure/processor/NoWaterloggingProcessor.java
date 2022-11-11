package quek.undergarden.world.gen.structure.processor;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.registry.UGStructureProcessors;

public class NoWaterloggingProcessor extends StructureProcessor {

    public static final Codec<NoWaterloggingProcessor> CODEC = Codec.unit(NoWaterloggingProcessor::new);

    private NoWaterloggingProcessor () {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos p_74417_, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo relativeBlockInfo, StructurePlaceSettings settings) {
        if (!relativeBlockInfo.state.getFluidState().isEmpty()) {
            if (level instanceof WorldGenRegion region && !region.getCenter().equals(new ChunkPos(relativeBlockInfo.pos))) {
                return relativeBlockInfo;
            }

            ChunkAccess chunk = level.getChunk(relativeBlockInfo.pos);
            int minY = chunk.getMinBuildHeight();
            int maxY = chunk.getMaxBuildHeight();
            int currentY = relativeBlockInfo.pos.getY();
            if (currentY >= minY && currentY <= maxY) {
                ((LevelAccessor) level).scheduleTick(relativeBlockInfo.pos, relativeBlockInfo.state.getBlock(), 0);
            }
        }
        return relativeBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return UGStructureProcessors.NO_WATERLOGGING.get();
    }
}
