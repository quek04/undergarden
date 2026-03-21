package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGParticleTypes;

@SuppressWarnings("unused")
public class UGDripParticles extends DripParticle {

	public UGDripParticles(ClientLevel level, double x, double y, double z, Fluid fluid, TextureAtlasSprite sprite) {
		super(level, x, y, z, fluid, sprite);
	}

	public static class BloodHangProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BloodHangProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle hangParticle = new DripParticle.DripHangParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.FALLING_BLOOD.get(), this.spriteSet.get(random));
			hangParticle.gravity *= 0.01F;
			hangParticle.setLifetime(10);
			hangParticle.setColor(0.622F, 0.082F, 0.082F);
			return hangParticle;
		}
	}

	public static class BloodFallProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BloodFallProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.LANDING_BLOOD.get(), this.spriteSet.get(random));
			dripparticle.gravity = 0.01F;
			dripparticle.setColor(0.622F, 0.082F, 0.082F);
			return dripparticle;
		}
	}

	public static class BloodLandProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BloodLandProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY, this.spriteSet.get(random));
			dripparticle.setLifetime((int) (64.0D / (Math.random() * 0.8D + 0.2D)));
			dripparticle.setColor(0.622F, 0.082F, 0.082F);
			return dripparticle;
		}
	}

	public static class InkHangProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public InkHangProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle hangParticle = new DripParticle.DripHangParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.FALLING_INK.get(), this.spriteSet.get(random));
			hangParticle.gravity *= 0.01F;
			hangParticle.setLifetime(10);
			hangParticle.setColor(0.0F, 0.0F, 0.0F);
			return hangParticle;
		}
	}

	public static class InkFallProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public InkFallProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.LANDING_INK.get(), this.spriteSet.get(random));
			dripparticle.gravity = 0.01F;
			dripparticle.setColor(0.0F, 0.0F, 0.0F);
			return dripparticle;
		}
	}

	public static class InkLandProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public InkLandProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY, this.spriteSet.get(random));
			dripparticle.setLifetime((int) (64.0D / (Math.random() * 0.8D + 0.2D)));
			dripparticle.setColor(0.0F, 0.0F, 0.0F);
			return dripparticle;
		}
	}

	public static class GooFallProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public GooFallProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.LANDING_GOO.get(), this.spriteSet.get(random));
			dripparticle.gravity = 0.01F;
			dripparticle.setColor(0.482F, 0.447F, 0.329F);
			return dripparticle;
		}
	}

	public static class GooLandProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public GooLandProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY, this.spriteSet.get(random));
			dripparticle.setLifetime((int) (32.0D / (Math.random() * 0.8D + 0.2D)));
			dripparticle.setColor(0.482F, 0.447F, 0.329F);
			return dripparticle;
		}
	}

	public static class DripstoneVirulentHangProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public DripstoneVirulentHangProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.DripHangParticle(level, x, y, z, UGFluids.VIRULENT_MIX_SOURCE.get(), UGParticleTypes.FALLING_VIRULENT.get(), this.spriteSet.get(random));
			dripparticle.setColor(0.3F, 0.0F, 0.3F);
			return dripparticle;
		}
	}

	public static class DripstoneVirulentFallProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public DripstoneVirulentFallProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new VirulentFallAndLandParticle(level, x, y, z, UGFluids.VIRULENT_MIX_SOURCE.get(), UGParticleTypes.LANDING_VIRULENT.get(), this.spriteSet.get(random));
			dripparticle.setColor(0.3F, 0.0F, 0.3F);
			return dripparticle;
		}
	}

	public static class DripstoneVirulentLandProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public DripstoneVirulentLandProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, UGFluids.VIRULENT_MIX_SOURCE.get(), this.spriteSet.get(random));
			dripparticle.setColor(0.3F, 0.0F, 0.3F);
			return dripparticle;
		}
	}

	static class VirulentFallAndLandParticle extends DripParticle.FallAndLandParticle {
		public VirulentFallAndLandParticle(ClientLevel level, double x, double y, double z, Fluid fluid, ParticleOptions particle, TextureAtlasSprite sprite) {
			super(level, x, y, z, fluid, particle, sprite);
		}

		@Override
		protected void postMoveUpdate() {
			if (this.onGround) {
				this.remove();
				this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
				float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
				this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA, SoundSource.BLOCKS, f, 1.0F, false);
			}

		}
	}
}
