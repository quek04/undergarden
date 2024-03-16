package quek.undergarden.registry;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.attachment.UndergardenPortalAttachment;

@Mod.EventBusSubscriber(modid = Undergarden.MODID)
public class UGAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Undergarden.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<UndergardenPortalAttachment>> UNDERGARDEN_PORTAL = ATTACHMENTS.register("undergarden_portal", () -> AttachmentType.builder(UndergardenPortalAttachment::new).build());

	/*public static final Capability<IUndergardenPortal> UNDERGARDEN_PORTAL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player player) {
			UndergardenPortalCapability portal = new UndergardenPortalCapability(player);
			LazyOptional<IUndergardenPortal> capability = LazyOptional.of(() -> portal);
			ICapabilityProvider provider = new ICapabilityProvider() {
				@Override
				public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
					return UNDERGARDEN_PORTAL_CAPABILITY.orEmpty(cap, capability);
				}
			};
			event.addCapability(new ResourceLocation(Undergarden.MODID, "portal"), provider);
		}
	}*/
}
