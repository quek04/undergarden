package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
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
import quek.undergarden.registry.*;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class UGSwordItem extends SwordItem {
    public UGSwordItem(IItemTier tier) {
        super(tier, 3, -2.4F, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
                .rarity(isForgotten(tier))
        );
    }

    protected static Rarity isForgotten(IItemTier tier) {
        if(tier.equals(UGTools.FORGOTTEN)) {
            return UGItems.FORGOTTEN;
        }
        else return Rarity.COMMON;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.CLOGGRUM_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.cloggrum_sword").mergeStyle(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UGItems.FROSTSTEEL_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.froststeel_sword").mergeStyle(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UGItems.UTHERIC_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.utheric_sword").mergeStyle(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UGItems.FORGOTTEN_SWORD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.forgotten_sword").mergeStyle(TextFormatting.GRAY));
        }
    }

    @SubscribeEvent
    public static void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        float damage = event.getAmount();

        if(source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            if(player.getHeldItemMainhand().getItem() == UGItems.UTHERIC_SWORD.get()) {
                if(event.getEntityLiving().getType().isContained(UGTags.Entities.ROTSPAWN)) {
                    event.setAmount(damage * 1.4F);
                }
                else event.setAmount(damage * 1F);
            }
            else if(player.getHeldItemMainhand().getItem() == UGItems.FROSTSTEEL_SWORD.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 4));
            }
            else if(player.getHeldItemMainhand().getItem() == UGItems.FORGOTTEN_SWORD.get()) {
                if(event.getEntityLiving().getType().getRegistryName().getNamespace().equals("undergarden") && event.getEntityLiving().isNonBoss()) {
                    event.setAmount(damage * 2F);
                }
                else event.setAmount(damage * 1F);
            }
        }
    }

}
