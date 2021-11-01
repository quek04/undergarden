package quek.undergarden.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeSpawnEggItem;
import quek.undergarden.registry.UGItemGroups;

public class UGSpawnEggItem extends ForgeSpawnEggItem {

    //public static final Map<EntityType<?>, UGSpawnEggItem> EGGS = Maps.newIdentityHashMap();

    public UGSpawnEggItem(EntityType<? extends Mob> entity, int egg, int spots) {
        super(() -> entity, egg, spots, new Properties()
                .tab(UGItemGroups.GROUP)
        );
        //EGGS.put(entity, this);
    }

    //public static Iterable<UGSpawnEggItem> UGEggs() {
    //    return Iterables.unmodifiableIterable(EGGS.values());
    //}
}