package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class UtheriumCritParticle extends TextureSheetParticle {

	private float rotSpeed;
	private final float spinAcceleration;

	protected UtheriumCritParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(level, x, y, z, xSpeed, ySpeed, zSpeed);
		this.friction = 1.0F;
		this.xd *= 0.1F;
		this.yd *= 0.1F;
		this.zd *= 0.1F;
		this.xd += xSpeed * 0.05D;
		this.yd += ySpeed * 0.1D;
		this.zd += zSpeed * 0.05D;
		this.quadSize *= 0.75F;
		this.hasPhysics = true;
		this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? -90.0D : 90.0D);
		this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? -15.0D : 15.0D);
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
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			UtheriumCritParticle crit = new UtheriumCritParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
			crit.lifetime = 40;
			crit.gravity = 0.025F;
			crit.pickSprite(this.spriteSet);
			return crit;
		}
	}
}
