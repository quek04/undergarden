package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleLimit;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import quek.undergarden.registry.UGParticleTypes;

import java.util.Optional;

public class ShimmerParticle extends SingleQuadParticle {

	private final SpriteSet sprites;

	private ShimmerParticle(ClientLevel level, SpriteSet sprites, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites.first());
		this.sprites = sprites;
		this.lifetime = 50;
		this.gravity = 0.01F;
		this.xd *= xSpeed;
		this.yd *= ySpeed;
		this.zd *= zSpeed;
		this.setSpriteFromAge(sprites);
		this.hasPhysics = true;
	}

	@Override
	protected Layer getLayer() {
		return Layer.OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}

	@Override
	public Optional<ParticleLimit> getParticleLimit() {
		return Optional.of(UGParticleTypes.SHIMMER_GROUP);
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites) {
			this.sprites = sprites;
		}

		@Override
		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			return new ShimmerParticle(level, this.sprites, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}