package quek.undergarden.registry;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.attachment.UndergardenPortalAttachment;

public class UGAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Undergarden.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<UndergardenPortalAttachment>> UNDERGARDEN_PORTAL = ATTACHMENTS.register("undergarden_portal", () -> AttachmentType.builder(UndergardenPortalAttachment::new).build());
	public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> UTHERIC_INFECTION = ATTACHMENTS.register("utheric_infection", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());
}