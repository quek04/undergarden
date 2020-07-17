package quek.undergarden.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
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
        makePlanks(UndergardenBlocks.smogstem_planks, UndergardenBlocks.smogstem_log).build(consumer, name("smogstem_planks"));
        makePlanks(UndergardenBlocks.wigglewood_planks, UndergardenBlocks.wigglewood_log).build(consumer, name("wigglewood_planks"));

        makeBricks(UndergardenBlocks.depthrock_bricks, UndergardenBlocks.depthrock).build(consumer);
        makeBricks(UndergardenBlocks.shiverstone_bricks, UndergardenBlocks.shiverstone).build(consumer);
        makeBricks(UndergardenBlocks.tremblecrust_bricks, UndergardenBlocks.tremblecrust).build(consumer);

        makeWood(UndergardenBlocks.smogstem_wood, UndergardenBlocks.smogstem_log).build(consumer);
        makeWood(UndergardenBlocks.wigglewood_wood, UndergardenBlocks.wigglewood_log).build(consumer);

        makeSticks(UndergardenItems.smogstem_stick, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, name("smogstem_stick"));
        //makeSticks(UndergardenItems.twistytwig, UndergardenTags.Items.WIGGLEWOOD_PLANKS).build(consumer, name("twistytwig"));

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.twistytwig.get(), 4)
                .patternLine("P ")
                .patternLine(" P")
                .key('P', UndergardenBlocks.wigglewood_planks.get())
                .addCriterion("has_wigglewood_planks", hasItem(UndergardenBlocks.wigglewood_planks.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Blocks.SCAFFOLDING, 6)
                .patternLine("STS")
                .patternLine("S S")
                .patternLine("S S")
                .key('S', UndergardenItems.smogstem_stick.get())
                .key('T', UndergardenItems.twistytwig.get())
                .addCriterion("has_smogstem_stick", hasItem(UndergardenItems.smogstem_stick.get()))
                .addCriterion("has_twistytwig", hasItem(UndergardenItems.twistytwig.get()))
                .build(consumer, name("undergarden_scaffolding"));

        ShapedRecipeBuilder.shapedRecipe(UndergardenBlocks.gloom_o_lantern.get())
                .patternLine("G")
                .patternLine("T")
                .key('G', UndergardenBlocks.carved_gloomgourd.get())
                .key('T', UndergardenItems.smogstem_torch.get())
                .addCriterion("has_carved_gourd", hasItem(UndergardenBlocks.carved_gloomgourd.get()))
                .addCriterion("has_torch", hasItem(UndergardenItems.smogstem_torch.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UndergardenBlocks.cloggrum_bars.get(), 16)
                .patternLine("CCC")
                .patternLine("CCC")
                .key('C', UndergardenItems.cloggrum_ingot.get())
                .addCriterion("has_cloggrum_ingot", hasItem(UndergardenItems.cloggrum_ingot.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.slingshot.get())
                .patternLine("STS")
                .patternLine("SSS")
                .patternLine(" S ")
                .key('S', UndergardenItems.smogstem_stick.get())
                .key('T', UndergardenItems.twistytwig.get())
                .addCriterion("has_smogstem_stick", hasItem(UndergardenItems.smogstem_stick.get()))
                .addCriterion("has_twistytwig", hasItem(UndergardenItems.twistytwig.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.blisterbomb.get())
                .patternLine(" T ")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('T', UndergardenItems.twistytwig.get())
                .key('B', UndergardenItems.rotten_blisterberry.get())
                .addCriterion("has_twistytwig", hasItem(UndergardenItems.twistytwig.get()))
                .addCriterion("has_blisterberry", hasItem(UndergardenItems.rotten_blisterberry.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.cloggrum_shield.get())
                .patternLine("CSC")
                .patternLine("CCC")
                .patternLine(" C ")
                .key('S', UndergardenBlocks.smogstem_planks.get())
                .key('C', UndergardenItems.cloggrum_ingot.get())
                .addCriterion("has_scales", hasItem(UndergardenItems.cloggrum_ingot.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.smogstem_torch.get(), 4)
                .patternLine("C")
                .patternLine("S")
                .key('C', Items.COAL)
                .key('S', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_coal", hasItem(Items.COAL))
                .addCriterion("has_smogstem_stick", hasItem(UndergardenItems.smogstem_stick.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.smogstem_torch.get(), 2)
                .patternLine("D")
                .patternLine("S")
                .key('D', UndergardenItems.ditchbulb.get())
                .key('S', UndergardenItems.smogstem_stick.get())
                .addCriterion("has_ditchbulb", hasItem(UndergardenItems.ditchbulb.get()))
                .addCriterion("has_smogstem_stick", hasItem(UndergardenItems.smogstem_stick.get()))
                .build(consumer, name("smogstem_torch_ditchbulb"));

        ShapedRecipeBuilder.shapedRecipe(UndergardenItems.catalyst_item.get())
                .patternLine("GIG")
                .patternLine("IDI")
                .patternLine("GIG")
                .key('G', Items.GOLD_INGOT)
                .key('I', Items.IRON_INGOT)
                .key('D', Items.DIAMOND)
                .addCriterion("has_gold", hasItem(Items.GOLD_INGOT))
                .addCriterion("has_iron", hasItem(Items.IRON_INGOT))
                .addCriterion("has_diamond", hasItem(Items.DIAMOND))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(UndergardenItems.gloomgourd_pie.get())
                .addIngredient(UndergardenTags.Items.MUSHROOMS)
                .addIngredient(UndergardenBlocks.gloomgourd.get())
                .addIngredient(UndergardenItems.glowing_kelp.get())
                .addCriterion("has_mushroom", hasItem(UndergardenTags.Items.MUSHROOMS))
                .addCriterion("has_gloomgourd", hasItem(UndergardenBlocks.gloomgourd.get()))
                .addCriterion("has_kelp", hasItem(UndergardenItems.glowing_kelp.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(Items.BONE_MEAL, 4)
                .addIngredient(UndergardenItems.brute_tusk.get())
                .addCriterion("has_tusk", hasItem(UndergardenItems.brute_tusk.get()))
                .build(consumer, name("tusk_to_bonemeal"));

        makeShardToIngot().build(consumer, name("shard_to_ingot"));

        makeIngotToBlock(UndergardenBlocks.cloggrum_block, UndergardenItems.cloggrum_ingot).build(consumer);
        makeIngotToBlock(UndergardenBlocks.froststeel_block, UndergardenItems.froststeel_ingot).build(consumer);
        makeIngotToBlock(UndergardenBlocks.utherium_block, UndergardenItems.utherium_ingot).build(consumer);
        makeIngotToBlock(UndergardenBlocks.regalium_block, UndergardenItems.regalium_ingot).build(consumer);
        makeIngotToBlock(UndergardenBlocks.cobbled_depthrock, UndergardenItems.depthrock_pebble).build(consumer);

        makeBlockToIngot(UndergardenItems.cloggrum_ingot, UndergardenBlocks.cloggrum_block).build(consumer, name("cloggrum_block_to_ingot"));
        makeBlockToIngot(UndergardenItems.froststeel_ingot, UndergardenBlocks.froststeel_block).build(consumer, name("froststeel_block_to_ingot"));
        makeBlockToIngot(UndergardenItems.utherium_ingot, UndergardenBlocks.utherium_block).build(consumer, name("utherium_block_to_ingot"));
        makeBlockToIngot(UndergardenItems.regalium_ingot, UndergardenBlocks.regalium_block).build(consumer, name("regalium_block_to_ingot"));
        makeBlockToIngot(UndergardenItems.depthrock_pebble, UndergardenBlocks.cobbled_depthrock).build(consumer);

        makeIngotToNugget(UndergardenItems.cloggrum_nugget, UndergardenItems.cloggrum_ingot).build(consumer, name("cloggrum_ingot_to_nugget"));
        makeIngotToNugget(UndergardenItems.froststeel_nugget, UndergardenItems.froststeel_ingot).build(consumer, name("froststeel_ingot_to_nugget"));
        makeIngotToNugget(UndergardenItems.utherium_chunk, UndergardenItems.utherium_ingot).build(consumer, name("utherium_ingot_to_nugget"));
        makeIngotToNugget(UndergardenItems.regalium_nugget, UndergardenItems.regalium_ingot).build(consumer, name("regalium_ingot_to_nugget"));

        makeNuggetToIngot(UndergardenItems.cloggrum_ingot, UndergardenItems.cloggrum_nugget).build(consumer, name("cloggrum_nugget_to_ingot"));
        makeNuggetToIngot(UndergardenItems.froststeel_ingot, UndergardenItems.froststeel_nugget).build(consumer, name("froststeel_nugget_to_ingot"));
        makeNuggetToIngot(UndergardenItems.utherium_ingot, UndergardenItems.utherium_chunk).build(consumer, name("utherium_chunk_to_ingot"));
        makeNuggetToIngot(UndergardenItems.regalium_ingot, UndergardenItems.regalium_nugget).build(consumer, name("regalium_chunk_to_ingot"));

        makeTagSword(UndergardenItems.smogstem_sword, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, name("smogstem_sword"));
        makeSword(UndergardenItems.cloggrum_sword, UndergardenItems.cloggrum_ingot).build(consumer, name("cloggrum_sword"));
        makeSword(UndergardenItems.froststeel_sword, UndergardenItems.froststeel_ingot).build(consumer, name("froststeel_sword"));
        makeSword(UndergardenItems.utheric_sword, UndergardenItems.utherium_ingot).build(consumer, name("utheric_sword"));

        makeTagPickaxe(UndergardenItems.smogstem_pickaxe, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, name("smogstem_pickaxe"));
        makePickaxe(UndergardenItems.cloggrum_pickaxe, UndergardenItems.cloggrum_ingot).build(consumer, name("cloggrum_pickaxe"));
        makePickaxe(UndergardenItems.froststeel_pickaxe, UndergardenItems.froststeel_ingot).build(consumer, name("froststeel_pickaxe"));
        makePickaxe(UndergardenItems.utheric_pickaxe, UndergardenItems.utherium_ingot).build(consumer, name("utheric_pickaxe"));

        makeTagAxe(UndergardenItems.smogstem_axe, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, name("smogstem_axe"));
        makeAxe(UndergardenItems.cloggrum_axe, UndergardenItems.cloggrum_ingot).build(consumer, name("cloggrum_axe"));
        makeAxe(UndergardenItems.froststeel_axe, UndergardenItems.froststeel_ingot).build(consumer, name("froststeel_axe"));
        makeAxe(UndergardenItems.utheric_axe, UndergardenItems.utherium_ingot).build(consumer, name("utheric_axe"));

        makeTagShovel(UndergardenItems.smogstem_shovel, UndergardenTags.Items.SMOGSTEM_PLANKS).build(consumer, name("smogstem_shovel"));
        makeShovel(UndergardenItems.cloggrum_shovel, UndergardenItems.cloggrum_ingot).build(consumer, name("cloggrum_shovel"));
        makeShovel(UndergardenItems.froststeel_shovel, UndergardenItems.froststeel_ingot).build(consumer, name("froststeel_shovel"));
        makeShovel(UndergardenItems.utheric_shovel, UndergardenItems.utherium_ingot).build(consumer, name("utheric_shovel"));

        makeChestplate(UndergardenItems.masticated_chestplate, UndergardenItems.masticator_scales).build(consumer);

        makeHelmet(UndergardenItems.cloggrum_helmet, UndergardenItems.cloggrum_ingot).build(consumer);
        makeChestplate(UndergardenItems.cloggrum_chestplate, UndergardenItems.cloggrum_ingot).build(consumer);
        makeLeggings(UndergardenItems.cloggrum_leggings, UndergardenItems.cloggrum_ingot).build(consumer);
        makeBoots(UndergardenItems.cloggrum_boots, UndergardenItems.cloggrum_ingot).build(consumer);

        makeHelmet(UndergardenItems.froststeel_helmet, UndergardenItems.froststeel_ingot).build(consumer);
        makeChestplate(UndergardenItems.froststeel_chestplate, UndergardenItems.froststeel_ingot).build(consumer);
        makeLeggings(UndergardenItems.froststeel_leggings, UndergardenItems.froststeel_ingot).build(consumer);
        makeBoots(UndergardenItems.froststeel_boots, UndergardenItems.froststeel_ingot).build(consumer);

        makeHelmet(UndergardenItems.utheric_helmet, UndergardenItems.utherium_ingot).build(consumer);
        makeChestplate(UndergardenItems.utheric_chestplate, UndergardenItems.utherium_ingot).build(consumer);
        makeLeggings(UndergardenItems.utheric_leggings, UndergardenItems.utherium_ingot).build(consumer);
        makeBoots(UndergardenItems.utheric_boots, UndergardenItems.utherium_ingot).build(consumer);

        makeStairs(UndergardenBlocks.depthrock_stairs, UndergardenBlocks.depthrock).build(consumer);
        makeStairs(UndergardenBlocks.cobbled_depthrock_stairs, UndergardenBlocks.cobbled_depthrock).build(consumer);
        makeStairs(UndergardenBlocks.depthrock_brick_stairs, UndergardenBlocks.depthrock_bricks).build(consumer);
        makeStairs(UndergardenBlocks.smogstem_stairs, UndergardenBlocks.smogstem_planks).build(consumer);
        makeStairs(UndergardenBlocks.wigglewood_stairs, UndergardenBlocks.wigglewood_planks).build(consumer);
        makeStairs(UndergardenBlocks.shiverstone_stairs, UndergardenBlocks.shiverstone).build(consumer);
        makeStairs(UndergardenBlocks.cobbled_shiverstone_stairs, UndergardenBlocks.cobbled_shiverstone).build(consumer);
        makeStairs(UndergardenBlocks.shiverstone_brick_stairs, UndergardenBlocks.shiverstone_bricks).build(consumer);

        makeSlab(UndergardenBlocks.depthrock_slab, UndergardenBlocks.depthrock).build(consumer);
        makeSlab(UndergardenBlocks.cobbled_depthrock_slab, UndergardenBlocks.cobbled_depthrock).build(consumer);
        makeSlab(UndergardenBlocks.depthrock_brick_slab, UndergardenBlocks.depthrock_bricks).build(consumer);
        makeSlab(UndergardenBlocks.smogstem_slab, UndergardenBlocks.smogstem_planks).build(consumer);
        makeSlab(UndergardenBlocks.wigglewood_slab, UndergardenBlocks.wigglewood_planks).build(consumer);
        makeSlab(UndergardenBlocks.shiverstone_slab, UndergardenBlocks.shiverstone).build(consumer);
        makeSlab(UndergardenBlocks.cobbled_shiverstone_slab, UndergardenBlocks.cobbled_shiverstone).build(consumer);
        makeSlab(UndergardenBlocks.shiverstone_brick_slab, UndergardenBlocks.shiverstone_bricks).build(consumer);

        makeWall(UndergardenBlocks.cobbled_depthrock_wall, UndergardenBlocks.cobbled_depthrock).build(consumer);
        makeWall(UndergardenBlocks.depthrock_brick_wall, UndergardenBlocks.depthrock_bricks).build(consumer);
        makeWall(UndergardenBlocks.cobbled_shiverstone_wall, UndergardenBlocks.cobbled_shiverstone).build(consumer);
        makeWall(UndergardenBlocks.shiverstone_brick_wall, UndergardenBlocks.shiverstone_bricks).build(consumer);

        makeFence(UndergardenBlocks.smogstem_fence, UndergardenBlocks.smogstem_planks).build(consumer);
        makeFence(UndergardenBlocks.wigglewood_fence, UndergardenBlocks.wigglewood_planks).build(consumer);

        makeDoor(UndergardenBlocks.smogstem_door, UndergardenBlocks.smogstem_planks).build(consumer);
        makeDoor(UndergardenBlocks.wigglewood_door, UndergardenBlocks.wigglewood_planks).build(consumer);

        makeTrapdoor(UndergardenBlocks.smogstem_trapdoor, UndergardenBlocks.smogstem_planks).build(consumer);
        makeTrapdoor(UndergardenBlocks.wigglewood_trapdoor, UndergardenBlocks.wigglewood_planks).build(consumer);

        smeltingRecipe(Items.IRON_INGOT, UndergardenItems.catalyst_item.get(), 1F).build(consumer, "smelt_catalyst");

        smeltingRecipe(UndergardenBlocks.depthrock.get(), UndergardenBlocks.cobbled_depthrock.get(), .1F).build(consumer, name("smelt_depthrock"));
        smeltingRecipe(UndergardenBlocks.shiverstone.get(), UndergardenBlocks.cobbled_shiverstone.get(), .1F).build(consumer, name("smelt_shiverstone"));

        smeltingRecipe(UndergardenItems.cloggrum_ingot.get(), UndergardenBlocks.cloggrum_ore.get(), .7F).build(consumer, name("smelt_cloggrum_ore"));
        blastingRecipe(UndergardenItems.cloggrum_ingot.get(), UndergardenBlocks.cloggrum_ore.get(), .7F).build(consumer, name("blast_cloggrum_ore"));
        smeltingRecipe(UndergardenItems.froststeel_ingot.get(), UndergardenBlocks.froststeel_ore.get(), .7F).build(consumer, name("smelt_froststeel_ore"));
        blastingRecipe(UndergardenItems.froststeel_ingot.get(), UndergardenBlocks.froststeel_ore.get(), .7F).build(consumer, name("blast_froststeel_ore"));
        smeltingRecipe(UndergardenItems.utherium_chunk.get(), UndergardenBlocks.utherium_ore.get(), .9F).build(consumer, name("smelt_utherium_ore"));
        blastingRecipe(UndergardenItems.utherium_chunk.get(), UndergardenBlocks.utherium_ore.get(), .9F).build(consumer, name("blast_utherium_ore"));
        smeltingRecipe(UndergardenItems.regalium_ingot.get(), UndergardenBlocks.regalium_ore.get(), 1F).build(consumer, name("smelt_regalium_ore"));
        blastingRecipe(UndergardenItems.regalium_ingot.get(), UndergardenBlocks.regalium_ore.get(), 1F).build(consumer, name("blast_regalium_ore"));

        smeltingRecipeTag(UndergardenItems.cloggrum_nugget.get(), UndergardenTags.Items.CLOGGRUM_ITEMS, .1F).build(consumer, name("smelt_cloggrum_item"));
        blastingRecipeTag(UndergardenItems.cloggrum_nugget.get(), UndergardenTags.Items.CLOGGRUM_ITEMS, .1F).build(consumer, name("blast_cloggrum_item"));

        smeltingRecipeTag(UndergardenItems.froststeel_nugget.get(), UndergardenTags.Items.FROSTSTEEL_ITEMS, .1F).build(consumer, name("smelt_froststeel_item"));
        blastingRecipeTag(UndergardenItems.froststeel_nugget.get(), UndergardenTags.Items.FROSTSTEEL_ITEMS, .1F).build(consumer, name("blast_froststeel_item"));

        smeltingRecipeTag(UndergardenItems.utheric_shard.get(), UndergardenTags.Items.UTHERIUM_ITEMS, .1F).build(consumer, name("smelt_utherium_item"));
        blastingRecipeTag(UndergardenItems.utheric_shard.get(), UndergardenTags.Items.UTHERIUM_ITEMS, .1F).build(consumer, name("blast_utherium_item"));

        smeltingRecipe(UndergardenItems.dweller_steak.get(), UndergardenItems.raw_dweller_meat.get(), .35F).build(consumer, name("smelt_dweller_meat"));
        smokingRecipe(UndergardenItems.dweller_steak.get(), UndergardenItems.raw_dweller_meat.get(), .35F).build(consumer, name("smoke_dweller_meat"));

        smeltingRecipe(UndergardenItems.cooked_gwibling.get(), UndergardenItems.raw_gwibling.get(), .35F).build(consumer, name("smelt_gwibling"));
        smokingRecipe(UndergardenItems.cooked_gwibling.get(), UndergardenItems.raw_gwibling.get(), .35F).build(consumer, name("smoke_gwibling"));
    }

    private ResourceLocation name(String name) {
        return new ResourceLocation(UndergardenMod.MODID, name);
    }
}
