package quek.undergarden.item.tool;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import quek.undergarden.entity.projectile.SlingshotAmmoEntity;
import quek.undergarden.item.DepthrockPebbleItem;
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenItems;

import java.util.function.Predicate;

public class SlingshotItem extends ShootableItem {

    public static final Predicate<ItemStack> SLINGSHOT_AMMO = (stack) -> stack.getItem() == UndergardenItems.depthrock_pebble.get();

    public SlingshotItem() {
        super(new Properties()
                .maxStackSize(1)
                .maxDamage(192)
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
                .rarity(Rarity.UNCOMMON)
        );
        this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
            if (p_210310_2_ == null) {
                return 0.0F;
            } else {
                return !(p_210310_2_.getActiveItemStack().getItem() instanceof SlingshotItem) ? 0.0F : (float)(p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) / 10.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
            return p_210309_2_ != null && p_210309_2_.isHandActive() && p_210309_2_.getActiveItemStack() == p_210309_0_ ? 1.0F : 0.0F;
        });
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return SLINGSHOT_AMMO;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = getUseDuration(stack) - timeLeft;
            i = onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(UndergardenItems.depthrock_pebble.get());
                }

                float f = getArrowVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof DepthrockPebbleItem && ((DepthrockPebbleItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        DepthrockPebbleItem pebbleItem = (DepthrockPebbleItem) (itemstack.getItem() instanceof DepthrockPebbleItem ? itemstack.getItem() : UndergardenItems.depthrock_pebble.get());
                        SlingshotAmmoEntity ammoEntity = pebbleItem.createArrow(worldIn, itemstack, playerentity);
                        ammoEntity = ammo(ammoEntity);
                        ammoEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });
                        if (flag1 || playerentity.abilities.isCreativeMode) {
                            ammoEntity.pickupStatus = SlingshotAmmoEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(ammoEntity);
                    }

                    worldIn.playSound(null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

        ActionResult<ItemStack> ret = onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    public static ActionResult<ItemStack> onArrowNock(ItemStack item, World world, PlayerEntity player, Hand hand, boolean hasAmmo)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, item, hand, world, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return new ActionResult<>(ActionResultType.FAIL, item);
        return event.getAction();
    }

    public static int onArrowLoose(ItemStack stack, World world, PlayerEntity player, int charge, boolean hasAmmo)
    {
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, world, charge, hasAmmo);
        if (MinecraftForge.EVENT_BUS.post(event))
            return -1;
        return event.getCharge();
    }

    public static float getArrowVelocity(int charge) {
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
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public SlingshotAmmoEntity ammo(SlingshotAmmoEntity ammoEntity) {
        return ammoEntity;
    }
}
