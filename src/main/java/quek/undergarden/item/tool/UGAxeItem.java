package quek.undergarden.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
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
import quek.undergarden.registry.UGItemGroups;
import quek.undergarden.registry.UGItems;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class UGAxeItem extends AxeItem {
    public UGAxeItem(IItemTier tier, float attack) {
        super(tier, attack, -3.2F, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UGItemGroups.GROUP)
                .rarity(UGSwordItem.isForgotten(tier))
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UGItems.UTHERIC_AXE.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.utheric_axe").mergeStyle(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UGItems.FROSTSTEEL_AXE.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.froststeel_sword").mergeStyle(TextFormatting.GRAY));
        }
    }

    @SubscribeEvent
    public static void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        float damage = event.getAmount();

        if(source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            if(player.getHeldItemMainhand().getItem() == UGItems.UTHERIC_AXE.get()) {
                if(event.getEntityLiving().getClassification(false) == EntityClassification.CREATURE) {
                    event.setAmount(damage * 1.5F);
                }
            }
            else if(player.getHeldItemMainhand().getItem() == UGItems.FROSTSTEEL_AXE.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 3));
            }
        }
    }
}
