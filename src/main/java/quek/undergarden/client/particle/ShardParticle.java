package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.RisingParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.RandomSource;

public class ShardParticle extends RisingParticle {

	private ShardParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ, TextureAtlasSprite sprite) {
		super(level, x, y, z, motionX, motionY, motionZ, sprite);
	}

	@Override
	protected Layer getLayer() {
		return Layer.OPAQUE;
	}

	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().move(x, y, z));
		this.setLocationFromBoundingbox();
	}

	@Override
	public float getQuadSize(float scaleFactor) {
		float scale = ((float) this.age + scaleFactor) / (float) this.lifetime;
		return this.quadSize * (1.0F - scale * scale * 0.5F);
	}

	@Override
	protected int getLightCoords(float partialTicks) {
		return LightCoordsUtil.addSmoothBlockEmission(super.getLightCoords(partialTicks), (this.age + partialTicks) / this.lifetime);
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			return new ShardParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet.get(random));
		}
	}

	public static class BeamProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BeamProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			ShardParticle shard = new ShardParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet.get(random));
			shard.lifetime = 5;
			return shard;
		}
	}
}