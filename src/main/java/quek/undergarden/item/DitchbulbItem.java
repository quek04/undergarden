package quek.undergarden.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItemGroups;

import javax.annotation.Nullable;

public class DitchbulbItem extends ItemNameBlockItem {

    public DitchbulbItem() {
        super(UGBlocks.DITCHBULB_PLANT.get(), new Properties()
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 800;
    }
}