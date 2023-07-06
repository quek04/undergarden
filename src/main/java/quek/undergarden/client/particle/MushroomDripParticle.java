package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import quek.undergarden.registry.UGParticleTypes;

@SuppressWarnings("unused")
public class MushroomDripParticle extends DripParticle {

	public MushroomDripParticle(ClientLevel level, double x, double y, double z, Fluid fluid) {
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
		dripparticle.setLifetime((int)(64.0D / (Math.random() * 0.8D + 0.2D)));
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
		dripparticle.setLifetime((int)(64.0D / (Math.random() * 0.8D + 0.2D)));
		dripparticle.setColor(0.0F, 0.0F, 0.0F);
		return dripparticle;
	}
}
