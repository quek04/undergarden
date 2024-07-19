package quek.undergarden.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class InfuserUtheriumFuelSlot extends Slot {

	private final InfuserMenu menu;

	public InfuserUtheriumFuelSlot(InfuserMenu menu, Container container, int slot, int x, int y) {
		super(container, slot, x, y);
		this.menu = menu;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return this.menu.isUtheriumFuel(stack);
	}

	@Override
	public boolean isActive() {
		return !this.menu.isRogdoriumFuelFull();
	}
}
