package quek.undergarden.registry;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;

public class UGAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Undergarden.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<Double>> UTHERIC_INFECTION = ATTACHMENTS.register("utheric_infection", () -> AttachmentType.builder(() -> 0.0D).serialize(Codec.DOUBLE).build());
}