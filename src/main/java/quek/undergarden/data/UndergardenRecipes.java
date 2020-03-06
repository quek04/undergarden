package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.data.provider.UndergardenRecipeProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItems;
import quek.undergarden.registry.UndergardenTags;

import java.util.function.Consumer;

public class UndergardenRecipes extends UndergardenRecipeProvider {

    public UndergardenRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        makePlanks(UndergardenBlocks.smogstem_planks, UndergardenTags.Items.SMOGSTEM_LOG).build(consumer, resourceLocation("smogstem_planks"));
        makePlanks(UndergardenBlocks.wigglewood_planks, UndergardenTags.Items.WIGGLEWOOD_LOG).build(consumer, resourceLocation("wigglewood_planks"));

        makeSticks(UndergardenItems.smogstem_stick, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, resourceLocation("smogstem_stick"));

        makeShardToIngot().build(consumer, resourceLocation("shard_to_ingot"));

        makeIngotToNugget(UndergardenItems.cloggrum_nugget, UndergardenItems.cloggrum_ingot).build(consumer, resourceLocation("cloggrum_ingot_to_nugget"));
        makeIngotToNugget(UndergardenItems.utherium_chunk, UndergardenItems.utherium_ingot).build(consumer, resourceLocation("utherium_ingot_to_nugget"));

        makeNuggetToIngot(UndergardenItems.cloggrum_ingot, UndergardenItems.cloggrum_nugget).build(consumer, resourceLocation("cloggrum_nugget_to_ingot"));
        makeNuggetToIngot(UndergardenItems.utherium_ingot, UndergardenItems.utherium_chunk).build(consumer, resourceLocation("utherium_chunk_to_ingot"));

        makeTagSword(UndergardenItems.smogstem_sword, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, resourceLocation("smogstem_sword"));
        makeSword(UndergardenItems.cloggrum_sword, UndergardenItems.cloggrum_ingot).build(consumer, resourceLocation("cloggrum_sword"));
        makeSword(UndergardenItems.utheric_sword, UndergardenItems.utherium_ingot).build(consumer, resourceLocation("utheric_sword"));

        makeTagPickaxe(UndergardenItems.smogstem_pickaxe, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, resourceLocation("smogstem_pickaxe"));
        makePickaxe(UndergardenItems.cloggrum_pickaxe, UndergardenItems.cloggrum_ingot).build(consumer, resourceLocation("cloggrum_pickaxe"));
        makePickaxe(UndergardenItems.utheric_pickaxe, UndergardenItems.utherium_ingot).build(consumer, resourceLocation("utheric_pickaxe"));

        makeTagAxe(UndergardenItems.smogstem_axe, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, resourceLocation("smogstem_axe"));
        makeAxe(UndergardenItems.cloggrum_axe, UndergardenItems.cloggrum_ingot).build(consumer, resourceLocation("cloggrum_axe"));
        makeAxe(UndergardenItems.utheric_axe, UndergardenItems.utherium_ingot).build(consumer, resourceLocation("utheric_axe"));

        makeTagShovel(UndergardenItems.smogstem_shovel, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, resourceLocation("smogstem_shovel"));
        makeShovel(UndergardenItems.cloggrum_shovel, UndergardenItems.cloggrum_ingot).build(consumer, resourceLocation("cloggrum_shovel"));
        makeShovel(UndergardenItems.utheric_shovel, UndergardenItems.utherium_ingot).build(consumer, resourceLocation("utheric_shovel"));
    }

    private ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(Undergarden.MODID, name);
    }
}
