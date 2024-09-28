package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import quek.undergarden.registry.UGFluids;
import quek.undergarden.registry.UGParticleTypes;

@SuppressWarnings("unused")
public class UGDripParticles extends DripParticle {

	public UGDripParticles(ClientLevel level, double x, double y, double z, Fluid fluid) {
		super(level, x, y, z, fluid);
	}

	public static TextureSheetParticle createBloodHangParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle hangParticle = new DripParticle.DripHangParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.FALLING_BLOOD.get());
		hangParticle.gravity *= 0.01F;
		hangParticle.setLifetime(10);
		hangParticle.setColor(0.622F, 0.082F, 0.082F);
		return hangParticle;
	}

	public static TextureSheetParticle createBloodFallParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.LANDING_BLOOD.get());
		dripparticle.gravity = 0.01F;
		dripparticle.setColor(0.622F, 0.082F, 0.082F);
		return dripparticle;
	}

	public static TextureSheetParticle createBloodLandParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY);
		dripparticle.setLifetime((int) (64.0D / (Math.random() * 0.8D + 0.2D)));
		dripparticle.setColor(0.622F, 0.082F, 0.082F);
		return dripparticle;
	}

	public static TextureSheetParticle createInkHangParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle hangParticle = new DripParticle.DripHangParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.FALLING_INK.get());
		hangParticle.gravity *= 0.01F;
		hangParticle.setLifetime(10);
		hangParticle.setColor(0.0F, 0.0F, 0.0F);
		return hangParticle;
	}

	public static TextureSheetParticle createInkFallParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.LANDING_INK.get());
		dripparticle.gravity = 0.01F;
		dripparticle.setColor(0.0F, 0.0F, 0.0F);
		return dripparticle;
	}

	public static TextureSheetParticle createInkLandParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY);
		dripparticle.setLifetime((int) (64.0D / (Math.random() * 0.8D + 0.2D)));
		dripparticle.setColor(0.0F, 0.0F, 0.0F);
		return dripparticle;
	}

	public static TextureSheetParticle createGooFallParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.FallAndLandParticle(level, x, y, z, Fluids.EMPTY, UGParticleTypes.LANDING_GOO.get());
		dripparticle.gravity = 0.01F;
		dripparticle.setColor(0.482F, 0.447F, 0.329F);
		return dripparticle;
	}

	public static TextureSheetParticle createGooLandParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY);
		dripparticle.setLifetime((int) (32.0D / (Math.random() * 0.8D + 0.2D)));
		dripparticle.setColor(0.482F, 0.447F, 0.329F);
		return dripparticle;
	}

	public static TextureSheetParticle createDripstoneVirulentHangParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.DripHangParticle(level, x, y, z, UGFluids.VIRULENT_MIX_SOURCE.get(), UGParticleTypes.FALLING_VIRULENT.get());
		dripparticle.setColor(0.3F, 0.0F, 0.3F);
		return dripparticle;
	}

	public static TextureSheetParticle createDripstoneVirulentFallParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new VirulentFallAndLandParticle(level, x, y, z, UGFluids.VIRULENT_MIX_SOURCE.get(), UGParticleTypes.LANDING_VIRULENT.get());
		dripparticle.setColor(0.3F, 0.0F, 0.3F);
		return dripparticle;
	}

	public static TextureSheetParticle createVirulentLandParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		DripParticle dripparticle = new DripParticle.DripLandParticle(level, x, y, z, UGFluids.VIRULENT_MIX_SOURCE.get());
		dripparticle.setColor(0.3F, 0.0F, 0.3F);
		return dripparticle;
	}

	static class VirulentFallAndLandParticle extends DripParticle.FallAndLandParticle {
		public VirulentFallAndLandParticle(ClientLevel level, double x, double y, double z, Fluid fluid, ParticleOptions particle) {
			super(level, x, y, z, fluid, particle);
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
