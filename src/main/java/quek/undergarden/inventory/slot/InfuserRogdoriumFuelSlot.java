package quek.undergarden.inventory.slot;

import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.Undergarden;
import quek.undergarden.inventory.InfuserMenu;

public class InfuserRogdoriumFuelSlot extends Slot {

	private static final Identifier ICON = Undergarden.prefix("container/slot/rogdorium");
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
	public Identifier getNoItemIcon() {
		return ICON;
	}

	@Override
	public boolean isActive() {
		return !this.menu.isUtheriumFuelFull();
	}
}
