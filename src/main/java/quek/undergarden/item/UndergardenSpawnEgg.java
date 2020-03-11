package quek.undergarden.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.*;

public class UndergardenSpawnEgg extends SpawnEggItem {
    public UndergardenSpawnEgg(EntityType<?> entity, int egg, int spots) {
        super(entity, egg, spots, new Properties()
                .group(ItemGroup.MISC)
        );
    }
}
