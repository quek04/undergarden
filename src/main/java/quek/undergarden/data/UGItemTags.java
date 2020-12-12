package quek.undergarden.data;

import net.minecraft.data.BlockTagsProvider;
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

    public UGItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, UGMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Tags";
    }

    @Override
    protected void registerTags() {
        //undergarden
        copy(UGTags.Blocks.MUSHROOMS, UGTags.Items.MUSHROOMS);
        tag(UGTags.Items.CLOGGRUM_ITEMS).add(UGItems.CLOGGRUM_SWORD.get(), UGItems.CLOGGRUM_PICKAXE.get(), UGItems.CLOGGRUM_AXE.get(), UGItems.CLOGGRUM_SHOVEL.get(), UGItems.CLOGGRUM_HOE.get(), UGItems.CLOGGRUM_HELMET.get(), UGItems.CLOGGRUM_CHESTPLATE.get(), UGItems.CLOGGRUM_LEGGINGS.get(), UGItems.CLOGGRUM_BOOTS.get());
        tag(UGTags.Items.FROSTSTEEL_ITEMS).add(UGItems.FROSTSTEEL_SWORD.get(), UGItems.FROSTSTEEL_PICKAXE.get(), UGItems.FROSTSTEEL_AXE.get(), UGItems.FROSTSTEEL_SHOVEL.get(), UGItems.FROSTSTEEL_HOE.get(), UGItems.FROSTSTEEL_HELMET.get(), UGItems.FROSTSTEEL_CHESTPLATE.get(), UGItems.FROSTSTEEL_LEGGINGS.get(), UGItems.FROSTSTEEL_BOOTS.get());
        tag(UGTags.Items.UTHERIUM_ITEMS).add(UGItems.UTHERIC_SWORD.get(), UGItems.UTHERIC_PICKAXE.get(), UGItems.UTHERIC_AXE.get(), UGItems.UTHERIC_SHOVEL.get(), UGItems.UTHERIC_HOE.get(), UGItems.UTHERIC_HELMET.get(), UGItems.UTHERIC_CHESTPLATE.get(), UGItems.UTHERIC_LEGGINGS.get(), UGItems.UTHERIC_BOOTS.get());
        copy(UGTags.Blocks.SMOGSTEM_LOGS, UGTags.Items.SMOGSTEM_LOGS);
        copy(UGTags.Blocks.WIGGLEWOOD_LOGS, UGTags.Items.WIGGLEWOOD_LOGS);
        copy(UGTags.Blocks.GRONGLE_STEMS, UGTags.Items.GRONGLE_STEMS);

        //vanilla
        tag(ItemTags.COALS).add(UGItems.DITCHBULB.get());
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(UGItems.CLOGGRUM_INGOT.get(), UGItems.FROSTSTEEL_INGOT.get(), UGItems.UTHERIUM_INGOT.get(), UGItems.REGALIUM_INGOT.get(), UGItems.FORGOTTEN_INGOT.get());
        tag(ItemTags.NON_FLAMMABLE_WOOD).addTag(UGTags.Items.GRONGLE_STEMS).add(UGBlocks.GRONGLE_PLANKS.get().asItem(), UGBlocks.GRONGLE_SLAB.get().asItem(), UGBlocks.GRONGLE_PRESSURE_PLATE.get().asItem(), UGBlocks.GRONGLE_FENCE.get().asItem(), UGBlocks.GRONGLE_TRAPDOOR.get().asItem(), UGBlocks.GRONGLE_FENCE_GATE.get().asItem(), UGBlocks.GRONGLE_STAIRS.get().asItem(), UGBlocks.GRONGLE_BUTTON.get().asItem(), UGBlocks.GRONGLE_DOOR.get().asItem()); //TODO grongle sign
        tag(ItemTags.STONE_TOOL_MATERIALS).add(UGBlocks.DEPTHROCK.get().asItem(), UGBlocks.SHIVERSTONE.get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(UGBlocks.DEPTHROCK.get().asItem(), UGBlocks.SHIVERSTONE.get().asItem());

        //forge
        tag(Tags.Items.BONES).add(UGItems.BRUTE_TUSK.get());
        tag(Tags.Items.INGOTS).add(UGItems.CLOGGRUM_INGOT.get(), UGItems.FROSTSTEEL_INGOT.get(), UGItems.UTHERIUM_INGOT.get(), UGItems.REGALIUM_INGOT.get(), UGItems.FORGOTTEN_INGOT.get());
        tag(Tags.Items.MUSHROOMS).addTag(UGTags.Items.MUSHROOMS);
        tag(Tags.Items.NUGGETS).add(UGItems.CLOGGRUM_NUGGET.get(), UGItems.FROSTSTEEL_NUGGET.get(), UGItems.UTHERIUM_CHUNK.get(), UGItems.REGALIUM_NUGGET.get(), UGItems.FORGOTTEN_NUGGET.get());
        tag(Tags.Items.RODS_WOODEN).add(UGItems.TWISTYTWIG.get());
        tag(Tags.Items.SEEDS).add(UGItems.GLOOMGOURD_SEEDS.get());
        tag(Tags.Items.SLIMEBALLS).add(UGItems.GOO_BALL.get());
        tag(Tags.Items.STRING).add(UGItems.TWISTYTWIG.get());
    }

    private TagsProvider.Builder<Item> tag(ITag.INamedTag<Item> tag) {
        return this.getOrCreateBuilder(tag);
    }
}
