package quek.undergarden.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.OnAStickItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import quek.undergarden.registry.UGEntityTypes;

public class UnderbeanOnAStickItem<T extends Entity & IRideable> extends OnAStickItem<T> {

    public UnderbeanOnAStickItem(Properties properties, EntityType<T> entity) {
        super(properties, entity, 1);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            Entity entity = player.getVehicle();
            if (player.isPassenger() && entity instanceof IRideable && entity.getType() == UGEntityTypes.DWELLER.get()) {
                IRideable irideable = (IRideable) entity;
                if (irideable.boost()) {
                    itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                    if (itemstack.isEmpty()) {
                        ItemStack stick = new ItemStack(Items.STICK);
                        stick.setTag(itemstack.getTag());
                        return ActionResult.success(stick);
                    }

                    return ActionResult.success(itemstack);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return ActionResult.pass(itemstack);
    }

}