package quek.undergarden.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class DitchbulbPasteItem extends Item {

	public DitchbulbPasteItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {
		return ItemAbilities.FIRESTARTER_LIGHT.equals(itemAbility);
	}
}