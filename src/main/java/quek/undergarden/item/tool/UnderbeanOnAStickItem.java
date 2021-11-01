package quek.undergarden.item.tool;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGEntityTypes;

public class UnderbeanOnAStickItem<T extends Entity & ItemSteerable> extends FoodOnAStickItem<T> {

    public UnderbeanOnAStickItem(Properties properties, EntityType<T> entity) {
        super(properties, entity, 1);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            Entity entity = player.getVehicle();
            if (player.isPassenger() && entity instanceof ItemSteerable && entity.getType() == UGEntityTypes.DWELLER.get()) {
                ItemSteerable irideable = (ItemSteerable) entity;
                if (irideable.boost()) {
                    itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                    if (itemstack.isEmpty()) {
                        ItemStack stick = new ItemStack(Items.STICK);
                        stick.setTag(itemstack.getTag());
                        return InteractionResultHolder.success(stick);
                    }

                    return InteractionResultHolder.success(itemstack);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.pass(itemstack);
    }
}