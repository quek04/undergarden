package quek.undergarden.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quek.undergarden.entity.projectile.SlingshotAmmoEntity;
import quek.undergarden.registry.UndergardenItemGroups;

public class DepthrockPebbleItem extends ArrowItem {

    public DepthrockPebbleItem() {
        super(new Properties()
                .group(UndergardenItemGroups.UNDERGARDEN_ITEMS)
        );
    }

}
