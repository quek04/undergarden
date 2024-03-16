package quek.undergarden.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import quek.undergarden.Undergarden;

public record CreateCritParticlePacket(int entityID, int duration, ParticleType<?> particle) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(Undergarden.MODID, "create_crit_particle");

	public CreateCritParticlePacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readInt(), buf.readById(BuiltInRegistries.PARTICLE_TYPE));
	}

	@Override
	public void write(FriendlyByteBuf buf) {
		buf.writeInt(this.entityID());
		buf.writeInt(this.duration());
		buf.writeId(BuiltInRegistries.PARTICLE_TYPE, this.particle());
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handle(CreateCritParticlePacket message, PlayPayloadContext ctx) {
		ctx.workHandler().execute(() -> {
			Entity entity = Minecraft.getInstance().level.getEntity(message.entityID());
			if (entity != null) {
				Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, (ParticleOptions) message.particle(), message.duration());
			}
		});
	}
}
