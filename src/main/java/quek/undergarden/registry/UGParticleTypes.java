package quek.undergarden.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.client.particle.GrongleSporeParticle;
import quek.undergarden.client.particle.ShardParticle;
import quek.undergarden.client.particle.UndergardenPortalParticle;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Undergarden.MODID);

    public static final RegistryObject<SimpleParticleType> SHARD = PARTICLES.register("shard", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GRONGLE_SPORE = PARTICLES.register("grongle_spore", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> UNDERGARDEN_PORTAL = PARTICLES.register("undergarden_portal", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GLOOMPER_FART = PARTICLES.register("gloomper_fart", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleEngine particleManager = Minecraft.getInstance().particleEngine;

        particleManager.register(SHARD.get(), ShardParticle.Factory::new);
        particleManager.register(GRONGLE_SPORE.get(), GrongleSporeParticle.Factory::new);
        particleManager.register(UNDERGARDEN_PORTAL.get(), UndergardenPortalParticle.Factory::new);
        particleManager.register(GLOOMPER_FART.get(), SmokeParticle.Provider::new);
    }
}