package quek.undergarden.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.particle.*;

@Mod.EventBusSubscriber(modid = UndergardenMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergardenParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UndergardenMod.MODID);

    public static final RegistryObject<BasicParticleType> shard = PARTICLES.register("shard", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> grongle_spore = PARTICLES.register("grongle_spore", () -> new BasicParticleType(false));

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager particleManager = Minecraft.getInstance().particles;

        particleManager.registerFactory(UndergardenParticles.shard.get(), ShardParticle.Factory::new);
        particleManager.registerFactory(UndergardenParticles.grongle_spore.get(), GrongleSporeParticle.Factory::new);
    }
}
