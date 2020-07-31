package quek.undergarden.world.layer;

import net.minecraft.world.storage.DerivedWorldInfo;

public class UndergardenBiomeProviderSettings
{
	private long seed;
	private DerivedWorldInfo worldInfo;

	public UndergardenBiomeProviderSettings setWorldInfo(DerivedWorldInfo info)
	{
		this.worldInfo = info;
		return this;
	}

	public UndergardenBiomeProviderSettings setSeed(long seed)
	{
		this.seed = seed;
		return this;
	}

	public long getSeed()
	{
		return this.seed;
	}


	public DerivedWorldInfo getWorldInfo()
	{
		return this.worldInfo;
	}
}
