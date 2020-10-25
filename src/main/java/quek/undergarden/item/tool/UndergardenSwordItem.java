package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class UndergardenSwordItem extends SwordItem {
    public UndergardenSwordItem(IItemTier tier) {
        super(tier, 3, -2.4f, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.cloggrum_sword.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.cloggrum_sword").mergeStyle(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UGItems.froststeel_sword.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.froststeel_sword").mergeStyle(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UGItems.utheric_sword.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.utheric_sword").mergeStyle(TextFormatting.GRAY));
        }
    }

    @SubscribeEvent
    public static void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        float damage = event.getAmount();

        if(source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            if(player.getHeldItemMainhand().getItem() == UGItems.utheric_sword.get()) {
                if(event.getEntityLiving().getCreatureAttribute() == UGEntityTypes.ROTSPAWN) {
                    event.setAmount(damage * 1.4F);
                }
                else event.setAmount(damage * 1F);
            }
            else if(player.getHeldItemMainhand().getItem() == UGItems.froststeel_sword.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 4));
            }
        }
    }

}
