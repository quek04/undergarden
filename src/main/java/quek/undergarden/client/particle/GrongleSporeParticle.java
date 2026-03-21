package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class GrongleSporeParticle extends SingleQuadParticle {

	private GrongleSporeParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ, TextureAtlasSprite sprite) {
		super(level, x, y, z, motionX, motionY, motionZ, sprite);
		this.setSize(0.01F, 0.01F);
		this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
		this.lifetime = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
		this.hasPhysics = false;
	}

	@Override
	protected Layer getLayer() {
		return Layer.OPAQUE;
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.lifetime-- <= 0) {
			this.remove();
		} else {
			this.move(this.xd, this.yd, this.zd);
		}
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet sprites) {
			this.spriteSet = sprites;
		}

		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			double yMotion = (double) random.nextFloat() * -50.0D * (double) random.nextFloat() * 0.1D;
			return new GrongleSporeParticle(level, x, y, z, 0.0D, yMotion, 0.0D, this.spriteSet.get(random));
		}
	}
}