package quek.undergarden.client.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import quek.undergarden.registry.UGParticleTypes;

public class TotemBeamParticle extends SimpleAnimatedParticle {

	private final Vec3 target;

	public TotemBeamParticle(ClientLevel level, SpriteSet sprites, double x, double y, double z, Vec3 target) {
		super(level, x, y, z, sprites, 0.0F);
		this.quadSize = 0.15F;
		this.target = target;
		this.hasPhysics = true;
		this.setSpriteFromAge(sprites);
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			int i = this.lifetime - this.age;
			double d0 = 1.0 / (double) i;
			this.x = Mth.lerp(d0, this.x, this.target.x());
			this.y = Mth.lerp(d0, this.y, this.target.y());
			this.z = Mth.lerp(d0, this.z, this.target.z());
		}
		this.setSpriteFromAge(this.sprites);
	}

	public static class Provider implements ParticleProvider<Options> {
		private final SpriteSet sprite;

		public Provider(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(Options options, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			TotemBeamParticle particle = new TotemBeamParticle(level, this.sprite, x, y, z, options.target());
			particle.setLifetime(options.duration());
			return particle;
		}
	}

	public record Options(Vec3 target, int duration) implements ParticleOptions {

		public static final MapCodec<Options> CODEC = RecordCodecBuilder.mapCodec(p_382882_ -> p_382882_.group(
				Vec3.CODEC.fieldOf("target").forGetter(Options::target),
				ExtraCodecs.POSITIVE_INT.fieldOf("duration").forGetter(Options::duration))
			.apply(p_382882_, Options::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, Options> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.fromCodec(Vec3.CODEC),
			Options::target,
			ByteBufCodecs.VAR_INT,
			Options::duration,
			Options::new
		);

		@Override
		public ParticleType<Options> getType() {
			return UGParticleTypes.TOTEM_BEAM.get();
		}
	}
}
