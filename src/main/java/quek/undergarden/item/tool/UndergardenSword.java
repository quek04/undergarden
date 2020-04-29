package quek.undergarden.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenItems;

@Mod.EventBusSubscriber
public class UndergardenSword extends SwordItem {
    public UndergardenSword(IItemTier tier) {
        super(tier, 3, -2.4f, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UndergardenItemGroups.UNDERGARDEN_GEAR)
        );
    }

    @SubscribeEvent
    public static void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        float damage = event.getAmount();

        if(source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            if(player.getHeldItemMainhand().getItem() == UndergardenItems.utheric_sword.get()) {
                if(event.getEntityLiving().getType() == UndergardenEntities.rotwalker ||event.getEntityLiving().getType() == UndergardenEntities.rotbeast || event.getEntityLiving().getType() == UndergardenEntities.rotdweller) {
                    event.setAmount(damage * 1.4F);
                }
                else event.setAmount(damage * 1F);
            }
            else if(player.getHeldItemMainhand().getItem() == UndergardenItems.froststeel_sword.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 4));
            }
        }
    }

}
