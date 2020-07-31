package quek.undergarden.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenItem extends Item {

    public UndergardenItem() {
        super(new Properties()
                .group(UndergardenItemGroups.GROUP));
    }

    public UndergardenItem(Rarity rarity) {
        super(new Properties()
                .group(UndergardenItemGroups.GROUP)
                .rarity(rarity)
        );
    }

    public UndergardenItem(Food food) {
        super(new Properties()
                .food(food)
                .group(UndergardenItemGroups.GROUP));
    }
}
