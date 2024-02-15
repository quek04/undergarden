package quek.undergarden.registry;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.capability.IUndergardenPortal;
import quek.undergarden.capability.UndergardenPortalCapability;

@Mod.EventBusSubscriber(modid = Undergarden.MODID)
public class UndergardenCapabilities {

    public static final Capability<IUndergardenPortal> UNDERGARDEN_PORTAL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            UndergardenPortalCapability portal = new UndergardenPortalCapability(player);
            LazyOptional<IUndergardenPortal> capability = LazyOptional.of(() -> portal);
            ICapabilityProvider provider = new ICapabilityProvider() {
                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    return capability.cast();
                }
            };
            event.addCapability(new ResourceLocation(Undergarden.MODID, "portal"), provider);
        }
    }
}
