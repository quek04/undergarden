package quek.undergarden.registry;

import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.client.particle.*;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGParticleTypes {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Undergarden.MODID);

	public static final RegistryObject<SimpleParticleType> SHARD = PARTICLES.register("shard", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHARD_BEAM = PARTICLES.register("shard_beam", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> GRONGLE_SPORE = PARTICLES.register("grongle_spore", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> UNDERGARDEN_PORTAL = PARTICLES.register("undergarden_portal", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> GLOOMPER_FART = PARTICLES.register("gloomper_fart", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHIMMER = PARTICLES.register("shimmer", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SMOG = PARTICLES.register("smog", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> UTHERIUM_CRIT = PARTICLES.register("utherium_crit", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SNOWFLAKE = PARTICLES.register("snowflake", () -> new SimpleParticleType(false));

	public static final RegistryObject<SimpleParticleType> DRIPPING_BLOOD = PARTICLES.register("dripping_blood", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FALLING_BLOOD = PARTICLES.register("falling_blood", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LANDING_BLOOD = PARTICLES.register("landing_blood", () -> new SimpleParticleType(false));

	public static final RegistryObject<SimpleParticleType> DRIPPING_INK = PARTICLES.register("dripping_ink", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FALLING_INK = PARTICLES.register("falling_ink", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LANDING_INK = PARTICLES.register("landing_ink", () -> new SimpleParticleType(false));

	public static final RegistryObject<SimpleParticleType> DRIPPING_VIRULENT = PARTICLES.register("dripping_virulent", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FALLING_VIRULENT = PARTICLES.register("falling_virulent", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LANDING_VIRULENT = PARTICLES.register("landing_virulent", () -> new SimpleParticleType(false));

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