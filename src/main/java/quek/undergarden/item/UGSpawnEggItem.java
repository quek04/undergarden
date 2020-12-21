package quek.undergarden.item;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

import java.util.Map;

public class UGSpawnEggItem extends SpawnEggItem {

    public static final Map<EntityType<?>, UGSpawnEggItem> EGGS = Maps.newIdentityHashMap();

    public UGSpawnEggItem(EntityType<?> entity, int egg, int spots) {
        super(entity, egg, spots, new Properties()
                .group(UGItemGroups.GROUP)
        );
        EGGS.put(entity, this);
    }

    public static Iterable<UGSpawnEggItem> eggs() {
        return Iterables.unmodifiableIterable(EGGS.values());
    }
}