package quek.undergarden.compat.jei;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGDataComponents;

public class InfusionSubInterpreter implements ISubtypeInterpreter<ItemStack> {
	public static final InfusionSubInterpreter INSTANCE = new InfusionSubInterpreter();

	@Override
	@Nullable
	public Object getSubtypeData(ItemStack ingredient, UidContext context) {
		return ingredient.get(UGDataComponents.ROGDORIUM_INFUSION);
	}
}
