package quek.undergarden.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenItems;

@Mod.EventBusSubscriber
public class UndergardenAxe extends AxeItem {
    public UndergardenAxe(IItemTier tier) {
        super(tier, 6, -3.2f, new Properties()
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
            if(player.getHeldItemMainhand().getItem() == UndergardenItems.utheric_axe.get()) {
                if(event.getEntityLiving().getClassification(false) == EntityClassification.CREATURE) {
                    event.setAmount(damage * event.getEntityLiving().getHealth());
                }
            }
            else if(player.getHeldItemMainhand().getItem() == UndergardenItems.froststeel_axe.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 3));
            }
        }
    }
}
