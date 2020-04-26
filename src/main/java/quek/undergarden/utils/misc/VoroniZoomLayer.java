package quek.undergarden.utils.misc;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

public enum VoroniZoomLayer implements IAreaTransformer1
{
	INSTANCE;

	public int func_215728_a(IExtendedNoiseRandom<?> p_215728_1_, IArea p_215728_2_, int p_215728_3_, int p_215728_4_) {
		int i = p_215728_3_ - 2;
		int j = p_215728_4_ - 2;
		int k = i >> 2;
		int l = j >> 2;
		int i1 = k << 2;
		int j1 = l << 2;
		p_215728_1_.setPosition((long)i1, (long)j1);
		double d0 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D;
		double d1 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D;
		p_215728_1_.setPosition((long)(i1 + 4), (long)j1);
		double d2 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
		double d3 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D;
		p_215728_1_.setPosition((long)i1, (long)(j1 + 4));
		double d4 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D;
		double d5 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
		p_215728_1_.setPosition((long)(i1 + 4), (long)(j1 + 4));
		double d6 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
		double d7 = ((double)p_215728_1_.random(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
		int k1 = i & 3;
		int l1 = j & 3;
		double d8 = ((double)l1 - d1) * ((double)l1 - d1) + ((double)k1 - d0) * ((double)k1 - d0);
		double d9 = ((double)l1 - d3) * ((double)l1 - d3) + ((double)k1 - d2) * ((double)k1 - d2);
		double d10 = ((double)l1 - d5) * ((double)l1 - d5) + ((double)k1 - d4) * ((double)k1 - d4);
		double d11 = ((double)l1 - d7) * ((double)l1 - d7) + ((double)k1 - d6) * ((double)k1 - d6);
		if (d8 < d9 && d8 < d10 && d8 < d11) {
			return p_215728_2_.getValue(this.func_215721_a(i1), this.func_215722_b(j1));
		} else if (d9 < d8 && d9 < d10 && d9 < d11) {
			return p_215728_2_.getValue(this.func_215721_a(i1 + 4), this.func_215722_b(j1)) & 255;
		} else {
			return d10 < d8 && d10 < d9 && d10 < d11 ? p_215728_2_.getValue(this.func_215721_a(i1), this.func_215722_b(j1 + 4)) : p_215728_2_.getValue(this.func_215721_a(i1 + 4), this.func_215722_b(j1 + 4)) & 255;
		}
	}

	public int func_215721_a(int p_215721_1_) {
		return p_215721_1_ >> 2;
	}

	public int func_215722_b(int p_215722_1_) {
		return p_215722_1_ >> 2;
	}
}