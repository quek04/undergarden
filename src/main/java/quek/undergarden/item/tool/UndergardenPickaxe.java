package quek.undergarden.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenItems;

@Mod.EventBusSubscriber
public class UndergardenPickaxe extends PickaxeItem {
    public UndergardenPickaxe(IItemTier tier) {
        super(tier, 1, -2.8f, new Properties()
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

            if(player.getHeldItemMainhand().getItem() == UndergardenItems.froststeel_pickaxe.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 2));
            }
        }
    }
}
