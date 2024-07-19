package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.recipe.InfusingRecipe;

public class UGRecipeSerializers {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Undergarden.MODID);

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InfusingRecipe>> INFUSING = RECIPE_SERIALIZERS.register("infusing", InfusingRecipe.Serializer::new);
}
