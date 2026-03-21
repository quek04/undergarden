package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class UtheriumCritParticle extends SingleQuadParticle {

	private float rotSpeed;
	private final float spinAcceleration;

	protected UtheriumCritParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
		this.friction = 1.0F;
		this.xd *= 0.1F;
		this.yd *= 0.1F;
		this.zd *= 0.1F;
		this.xd += xSpeed * 0.05D;
		this.yd += ySpeed * 0.1D;
		this.zd += zSpeed * 0.05D;
		this.quadSize *= 0.75F;
		this.hasPhysics = true;
		this.rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -90.0D : 90.0D);
		this.spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -15.0D : 15.0D);
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.lifetime-- <= 0) {
			this.remove();
		}

		if (!this.removed) {
			this.yd -= this.gravity;
			this.rotSpeed += this.spinAcceleration / 20.0F;
			this.oRoll = this.roll;
			this.roll += this.rotSpeed / 20.0F;
			this.move(this.xd, this.yd, this.zd);
			if (this.onGround) {
				this.remove();
			}
		}
	}

	@Override
	protected Layer getLayer() {
		return Layer.OPAQUE;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			UtheriumCritParticle crit = new UtheriumCritParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet.get(random));
			crit.lifetime = 40;
			crit.gravity = 0.025F;
			return crit;
		}
	}
}
