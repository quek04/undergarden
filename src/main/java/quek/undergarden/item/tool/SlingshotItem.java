package quek.undergarden.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.item.SlingshotAmmoItem;
import quek.undergarden.registry.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class SlingshotItem extends ProjectileWeaponItem {

    public SlingshotItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(192)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.slingshot").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(UGTags.Items.SLINGSHOT_AMMO);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            boolean isCreative = player.getAbilities().instabuild;
            ItemStack projectileStack = player.getProjectile(stack);

            int useTime = getUseDuration(stack) - timeLeft;
            useTime = onArrowLoose(stack, level, player, useTime, !projectileStack.isEmpty() || isCreative);
            if (useTime < 0) return;

            if (!projectileStack.isEmpty() || isCreative) {
                if (projectileStack.isEmpty()) {
                    projectileStack = new ItemStack(UGItems.DEPTHROCK_PEBBLE.get());
                }

                float velocity = getProjectileVelocity(useTime);
                if (!((double) velocity < 0.1D)) {
                    if (!level.isClientSide) {
                        SlingshotAmmoItem ammoItem = (SlingshotAmmoItem) projectileStack.getItem();
                        SlingshotProjectile slingshotProjectile = ammoItem.createProjectile(level, player);

                        slingshotProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity * 2.0F, 1.0F);

                        stack.hurtAndBreak(1, player, (player1) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                        if (EnchantmentHelper.getItemEnchantmentLevel(UGEnchantments.RICOCHET.get(), stack) > 0) {
                            slingshotProjectile.setRicochet(true);
                        }

                        level.addFreshEntity(slingshotProjectile);
                    }

                    level.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_SHOOT.get(), SoundSource.PLAYERS, 0.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
                    if (!isCreative) {
                        projectileStack.shrink(1);
                        if (projectileStack.isEmpty()) {
                            player.getInventory().removeItem(projectileStack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean hasAmmo = !player.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = onArrowNock(itemstack, level, player, hand, hasAmmo);
        if (ret != null) return ret;

        if (!player.getAbilities().instabuild && !hasAmmo) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_DRAW.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public static InteractionResultHolder<ItemStack> onArrowNock(ItemStack stack, Level level, Player player, InteractionHand hand, boolean hasAmmo) {
        ArrowNockEvent event = new ArrowNockEvent(player, stack, hand, level, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
        return event.getAction();
    }

    public static int onArrowLoose(ItemStack stack, Level level, Player player, int charge, boolean hasAmmo) {
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, level, charge, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return -1;
        return event.getCharge();
    }

    public static float getProjectileVelocity(int charge) {
        float f = (float) charge / 5.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ItemTags.PLANKS);
    }
}