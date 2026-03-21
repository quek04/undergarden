package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;

public class UGRecipeBookCategories {

	public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, Undergarden.MODID);

	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> INFUSER_SEARCH = RECIPE_BOOK_CATEGORIES.register("infuser_search", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> INFUSER_PURIFYING = RECIPE_BOOK_CATEGORIES.register("infuser_purifying", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> INFUSER_CORRUPTING = RECIPE_BOOK_CATEGORIES.register("infuser_corrupting", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> INFUSER_MISC = RECIPE_BOOK_CATEGORIES.register("infuser_misc", RecipeBookCategory::new);
}
