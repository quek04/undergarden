package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class SnowflakeParticle extends SingleQuadParticle {

	private float rotSpeed;
	private final float spinAcceleration;

	protected SnowflakeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
		this.friction = 1.0F;
		this.xd += xSpeed * 0.05D;
		this.zd += zSpeed * 0.05D;
		this.xd *= 0.0075F;
		this.yd = 0.0F;
		this.zd *= 0.0075F;
		this.gravity = 0.0005F;
		this.lifetime = 80;
		this.quadSize *= 0.5F;
		this.hasPhysics = true;
		this.rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -60.0D : 60.0D);
		this.spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -25.0D : 25.0D);
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		}

		if (!this.removed) {
			this.yd -= this.gravity;
			this.rotSpeed += this.spinAcceleration / 20.0F;
			this.oRoll = this.roll;
			this.roll += this.rotSpeed / 20.0F;
			if (!this.onGround) {
				this.move(this.xd, this.yd, this.zd);
			} else {
				this.age = Math.max(this.lifetime - 10, this.age);
			}

			if (this.age > this.lifetime - 10) {
				this.scale(Mth.abs(this.age - this.lifetime) * 0.1F);
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
			return new SnowflakeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet.get(random));
		}
	}
}
