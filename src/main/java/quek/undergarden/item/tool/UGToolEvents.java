package quek.undergarden.item.tool;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGTags;

@Mod.EventBusSubscriber(modid = "undergarden")
public class UGToolEvents {

    @SubscribeEvent
    public static void forgottenAttackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if(source instanceof Player) {
            Player player = (Player) source;
            if(player.getMainHandItem().getItem() == UGItems.FORGOTTEN_SWORD.get() || player.getMainHandItem().getItem() == UGItems.FORGOTTEN_AXE.get() || player.getMainHandItem().getItem() == UGItems.FORGOTTEN_BATTLEAXE.get()) {
                if(event.getEntityLiving().getType().getRegistryName().getNamespace().equals("undergarden") && event.getEntityLiving().canChangeDimensions()) {
                    event.setAmount(damage * 2F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void forgottenDigEvent(PlayerEvent.BreakSpeed event) {
        Player player = event.getPlayer();
        BlockState state = event.getState();

        if(player.getMainHandItem().getItem() == UGItems.FORGOTTEN_PICKAXE.get() || player.getMainHandItem().getItem() == UGItems.FORGOTTEN_AXE.get() || player.getMainHandItem().getItem() == UGItems.FORGOTTEN_SHOVEL.get() || player.getMainHandItem().getItem() == UGItems.FORGOTTEN_HOE.get()) {
            if(state != null && state.getBlock().getRegistryName().getNamespace().equals("undergarden")) {
                event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
            }
        }
    }

    @SubscribeEvent
    public static void utheriumAttackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if(source instanceof Player) {
            Player player = (Player) source;
            if(player.getMainHandItem().getItem() == UGItems.UTHERIUM_SWORD.get() || player.getMainHandItem().getItem() == UGItems.UTHERIUM_AXE.get()) {
                if(event.getEntityLiving().getType().is(UGTags.Entities.ROTSPAWN)) {
                    event.setAmount(damage * 1.5F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void froststeelAttackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        if(source instanceof Player) {
            Player player = (Player) source;
            if(player.getMainHandItem().getItem() == UGItems.FROSTSTEEL_SWORD.get()) {
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 2));
            }
            if(player.getMainHandItem().getItem() == UGItems.FROSTSTEEL_PICKAXE.get()) {
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
            }
            if(player.getMainHandItem().getItem() == UGItems.FROSTSTEEL_AXE.get()) {
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 2));
            }
            if(player.getMainHandItem().getItem() == UGItems.FROSTSTEEL_SHOVEL.get()) {
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
            }
        }
    }
}