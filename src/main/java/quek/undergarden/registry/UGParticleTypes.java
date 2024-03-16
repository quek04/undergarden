package quek.undergarden.registry;

import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.client.particle.*;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGParticleTypes {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Undergarden.MODID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHARD = PARTICLES.register("shard", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHARD_BEAM = PARTICLES.register("shard_beam", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GRONGLE_SPORE = PARTICLES.register("grongle_spore", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> UNDERGARDEN_PORTAL = PARTICLES.register("undergarden_portal", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GLOOMPER_FART = PARTICLES.register("gloomper_fart", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHIMMER = PARTICLES.register("shimmer", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMOG = PARTICLES.register("smog", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> UTHERIUM_CRIT = PARTICLES.register("utherium_crit", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SNOWFLAKE = PARTICLES.register("snowflake", () -> new SimpleParticleType(false));

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_BLOOD = PARTICLES.register("dripping_blood", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_BLOOD = PARTICLES.register("falling_blood", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_BLOOD = PARTICLES.register("landing_blood", () -> new SimpleParticleType(false));

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_INK = PARTICLES.register("dripping_ink", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_INK = PARTICLES.register("falling_ink", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_INK = PARTICLES.register("landing_ink", () -> new SimpleParticleType(false));

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_VIRULENT = PARTICLES.register("dripping_virulent", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_VIRULENT = PARTICLES.register("falling_virulent", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_VIRULENT = PARTICLES.register("landing_virulent", () -> new SimpleParticleType(false));

	public static final ParticleGroup SHIMMER_GROUP = new ParticleGroup(1000);

	@SubscribeEvent
	public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(SHARD.get(), ShardParticle.Provider::new);
		event.registerSpriteSet(SHARD_BEAM.get(), ShardParticle.BeamProvider::new);
		event.registerSpriteSet(GRONGLE_SPORE.get(), GrongleSporeParticle.Provider::new);
		event.registerSpriteSet(UNDERGARDEN_PORTAL.get(), UndergardenPortalParticle.Provider::new);
		event.registerSpriteSet(GLOOMPER_FART.get(), SmokeParticle.Provider::new);
		event.registerSpriteSet(SHIMMER.get(), ShimmerParticle.Provider::new);
		event.registerSpriteSet(SMOG.get(), SmogParticle.Provider::new);
		event.registerSpriteSet(UTHERIUM_CRIT.get(), UtheriumCritParticle.Provider::new);
		event.registerSpriteSet(SNOWFLAKE.get(), SnowflakeParticle.Provider::new);

		event.registerSprite(DRIPPING_BLOOD.get(), UGDripParticles::createBloodHangParticle);
		event.registerSprite(FALLING_BLOOD.get(), UGDripParticles::createBloodFallParticle);
		event.registerSprite(LANDING_BLOOD.get(), UGDripParticles::createBloodLandParticle);
		event.registerSprite(DRIPPING_INK.get(), UGDripParticles::createInkHangParticle);
		event.registerSprite(FALLING_INK.get(), UGDripParticles::createInkFallParticle);
		event.registerSprite(LANDING_INK.get(), UGDripParticles::createInkLandParticle);
		event.registerSprite(DRIPPING_VIRULENT.get(), UGDripParticles::createDripstoneVirulentHangParticle);
		event.registerSprite(FALLING_VIRULENT.get(), UGDripParticles::createDripstoneVirulentFallParticle);
		event.registerSprite(LANDING_VIRULENT.get(), UGDripParticles::createVirulentLandParticle);
	}
}