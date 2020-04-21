package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import quek.undergarden.data.provider.UndergardenItemModelProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenItems;

public class UndergardenItemModels extends UndergardenItemModelProvider {

    public UndergardenItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Item Models";
    }

    @Override
    protected void registerModels() {
        itemBlock(UndergardenBlocks.undergarden_portal_frame);
        itemBlock(UndergardenBlocks.depthrock);
        itemBlock(UndergardenBlocks.cobbled_depthrock);
        itemBlock(UndergardenBlocks.coal_ore);
        itemBlock(UndergardenBlocks.cloggrum_ore);
        itemBlock(UndergardenBlocks.froststeel_ore);
        itemBlock(UndergardenBlocks.utherium_ore);
        itemBlock(UndergardenBlocks.deepturf_block);
        itemBlock(UndergardenBlocks.deepsoil);
        itemBlockFlat(UndergardenBlocks.smogstem_sapling);
        itemBlock(UndergardenBlocks.smogstem_log);
        itemBlock(UndergardenBlocks.smogstem_leaves);
        itemBlockFlat(UndergardenBlocks.wigglewood_sapling);
        itemBlock(UndergardenBlocks.wigglewood_log);
        itemBlock(UndergardenBlocks.wigglewood_leaves);
        itemBlock(UndergardenBlocks.smogstem_planks);
        itemBlock(UndergardenBlocks.wigglewood_planks);
        itemBlock(UndergardenBlocks.depthrock_bricks);
        itemBlock(UndergardenBlocks.cracked_depthrock_bricks);
        itemBlockFlat(UndergardenBlocks.tall_deepturf);
        itemBlockFlat(UndergardenBlocks.shimmerweed);
        itemBlockFlat(UndergardenBlocks.indigo_mushroom);
        itemBlockFlat(UndergardenBlocks.veil_mushroom);
        itemBlockFlat(UndergardenBlocks.ink_mushroom);
        itemBlockFlat(UndergardenBlocks.blood_mushroom);

        normalItem(UndergardenItems.undergarden_portal_catalyst);
        normalItem(UndergardenItems.smogstem_stick);
        normalItem(UndergardenItems.cloggrum_ingot);
        normalItem(UndergardenItems.cloggrum_nugget);
        normalItem(UndergardenItems.froststeel_ingot);
        normalItem(UndergardenItems.froststeel_nugget);
        normalItem(UndergardenItems.utheric_shard);
        normalItem(UndergardenItems.utherium_ingot);
        normalItem(UndergardenItems.utherium_chunk);
        normalItem(UndergardenItems.smogstem_torch);

        toolItem(UndergardenItems.smogstem_sword);
        toolItem(UndergardenItems.smogstem_pickaxe);
        toolItem(UndergardenItems.smogstem_axe);
        toolItem(UndergardenItems.smogstem_shovel);

        toolItem(UndergardenItems.cloggrum_sword);
        toolItem(UndergardenItems.cloggrum_pickaxe);
        toolItem(UndergardenItems.cloggrum_axe);
        toolItem(UndergardenItems.cloggrum_shovel);

        normalItem(UndergardenItems.cloggrum_helmet);
        normalItem(UndergardenItems.cloggrum_chestplate);
        normalItem(UndergardenItems.cloggrum_leggings);
        normalItem(UndergardenItems.cloggrum_boots);

        toolItem(UndergardenItems.froststeel_sword);
        toolItem(UndergardenItems.froststeel_pickaxe);
        toolItem(UndergardenItems.froststeel_axe);
        toolItem(UndergardenItems.froststeel_shovel);

        toolItem(UndergardenItems.utheric_sword);
        toolItem(UndergardenItems.utheric_pickaxe);
        toolItem(UndergardenItems.utheric_axe);
        toolItem(UndergardenItems.utheric_shovel);

        normalItem(UndergardenItems.underbeans);
        normalItem(UndergardenItems.raw_dweller_meat);
        normalItem(UndergardenItems.dweller_steak);
    }


}
