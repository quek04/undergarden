package quek.undergarden.item.bucket;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Consumables;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.ItemAccessFluidHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;

public class ItemAccessBucketHandler extends ItemAccessFluidHandler {

	public ItemAccessBucketHandler(ItemAccess itemAccess, DataComponentType<SimpleFluidContent> component, int capacity) {
		super(itemAccess, component, capacity);
	}

	@Override
	protected ItemResource update(ItemResource accessResource, int index, FluidResource newResource, int newAmount) {
		ItemResource filled = super.update(accessResource, index, newResource, newAmount);
		if (newResource.is(NeoForgeMod.MILK)) {
			return filled.with(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET);
		}
		return filled.without(DataComponents.CONSUMABLE);
	}
}
