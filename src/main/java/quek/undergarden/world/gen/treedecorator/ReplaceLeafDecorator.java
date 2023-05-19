package quek.undergarden.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import quek.undergarden.registry.UGTreeDecoratorTypes;

public class ReplaceLeafDecorator extends TreeDecorator {

	public static final Codec<ReplaceLeafDecorator> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							Codec.floatRange(0.0F, 1.0F)
									.fieldOf("probability")
									.forGetter(decorator -> decorator.probability),
							BlockStateProvider.CODEC
									.fieldOf("block")
									.forGetter(decorator -> decorator.blockProvider)
					)
					.apply(instance, ReplaceLeafDecorator::new)
	);

	private final float probability;
	protected final BlockStateProvider blockProvider;

	public ReplaceLeafDecorator(float probability, BlockStateProvider blockProvider) {
		this.probability = probability;
		this.blockProvider = blockProvider;
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return UGTreeDecoratorTypes.REPLACE_LEAF_DECORATOR.get();
	}

	@Override
	public void place(Context context) {
		RandomSource random = context.random();

		context.leaves().forEach(pos -> {
			if (random.nextFloat() < this.probability) {
				context.setBlock(pos, blockProvider.getState(random, pos));
			}
		});
	}
}
