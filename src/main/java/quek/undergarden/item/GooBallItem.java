package quek.undergarden.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quek.undergarden.entity.projectile.GooBallEntity;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

public class GooBallItem extends Item {

    public GooBallItem() {
        super(new Properties()
                .food(UGFoods.GOO_BALL)
                .stacksTo(16)
                .tab(UGItemGroups.GROUP)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.isCrouching()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
        else {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this, 20);
            if (!level.isClientSide) {
                GooBallEntity gooBall = new GooBallEntity(level, player);
                gooBall.setItem(itemstack);
                gooBall.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(gooBall);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            return InteractionResultHolder.success(itemstack);
        }
    }
}