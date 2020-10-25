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
import quek.undergarden.UGMod;
import quek.undergarden.client.particle.*;

@Mod.EventBusSubscriber(modid = UGMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UGMod.MODID);

    public static final RegistryObject<BasicParticleType> shard = PARTICLES.register("shard", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> grongle_spore = PARTICLES.register("grongle_spore", () -> new BasicParticleType(false));

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager particleManager = Minecraft.getInstance().particles;

        particleManager.registerFactory(UGParticles.shard.get(), ShardParticle.Factory::new);
        particleManager.registerFactory(UGParticles.grongle_spore.get(), GrongleSporeParticle.Factory::new);
    }
}
