package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.inventory.InfuserMenu;

public class UGMenuTypes {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, Undergarden.MODID);

	public static final DeferredHolder<MenuType<?>, MenuType<InfuserMenu>> INFUSER = MENU_TYPES.register("infuser", () -> new MenuType<>(InfuserMenu::new, FeatureFlags.VANILLA_SET));
}
