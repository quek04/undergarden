package quek.undergarden.inventory.slot;

import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.Undergarden;
import quek.undergarden.inventory.InfuserMenu;

public class InfuserUtheriumFuelSlot extends Slot {

	private static final Identifier ICON = Undergarden.prefix("container/slot/utherium");
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
	public Identifier getNoItemIcon() {
		return ICON;
	}

	@Override
	public boolean isActive() {
		return !this.menu.isRogdoriumFuelFull();
	}
}
