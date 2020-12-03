package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.UGMod;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;

public class UGItemTags extends ItemTagsProvider {

    public UGItemTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, new UGBlockTags(dataGenerator, existingFileHelper), UGMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Tags";
    }

    @Override
    protected void registerTags() {
        //undergarden
        copy(UGTags.Blocks.MUSHROOMS, UGTags.Items.MUSHROOMS);
        tag(UGTags.Items.CLOGGRUM_ITEMS).add(UGItems.cloggrum_sword.get(), UGItems.cloggrum_pickaxe.get(), UGItems.cloggrum_axe.get(), UGItems.cloggrum_shovel.get(), UGItems.cloggrum_hoe.get(), UGItems.cloggrum_helmet.get(), UGItems.cloggrum_chestplate.get(), UGItems.cloggrum_leggings.get(), UGItems.cloggrum_boots.get());
        tag(UGTags.Items.FROSTSTEEL_ITEMS).add(UGItems.froststeel_sword.get(), UGItems.froststeel_pickaxe.get(), UGItems.froststeel_axe.get(), UGItems.froststeel_shovel.get(), UGItems.froststeel_hoe.get(), UGItems.froststeel_helmet.get(), UGItems.froststeel_chestplate.get(), UGItems.froststeel_leggings.get(), UGItems.froststeel_boots.get());
        tag(UGTags.Items.UTHERIUM_ITEMS).add(UGItems.utheric_sword.get(), UGItems.utheric_pickaxe.get(), UGItems.utheric_axe.get(), UGItems.utheric_shovel.get(), UGItems.utheric_hoe.get(), UGItems.utheric_helmet.get(), UGItems.utheric_chestplate.get(), UGItems.utheric_leggings.get(), UGItems.utheric_boots.get());
        copy(UGTags.Blocks.SMOGSTEM_LOGS, UGTags.Items.SMOGSTEM_LOGS);
        copy(UGTags.Blocks.WIGGLEWOOD_LOGS, UGTags.Items.WIGGLEWOOD_LOGS);
        copy(UGTags.Blocks.GRONGLE_STEMS, UGTags.Items.GRONGLE_STEMS);

        //vanilla
        tag(ItemTags.COALS).add(UGItems.ditchbulb.get());
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(UGItems.cloggrum_ingot.get(), UGItems.froststeel_ingot.get(), UGItems.utherium_ingot.get(), UGItems.regalium_ingot.get(), UGItems.forgotten_ingot.get());
        tag(ItemTags.NON_FLAMMABLE_WOOD).addTag(UGTags.Items.GRONGLE_STEMS).add(UGBlocks.grongle_planks.get().asItem(), UGBlocks.grongle_slab.get().asItem(), UGBlocks.grongle_pressure_plate.get().asItem(), UGBlocks.grongle_fence.get().asItem(), UGBlocks.grongle_trapdoor.get().asItem(), UGBlocks.grongle_fence_gate.get().asItem(), UGBlocks.grongle_stairs.get().asItem(), UGBlocks.grongle_button.get().asItem(), UGBlocks.grongle_door.get().asItem()); //TODO grongle sign
        tag(ItemTags.STONE_TOOL_MATERIALS).add(UGBlocks.depthrock.get().asItem(), UGBlocks.shiverstone.get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(UGBlocks.depthrock.get().asItem(), UGBlocks.shiverstone.get().asItem());

        //forge
        tag(Tags.Items.BONES).add(UGItems.brute_tusk.get());
        tag(Tags.Items.INGOTS).add(UGItems.cloggrum_ingot.get(), UGItems.froststeel_ingot.get(), UGItems.utherium_ingot.get(), UGItems.regalium_ingot.get(), UGItems.forgotten_ingot.get());
        tag(Tags.Items.MUSHROOMS).addTag(UGTags.Items.MUSHROOMS);
        tag(Tags.Items.NUGGETS).add(UGItems.cloggrum_nugget.get(), UGItems.froststeel_nugget.get(), UGItems.utherium_chunk.get(), UGItems.regalium_nugget.get(), UGItems.forgotten_nugget.get());
        tag(Tags.Items.RODS_WOODEN).add(UGItems.twistytwig.get());
        tag(Tags.Items.SEEDS).add(UGItems.gloomgourd_seeds.get());
        tag(Tags.Items.SLIMEBALLS).add(UGItems.goo_ball.get());
        tag(Tags.Items.STRING).add(UGItems.twistytwig.get());
    }

    private TagsProvider.Builder<Item> tag(ITag.INamedTag<Item> tag) {
        return this.getOrCreateBuilder(tag);
    }
}
