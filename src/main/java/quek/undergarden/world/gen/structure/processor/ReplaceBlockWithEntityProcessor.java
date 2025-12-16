package quek.undergarden.world.gen.structure.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGStructureProcessors;

import javax.annotation.Nullable;

public class ReplaceBlockWithEntityProcessor extends StructureProcessor {
	public static final MapCodec<ReplaceBlockWithEntityProcessor> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(processor -> processor.block),
			Codec.FLOAT.fieldOf("probability").forGetter(processor -> processor.probability),
			BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity").forGetter(processor -> processor.entity)
		).apply(instance, ReplaceBlockWithEntityProcessor::new)
	);
	private final Block block;
	private final float probability;
	private final EntityType<?> entity;

	public ReplaceBlockWithEntityProcessor(Block block, float probability, EntityType<?> entity) {
		this.block = block;
		this.probability = probability;
		this.entity = entity;
	}

	@Override
	public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo relativeBlockInfo, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
		BlockState state = blockInfo.state();
		RandomSource random = RandomSource.create(Mth.getSeed(blockInfo.pos()));
		if (state.is(this.block) && random.nextFloat() < this.probability) {
			if (level instanceof ServerLevel serverLevel) {
				Entity entity = this.entity.spawn(serverLevel, relativeBlockInfo.pos(), MobSpawnType.STRUCTURE);
				Undergarden.LOGGER.info("Pot at {}", entity.position());
			}
			return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos(), Blocks.AIR.defaultBlockState(), relativeBlockInfo.nbt());
		}
		return relativeBlockInfo;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return UGStructureProcessors.REPLACE_BLOCK_WITH_ENTITY.get();
	}
}