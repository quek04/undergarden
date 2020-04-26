package quek.undergarden.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import quek.undergarden.registry.UndergardenBiomes;
import quek.undergarden.utils.UndergardenLayerUtil;
import quek.undergarden.utils.misc.LazyInt;

public class UndergardenBiomeLayer implements IAreaTransformer0
{
	private static final int UNCOMMON_BIOME_CHANCE = 8;
	private static final int RARE_BIOME_CHANCE = 16;
	protected LazyInt[] commonBiomes = new LazyInt[]{
			UndergardenLayerUtil.lazyId(UndergardenBiomes.EXAMPLE_BIOME),
			UndergardenLayerUtil.lazyId(UndergardenBiomes.FORGOTTEN_ABYSS)

	};
	protected LazyInt[] uncommonBiomes = (new LazyInt[]{
			// add uncommon biomes here

			// for quek: there has to be something in both uncommon and are biomes or else it crashes

			UndergardenLayerUtil.lazyId(UndergardenBiomes.FORGOTTEN_ABYSS),

	});
	protected LazyInt[] rareBiomes = (new LazyInt[]{
			// add rare biomes here

			// for quek: there has to be something in both uncommon and are biomes or else it crashes
			UndergardenLayerUtil.lazyId(UndergardenBiomes.FORGOTTEN_ABYSS),

	});

	public UndergardenBiomeLayer()
	{
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int rand1, int rand2)
	{
		if (iNoiseRandom.random(RARE_BIOME_CHANCE) == 0) {
			//magic number!
			return rareBiomes[iNoiseRandom.random(rareBiomes.length)].getAsInt();
		} else if (iNoiseRandom.random(UNCOMMON_BIOME_CHANCE) == 0) {
			//Well, it's no rare biome, but it will suffice
			return uncommonBiomes[iNoiseRandom.random(uncommonBiomes.length)].getAsInt();
		} else {
			//aww...
			return commonBiomes[iNoiseRandom.random(commonBiomes.length)].getAsInt();
		}
	}
}
