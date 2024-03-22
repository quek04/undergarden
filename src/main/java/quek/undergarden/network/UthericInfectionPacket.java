package quek.undergarden.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGAttachments;

public record UthericInfectionPacket(int entityID, int infectionLevel) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(Undergarden.MODID, "utheric_infection");

	public UthericInfectionPacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readInt());
	}
	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityID());
		buffer.writeInt(this.infectionLevel());
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handle(UthericInfectionPacket message, PlayPayloadContext context) {
		context.workHandler().execute(() -> {
			Entity entity = Minecraft.getInstance().level.getEntity(message.entityID());
			if (entity instanceof Player player) {
				player.setData(UGAttachments.UTHERIC_INFECTION.get(), message.infectionLevel());
			}
		});
	}
}