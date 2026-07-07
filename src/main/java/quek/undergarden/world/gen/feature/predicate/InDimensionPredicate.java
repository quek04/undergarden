package quek.undergarden.world.gen.feature.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicateType;
import quek.undergarden.registry.UGBlockPredicates;

public record InDimensionPredicate(ResourceKey<Level> dimension) implements BlockPredicate {

	public static final MapCodec<InDimensionPredicate> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
			ResourceKey.codec(Registries.DIMENSION).fieldOf("dimension").forGetter(InDimensionPredicate::dimension))
		.apply(i, InDimensionPredicate::new));

	@Override
	public BlockPredicateType<?> type() {
		return UGBlockPredicates.IN_DIMENSION.get();
	}

	@Override
	public boolean test(WorldGenLevel gen, BlockPos pos) {
		if (gen instanceof Level level) {
			return level.dimension().equals(this.dimension());
		}
		return true; //I dont know man, I guess place the dirt
	}
}
