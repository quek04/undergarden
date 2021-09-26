package quek.undergarden.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.world.item.Item.Properties;

public class UGItem extends Item {

    public UGItem() {
        super(new Properties()
                .tab(UGItemGroups.GROUP)
        );
    }

    public UGItem(Rarity rarity) {
        super(new Properties()
                .tab(UGItemGroups.GROUP)
                .rarity(rarity)
        );
    }

    public UGItem(FoodProperties food) {
        super(new Properties()
                .food(food)
                .tab(UGItemGroups.GROUP));
    }
}