package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class OthersideAshParticle extends BaseAshSmokeParticle {
	protected OthersideAshParticle(ClientLevel level, double x, double y, double z, double pXSpeed, double pYSpeed, double pZSpeed, float pQuadSizeMultiplier, SpriteSet pSprites) {
		super(level, x, y, z, 0.0F, -0.1F, -0.1F, pXSpeed, pYSpeed, pZSpeed, pQuadSizeMultiplier, pSprites, 0.0F, 20, 0.0125F, false);
		this.rCol = (float) FastColor.ARGB32.red(12759473) / 255.0F;
		this.gCol = (float)FastColor.ARGB32.green(12759473) / 255.0F;
		this.bCol = (float)FastColor.ARGB32.blue(12759473) / 255.0F;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;
		public Provider(SpriteSet pSprites) {
			this.sprites = pSprites;
		}
		@Nullable
		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			RandomSource random = level.random;
			double d0 = (double)random.nextFloat() * -1.9 * (double)random.nextFloat() * 0.1;
			double d1 = (double)random.nextFloat() * -0.5 * (double)random.nextFloat() * 0.1 * 5.0;
			return new OthersideAshParticle(level, x, y, z, d0, d1, -0.5D, 1.0F, this.sprites);
		}
	}
}
