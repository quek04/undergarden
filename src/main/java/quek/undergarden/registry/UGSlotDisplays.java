package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.display.CatalystSlotDisplay;

public class UGSlotDisplays {

	public static final DeferredRegister<SlotDisplay.Type<?>> SLOTS = DeferredRegister.create(Registries.SLOT_DISPLAY, Undergarden.MODID);

	public static final DeferredHolder<SlotDisplay.Type<?>, SlotDisplay.Type<CatalystSlotDisplay>> CATALYST = SLOTS.register("catalyst", () -> CatalystSlotDisplay.TYPE);
}
