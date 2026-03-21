package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class UndergardenPortalParticle extends PortalParticle {

	private UndergardenPortalParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ, TextureAtlasSprite sprite) {
		super(level, x, y, z, motionX, motionY, motionZ, sprite);
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			UndergardenPortalParticle portalParticle = new UndergardenPortalParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet.get(random));
			portalParticle.setColor(0.0F, 0.25F, 0.05F);
			return portalParticle;
		}
	}
}