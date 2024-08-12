package quek.undergarden.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import quek.undergarden.Undergarden;
import quek.undergarden.client.UndergardenClient;

public class UndergardenPortalSoundPacket implements CustomPacketPayload {

	public static final UndergardenPortalSoundPacket INSTANCE = new UndergardenPortalSoundPacket();
	public static final Type<UndergardenPortalSoundPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "portal_sound"));
	public static final StreamCodec<RegistryFriendlyByteBuf, UndergardenPortalSoundPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext context) {
		context.enqueueWork(UndergardenClient::playPortalSound);
	}
}