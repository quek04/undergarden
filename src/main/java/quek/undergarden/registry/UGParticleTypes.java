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
    public static final RegistryObject<SimpleParticleType> GRONGLE_SPORE = PARTICLES.register("grongle_spore", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> UNDERGARDEN_PORTAL = PARTICLES.register("undergarden_portal", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GLOOMPER_FART = PARTICLES.register("gloomper_fart", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SHIMMER = PARTICLES.register("shimmer", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SMOG = PARTICLES.register("smog", () -> new SimpleParticleType(false));

    public static final ParticleGroup SHIMMER_GROUP = new ParticleGroup(1000);

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.register(SHARD.get(), ShardParticle.Provider::new);
        event.register(GRONGLE_SPORE.get(), GrongleSporeParticle.Provider::new);
        event.register(UNDERGARDEN_PORTAL.get(), UndergardenPortalParticle.Provider::new);
        event.register(GLOOMPER_FART.get(), SmokeParticle.Provider::new);
        event.register(SHIMMER.get(), ShimmerParticle.Provider::new);
        event.register(SMOG.get(), SmogParticle.Provider::new);
    }
}