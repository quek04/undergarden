package quek.undergarden.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;

public class UGAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Undergarden.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> UTHERIC_INFECTION = ATTACHMENTS.register("utheric_infection", () -> AttachmentType.builder(() -> 0.0F).serialize(Codec.FLOAT.fieldOf("infection_level")).build());
	public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> PREVIOUS_UTHERIC_INFECTION_DAMAGE = ATTACHMENTS.register("previous_utheric_infection_damage", () -> AttachmentType.builder(() -> 0.0F).serialize(Codec.FLOAT.fieldOf("previous_infection_damage")).build());
}