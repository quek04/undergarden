package quek.undergarden.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import quek.undergarden.registry.UndergardenBiomes;
import quek.undergarden.utils.UndergardenLayerUtil;
import quek.undergarden.utils.misc.LazyInt;

public class UndergardenBiomeLayer implements IAreaTransformer0 {
	private static final int UNCOMMON_BIOME_CHANCE = 8;
	private static final int RARE_BIOME_CHANCE = 16;

	protected LazyInt[] commonBiomes = new LazyInt[] {
			UndergardenLayerUtil.lazyId(UndergardenBiomes.FORGOTTEN_FIELD),
			UndergardenLayerUtil.lazyId(UndergardenBiomes.SMOG_SPIRES)
	};
	protected LazyInt[] uncommonBiomes = (new LazyInt[] {
			UndergardenLayerUtil.lazyId(UndergardenBiomes.SMOGSTEM_FOREST),
			UndergardenLayerUtil.lazyId(UndergardenBiomes.WIGGLEWOOD_FOREST)

	});
	protected LazyInt[] rareBiomes = (new LazyInt[] {
			UndergardenLayerUtil.lazyId(UndergardenBiomes.BARREN_ABYSS),
			UndergardenLayerUtil.lazyId(UndergardenBiomes.DENSE_FOREST)
	});

	public UndergardenBiomeLayer() {
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int rand1, int rand2) {
		if (iNoiseRandom.random(RARE_BIOME_CHANCE) == 0) {
			return rareBiomes[iNoiseRandom.random(rareBiomes.length)].getAsInt();
		} else if (iNoiseRandom.random(UNCOMMON_BIOME_CHANCE) == 0) {
			return uncommonBiomes[iNoiseRandom.random(uncommonBiomes.length)].getAsInt();
		} else {
			return commonBiomes[iNoiseRandom.random(commonBiomes.length)].getAsInt();
		}
	}
}
