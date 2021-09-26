package quek.undergarden.item.tool;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import quek.undergarden.entity.projectile.SlingshotAmmoEntity;
import quek.undergarden.item.DepthrockPebbleItem;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.world.item.Item.Properties;

public class SlingshotItem extends ProjectileWeaponItem {

    public SlingshotItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(192)
                .tab(UGItemGroups.GROUP)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.slingshot").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == UGItems.DEPTHROCK_PEBBLE.get();
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player) {
            Player player = (Player)entityLiving;
            boolean creativeOrInfinity = player.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);

            int i = getUseDuration(stack) - timeLeft;
            i = onArrowLoose(stack, worldIn, player, i, !itemstack.isEmpty() || creativeOrInfinity);
            if (i < 0) return;

            if (!itemstack.isEmpty() || creativeOrInfinity) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(UGItems.DEPTHROCK_PEBBLE.get());
                }

                float f = getProjectileVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.abilities.instabuild || (itemstack.getItem() instanceof DepthrockPebbleItem && ((DepthrockPebbleItem)itemstack.getItem()).isInfinite(itemstack, stack, player));
                    if (!worldIn.isClientSide) {
                        SlingshotAmmoEntity ammoEntity = new SlingshotAmmoEntity(worldIn, entityLiving);

                        ammoEntity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, f * 3.0F, 1.0F);

                        stack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));

                        worldIn.addFreshEntity(ammoEntity);
                    }

                    worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_SHOOT.get(), SoundSource.PLAYERS, 0.5F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.abilities.instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.inventory.removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand handIn) {
        ItemStack itemstack = player.getItemInHand(handIn);
        boolean hasAmmo = !player.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = onArrowNock(itemstack, worldIn, player, handIn, hasAmmo);
        if (ret != null) return ret;

        if (!player.abilities.instabuild && !hasAmmo) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(handIn);
            worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), UGSoundEvents.SLINGSHOT_DRAW.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public static InteractionResultHolder<ItemStack> onArrowNock(ItemStack item, Level world, Player player, InteractionHand hand, boolean hasAmmo)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, item, hand, world, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return new InteractionResultHolder<>(InteractionResult.FAIL, item);
        return event.getAction();
    }

    public static int onArrowLoose(ItemStack stack, Level world, Player player, int charge, boolean hasAmmo)
    {
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, world, charge, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return -1;
        return event.getCharge();
    }

    public static float getProjectileVelocity(int charge) {
        float f = (float)charge / 5.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem().is(ItemTags.PLANKS);
    }
}