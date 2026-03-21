package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class RogdoriumWispParticle extends SimpleAnimatedParticle {

	protected RogdoriumWispParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
		super(level, x, y, z, sprites, -0.1F);
		this.xd = xSpeed;
		this.yd = ySpeed;
		this.zd = zSpeed;
		this.lifetime = 44;
		this.setSpriteFromAge(sprites);
		this.hasPhysics = false;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites) {
			this.sprites = sprites;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			return new RogdoriumWispParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
		}
	}
}
