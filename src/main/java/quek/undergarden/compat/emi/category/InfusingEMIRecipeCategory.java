package quek.undergarden.compat.emi.category;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;

public class InfusingEMIRecipeCategory extends EmiRecipeCategory {

	public InfusingEMIRecipeCategory() {
		super(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "infusing"), EmiStack.of(UGBlocks.INFUSER.get()));
	}


}
