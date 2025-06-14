package quek.undergarden.inventory.slot;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.inventory.InfuserMenu;

public class InfuserRogdoriumFuelSlot extends Slot {

	private static final ResourceLocation ICON = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "item/rogdorium_slot");
	private final InfuserMenu menu;

	public InfuserRogdoriumFuelSlot(InfuserMenu menu, Container container, int slot, int x, int y) {
		super(container, slot, x, y);
		this.menu = menu;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return this.menu.isRogdoriumFuel(stack);
	}

	@Override
	public @Nullable Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
		return Pair.of(InventoryMenu.BLOCK_ATLAS, ICON);
	}

	@Override
	public boolean isActive() {
		return !this.menu.isUtheriumFuelFull();
	}
}
