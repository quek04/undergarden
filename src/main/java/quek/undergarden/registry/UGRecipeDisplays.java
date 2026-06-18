package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.display.InfusingRecipeDisplay;

public class UGRecipeDisplays {

	public static final DeferredRegister<RecipeDisplay.Type<?>> RECIPE_DISPLAYS = DeferredRegister.create(Registries.RECIPE_DISPLAY, Undergarden.MODID);

	public static final DeferredHolder<RecipeDisplay.Type<?>, RecipeDisplay.Type<InfusingRecipeDisplay>> INFUSING = RECIPE_DISPLAYS.register("infusing", () -> InfusingRecipeDisplay.TYPE);
}
