package quek.undergarden.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import quek.undergarden.registry.UGCreativeModeTabs;

public class UGItem extends Item {

    public UGItem() {
        super(new Properties()
                .tab(UGCreativeModeTabs.GROUP)
        );
    }

    public UGItem(Rarity rarity) {
        super(new Properties()
                .tab(UGCreativeModeTabs.GROUP)
                .rarity(rarity)
        );
    }

    public UGItem(FoodProperties food) {
        super(new Properties()
                .food(food)
                .tab(UGCreativeModeTabs.GROUP));
    }
}