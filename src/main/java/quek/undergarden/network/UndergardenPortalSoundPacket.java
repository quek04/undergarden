package quek.undergarden.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import quek.undergarden.Undergarden;
import quek.undergarden.client.UndergardenClient;

public record UndergardenPortalSoundPacket() implements CustomPacketPayload {

	public static final Type<UndergardenPortalSoundPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "portal_sound"));
	public static final StreamCodec<RegistryFriendlyByteBuf, UndergardenPortalSoundPacket> STREAM_CODEC = CustomPacketPayload.codec(UndergardenPortalSoundPacket::write, UndergardenPortalSoundPacket::new);

	public UndergardenPortalSoundPacket(RegistryFriendlyByteBuf buf) {
		this();
	}

	public void write(RegistryFriendlyByteBuf buf) {

	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(UndergardenPortalSoundPacket packet, IPayloadContext context) {
		if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
			context.enqueueWork(UndergardenClient::playPortalSound);
		}
	}
}