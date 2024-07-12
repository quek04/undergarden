package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import quek.undergarden.registry.UGStructures;

import java.util.List;
import java.util.Optional;

public class BiggerJigsawStructure extends Structure {
	public static final MapCodec<BiggerJigsawStructure> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		settingsCodec(instance),
		StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter((structure) -> structure.startPool),
		ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter((structure) -> structure.startJigsawName),
		Codec.intRange(0, 100).fieldOf("size").forGetter((structure) -> structure.maxDepth),
		HeightProvider.CODEC.fieldOf("start_height").forGetter((structure) -> structure.startHeight),
		Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter((structure) -> structure.projectStartToHeightmap),
		Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter((structure) -> structure.maxDistanceFromCenter),
		Codec.list(PoolAliasBinding.CODEC).optionalFieldOf("pool_aliases", List.of()).forGetter(structure -> structure.poolAliases),
		DimensionPadding.CODEC.optionalFieldOf("dimension_padding", DimensionPadding.ZERO).forGetter(structure -> structure.dimensionPadding),
		LiquidSettings.CODEC.optionalFieldOf("liquid_settings", LiquidSettings.APPLY_WATERLOGGING).forGetter(structure -> structure.liquidSettings)
	).apply(instance, BiggerJigsawStructure::new));

	private final Holder<StructureTemplatePool> startPool;
	private final Optional<ResourceLocation> startJigsawName;
	private final int maxDepth;
	private final HeightProvider startHeight;
	private final Optional<Heightmap.Types> projectStartToHeightmap;
	private final int maxDistanceFromCenter;
	private final List<PoolAliasBinding> poolAliases;
	private final DimensionPadding dimensionPadding;
	private final LiquidSettings liquidSettings;

	public BiggerJigsawStructure(Structure.StructureSettings structureSettings, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int maxDepth, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter, List<PoolAliasBinding> aliases, DimensionPadding dimensionPadding, LiquidSettings liquidSettings) {
		super(structureSettings);
		this.startPool = startPool;
		this.startJigsawName = startJigsawName;
		this.maxDepth = maxDepth;
		this.startHeight = startHeight;
		this.projectStartToHeightmap = projectStartToHeightmap;
		this.maxDistanceFromCenter = maxDistanceFromCenter;
		this.poolAliases = aliases;
		this.dimensionPadding = dimensionPadding;
		this.liquidSettings = liquidSettings;
	}

	@Override
	public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
		WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
		random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
		int x = context.chunkPos().getMinBlockX() + random.nextInt(16);
		int z = context.chunkPos().getMinBlockZ() + random.nextInt(16);
		int minY = context.chunkGenerator().getMinY();
		int y = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
		NoiseColumn column = context.chunkGenerator().getBaseColumn(x, z, context.heightAccessor(), context.randomState());
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(x, y, z);

		while (y > minY) {
			BlockState state = column.getBlock(y);
			--y;
			BlockState state1 = column.getBlock(y);
			if (state.isAir() && state1.isFaceSturdy(EmptyBlockGetter.INSTANCE, mutablePos.setY(y), Direction.UP)) {
				break;
			}
		}

		if (y <= minY) {
			return Optional.empty();
		} else {
			BlockPos pos = new BlockPos(x, y, z);
			return JigsawPlacement.addPieces(
				context,
				this.startPool,
				this.startJigsawName,
				this.maxDepth,
				pos,
				false,
				this.projectStartToHeightmap,
				this.maxDistanceFromCenter,
				PoolAliasLookup.create(this.poolAliases, pos, context.seed()),
				this.dimensionPadding,
				this.liquidSettings
			);
		}
	}

	@Override
	public StructureType<?> type() {
		return UGStructures.BIGGER_JIGSAW.get();
	}
}
