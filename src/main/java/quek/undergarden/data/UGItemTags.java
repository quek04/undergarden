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
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;

public class UGItemTags extends ItemTagsProvider {

    public UGItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, Undergarden.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Tags";
    }

    @Override
    protected void addTags() {
        //undergarden
        copy(UGTags.Blocks.MUSHROOMS, UGTags.Items.MUSHROOMS);
        tag(UGTags.Items.CLOGGRUM_ITEMS).add(UGItems.CLOGGRUM_SWORD.get(), UGItems.CLOGGRUM_PICKAXE.get(), UGItems.CLOGGRUM_AXE.get(), UGItems.CLOGGRUM_SHOVEL.get(), UGItems.CLOGGRUM_HOE.get(), UGItems.CLOGGRUM_HELMET.get(), UGItems.CLOGGRUM_CHESTPLATE.get(), UGItems.CLOGGRUM_LEGGINGS.get(), UGItems.CLOGGRUM_BOOTS.get());
        tag(UGTags.Items.FROSTSTEEL_ITEMS).add(UGItems.FROSTSTEEL_SWORD.get(), UGItems.FROSTSTEEL_PICKAXE.get(), UGItems.FROSTSTEEL_AXE.get(), UGItems.FROSTSTEEL_SHOVEL.get(), UGItems.FROSTSTEEL_HOE.get(), UGItems.FROSTSTEEL_HELMET.get(), UGItems.FROSTSTEEL_CHESTPLATE.get(), UGItems.FROSTSTEEL_LEGGINGS.get(), UGItems.FROSTSTEEL_BOOTS.get());
        tag(UGTags.Items.UTHERIUM_ITEMS).add(UGItems.UTHERIUM_SWORD.get(), UGItems.UTHERIUM_PICKAXE.get(), UGItems.UTHERIUM_AXE.get(), UGItems.UTHERIUM_SHOVEL.get(), UGItems.UTHERIUM_HOE.get(), UGItems.UTHERIUM_HELMET.get(), UGItems.UTHERIUM_CHESTPLATE.get(), UGItems.UTHERIUM_LEGGINGS.get(), UGItems.UTHERIUM_BOOTS.get());
        copy(UGTags.Blocks.SMOGSTEM_LOGS, UGTags.Items.SMOGSTEM_LOGS);
        copy(UGTags.Blocks.WIGGLEWOOD_LOGS, UGTags.Items.WIGGLEWOOD_LOGS);
        copy(UGTags.Blocks.GRONGLE_LOGS, UGTags.Items.GRONGLE_LOGS);

        //vanilla
        tag(ItemTags.PLANKS).add(UGBlocks.SMOGSTEM_PLANKS.get().asItem(), UGBlocks.WIGGLEWOOD_PLANKS.get().asItem(), UGBlocks.GRONGLE_PLANKS.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS).add(UGBlocks.SMOGSTEM_BUTTON.get().asItem(), UGBlocks.WIGGLEWOOD_BUTTON.get().asItem(), UGBlocks.GRONGLE_BUTTON.get().asItem());
        tag(ItemTags.BUTTONS).add(UGBlocks.DEPTHROCK_BUTTON.get().asItem(), UGBlocks.SHIVERSTONE_BUTTON.get().asItem());
        tag(ItemTags.WOODEN_DOORS).add(UGBlocks.SMOGSTEM_DOOR.get().asItem(), UGBlocks.WIGGLEWOOD_DOOR.get().asItem(), UGBlocks.GRONGLE_DOOR.get().asItem());
        tag(ItemTags.WOODEN_STAIRS).add(UGBlocks.SMOGSTEM_STAIRS.get().asItem(), UGBlocks.WIGGLEWOOD_STAIRS.get().asItem(), UGBlocks.GRONGLE_STAIRS.get().asItem());
        tag(ItemTags.WOODEN_SLABS).add(UGBlocks.SMOGSTEM_SLAB.get().asItem(), UGBlocks.WIGGLEWOOD_SLAB.get().asItem(), UGBlocks.GRONGLE_SLAB.get().asItem());
        tag(ItemTags.WOODEN_FENCES).add(UGBlocks.SMOGSTEM_FENCE.get().asItem(), UGBlocks.WIGGLEWOOD_FENCE.get().asItem(), UGBlocks.GRONGLE_FENCE.get().asItem());
        tag(ItemTags.SAPLINGS).add(UGBlocks.SMOGSTEM_SAPLING.get().asItem(), UGBlocks.WIGGLEWOOD_SAPLING.get().asItem(), UGBlocks.GRONGLE_SAPLING.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).addTag(UGTags.Items.SMOGSTEM_LOGS).addTag(UGTags.Items.WIGGLEWOOD_LOGS).addTag(UGTags.Items.GRONGLE_LOGS);
        tag(ItemTags.LOGS).addTag(UGTags.Items.SMOGSTEM_LOGS).addTag(UGTags.Items.WIGGLEWOOD_LOGS).addTag(UGTags.Items.GRONGLE_LOGS);
        tag(ItemTags.SMALL_FLOWERS).add(UGBlocks.SHIMMERWEED.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get().asItem(), UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get().asItem(), UGBlocks.GRONGLE_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WALLS).add(UGBlocks.DEPTHROCK_WALL.get().asItem(), UGBlocks.DEPTHROCK_BRICK_WALL.get().asItem(), UGBlocks.SHIVERSTONE_WALL.get().asItem(), UGBlocks.SHIVERSTONE_BRICK_WALL.get().asItem(), UGBlocks.TREMBLECRUST_WALL.get().asItem(), UGBlocks.TREMBLECRUST_BRICK_WALL.get().asItem());
        tag(ItemTags.LEAVES).add(UGBlocks.SMOGSTEM_LEAVES.get().asItem(), UGBlocks.WIGGLEWOOD_LEAVES.get().asItem(), UGBlocks.GRONGLE_LEAVES.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS).add(UGBlocks.SMOGSTEM_TRAPDOOR.get().asItem(), UGBlocks.WIGGLEWOOD_TRAPDOOR.get().asItem(), UGBlocks.GRONGLE_TRAPDOOR.get().asItem());
        tag(ItemTags.SIGNS).add(UGItems.SMOGSTEM_SIGN.get(), UGItems.WIGGLEWOOD_SIGN.get(), UGItems.GRONGLE_SIGN.get());
        tag(ItemTags.TALL_FLOWERS).add(UGBlocks.TALL_SHIMMERWEED.get().asItem());
        tag(ItemTags.GOLD_ORES).add(UGBlocks.GOLD_ORE.get().asItem());
        tag(ItemTags.COALS).add(UGItems.DITCHBULB.get());
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(UGItems.CLOGGRUM_INGOT.get(), UGItems.FROSTSTEEL_INGOT.get(), UGItems.UTHERIUM_INGOT.get(), UGItems.REGALIUM_INGOT.get(), UGItems.FORGOTTEN_INGOT.get());
        tag(ItemTags.STONE_TOOL_MATERIALS).add(UGBlocks.DEPTHROCK.get().asItem(), UGBlocks.SHIVERSTONE.get().asItem(), UGBlocks.TREMBLECRUST.get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(UGBlocks.DEPTHROCK.get().asItem(), UGBlocks.SHIVERSTONE.get().asItem(), UGBlocks.TREMBLECRUST.get().asItem());
        tag(ItemTags.MUSIC_DISCS).add(UGItems.MAMMOTH_DISC.get(), UGItems.LIMAX_MAXIMUS_DISC.get(), UGItems.RELICT_DISC.get(), UGItems.GLOOMPER_ANTHEM_DISC.get(), UGItems.GLOOMPER_SECRET_DISC.get());
        tag(ItemTags.FISHES).add(UGItems.RAW_GWIBLING.get(), UGItems.COOKED_GWIBLING.get());
        tag(ItemTags.BOATS).add(UGItems.SMOGSTEM_BOAT.get(), UGItems.WIGGLEWOOD_BOAT.get(), UGItems.GRONGLE_BOAT.get());

        //forge
        tag(Tags.Items.BONES).add(UGItems.BRUTE_TUSK.get());
        tag(Tags.Items.COBBLESTONE).add(UGBlocks.DEPTHROCK.get().asItem(), UGBlocks.SHIVERSTONE.get().asItem(), UGBlocks.TREMBLECRUST.get().asItem());
        tag(Tags.Items.FENCE_GATES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE_GATE.get().asItem(), UGBlocks.WIGGLEWOOD_FENCE_GATE.get().asItem(), UGBlocks.GRONGLE_FENCE_GATE.get().asItem());
        tag(Tags.Items.FENCES_WOODEN).add(UGBlocks.SMOGSTEM_FENCE.get().asItem(), UGBlocks.WIGGLEWOOD_FENCE.get().asItem(), UGBlocks.GRONGLE_FENCE.get().asItem());
        tag(Tags.Items.INGOTS).add(UGItems.CLOGGRUM_INGOT.get(), UGItems.FROSTSTEEL_INGOT.get(), UGItems.UTHERIUM_INGOT.get(), UGItems.REGALIUM_INGOT.get(), UGItems.FORGOTTEN_INGOT.get());
        tag(Tags.Items.MUSHROOMS).addTag(UGTags.Items.MUSHROOMS);
        tag(Tags.Items.NUGGETS).add(UGItems.CLOGGRUM_NUGGET.get(), UGItems.FROSTSTEEL_NUGGET.get(), UGItems.UTHERIUM_CHUNK.get(), UGItems.REGALIUM_NUGGET.get(), UGItems.FORGOTTEN_NUGGET.get());
        tag(Tags.Items.RODS_WOODEN).add(UGItems.TWISTYTWIG.get());
        tag(Tags.Items.SEEDS).add(UGItems.GLOOMGOURD_SEEDS.get());
        tag(Tags.Items.SLIMEBALLS).add(UGItems.GOO_BALL.get());
        tag(Tags.Items.STRING).add(UGItems.TWISTYTWIG.get());
        tag(Tags.Items.ORES).add(UGBlocks.CLOGGRUM_ORE.get().asItem(), UGBlocks.FROSTSTEEL_ORE.get().asItem(), UGBlocks.UTHERIUM_ORE.get().asItem(), UGBlocks.REGALIUM_ORE.get().asItem(), UGBlocks.OTHERSIDE_UTHERIUM_ORE.get().asItem());
        tag(Tags.Items.ORES_COAL).add(UGBlocks.COAL_ORE.get().asItem());
        tag(Tags.Items.ORES_DIAMOND).add(UGBlocks.DIAMOND_ORE.get().asItem());
        tag(Tags.Items.ORES_IRON).add(UGBlocks.IRON_ORE.get().asItem());
        tag(Tags.Items.STONE).add(UGBlocks.DEPTHROCK.get().asItem(), UGBlocks.SHIVERSTONE.get().asItem(), UGBlocks.TREMBLECRUST.get().asItem());
        tag(Tags.Items.STORAGE_BLOCKS).add(UGBlocks.CLOGGRUM_BLOCK.get().asItem(), UGBlocks.FROSTSTEEL_BLOCK.get().asItem(), UGBlocks.UTHERIUM_BLOCK.get().asItem(), UGBlocks.REGALIUM_BLOCK.get().asItem(), UGBlocks.FORGOTTEN_BLOCK.get().asItem());
        tag(Tags.Items.GLASS).add(UGBlocks.SEDIMENT_GLASS.get().asItem());
        tag(Tags.Items.GLASS_COLORLESS).add(UGBlocks.SEDIMENT_GLASS.get().asItem());
        tag(Tags.Items.GLASS_PANES).add(UGBlocks.SEDIMENT_GLASS_PANE.get().asItem());
        tag(Tags.Items.GLASS_PANES_COLORLESS).add(UGBlocks.SEDIMENT_GLASS_PANE.get().asItem());
    }

    protected TagsProvider.Builder<Item> tag(ITag.INamedTag<Item> tag) {
        return super.tag(tag);
    }
}