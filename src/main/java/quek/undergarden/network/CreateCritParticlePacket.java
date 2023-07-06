package quek.undergarden.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class CreateCritParticlePacket {

	private final int id;
	private final int duration;
	private final ParticleType<?> particle;

	public CreateCritParticlePacket(int entityId, int duration, ParticleType<?> particle) {
		this.id = entityId;
		this.duration = duration;
		this.particle = particle;
	}

	public CreateCritParticlePacket(FriendlyByteBuf buf) {
		this.id = buf.readInt();
		this.duration = buf.readInt();
		this.particle = buf.readRegistryIdUnsafe(ForgeRegistries.PARTICLE_TYPES);
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(this.id);
		buf.writeInt(this.duration);
		buf.writeRegistryIdUnsafe(ForgeRegistries.PARTICLE_TYPES, this.particle);
	}

	public static class Handler {

		public static boolean onMessage(CreateCritParticlePacket message, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				Entity entity = Minecraft.getInstance().level.getEntity(message.id);
				if (entity != null) {
					Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, (ParticleOptions) message.particle, message.duration);
				}
			});
			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
