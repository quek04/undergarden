package quek.undergarden.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import quek.undergarden.registry.UndergardenItemGroups;

import net.minecraft.item.Item.Properties;

public class UndergardenSpawnEggItem extends SpawnEggItem {
    public UndergardenSpawnEggItem(EntityType<?> entity, int egg, int spots) {
        super(entity, egg, spots, new Properties()
                .group(UndergardenItemGroups.GROUP)
        );
    }
}
