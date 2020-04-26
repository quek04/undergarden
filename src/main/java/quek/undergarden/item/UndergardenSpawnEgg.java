package quek.undergarden.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import quek.undergarden.registry.UndergardenItemGroups;

public class UndergardenSpawnEgg extends SpawnEggItem {
    public UndergardenSpawnEgg(EntityType<?> entity, int egg, int spots) {
        super(entity, egg, spots, new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_ITEMS)
        );
    }
}
