package quek.undergarden.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGAttachments;

public record UthericInfectionPacket(int entityID, double infectionLevel) implements CustomPacketPayload {

	public static final Type<UthericInfectionPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "utheric_infection"));
	public static final StreamCodec<FriendlyByteBuf, UthericInfectionPacket> STREAM_CODEC = CustomPacketPayload.codec(UthericInfectionPacket::write, UthericInfectionPacket::new);

	public UthericInfectionPacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readDouble());
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityID());
		buffer.writeDouble(this.infectionLevel());
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	@SuppressWarnings("Convert2Lambda")
	public static void handle(UthericInfectionPacket message, IPayloadContext context) {
		//ensure this is only done on clients as this uses client only code
		//the level is not yet set in the payload context when a player logs in, so we need to fall back to the clientlevel instead
		if (context.flow().isClientbound()) {
			context.enqueueWork(new Runnable() {
				@Override
				public void run() {
					Level level = context.player().level();
					Entity entity = level.getEntity(message.entityID());
					if (entity instanceof LivingEntity living) {
						living.setData(UGAttachments.UTHERIC_INFECTION.get(), message.infectionLevel());
					}
				}
			});
		}
	}
}