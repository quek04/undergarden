package quek.undergarden.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

public class UGSpawnEggItem extends SpawnEggItem {
    public UGSpawnEggItem(EntityType<?> entity, int egg, int spots) {
        super(entity, egg, spots, new Properties()
                .group(UGItemGroups.GROUP)
        );
    }
}