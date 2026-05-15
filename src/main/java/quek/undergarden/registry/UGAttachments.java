package quek.undergarden.registry;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.component.UndergardenData;

public class UGAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Undergarden.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<UndergardenData>> UNDERGARDEN_DATA = ATTACHMENTS.register("undergarden_data", () -> AttachmentType.builder(UndergardenData::new).serialize(new UndergardenData()).copyHandler(new UndergardenData()).sync(new UndergardenData()).build());
}