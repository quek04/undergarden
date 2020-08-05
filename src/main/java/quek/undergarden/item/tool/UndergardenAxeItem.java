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
import quek.undergarden.registry.UndergardenItemGroups;
import quek.undergarden.registry.UndergardenItemTiers;
import quek.undergarden.registry.UndergardenItems;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class UndergardenAxeItem extends AxeItem {
    public UndergardenAxeItem(IItemTier tier, float attack) {
        super(tier, attack, -3.2f, new Properties()
                .maxStackSize(1)
                .defaultMaxDamage(tier.getMaxUses())
                .group(UndergardenItemGroups.GROUP)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem() == UndergardenItems.utheric_axe.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.utheric_axe").func_240701_a_(TextFormatting.GRAY));
        }
        else if(stack.getItem() == UndergardenItems.froststeel_axe.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.froststeel_sword").func_240701_a_(TextFormatting.GRAY));
        }
    }

    @SubscribeEvent
    public static void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        float damage = event.getAmount();

        if(source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            if(player.getHeldItemMainhand().getItem() == UndergardenItems.utheric_axe.get()) {
                if(event.getEntityLiving().getClassification(false) == EntityClassification.CREATURE) {
                    event.setAmount(damage * 1.5F);
                }
            }
            else if(player.getHeldItemMainhand().getItem() == UndergardenItems.froststeel_axe.get()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 3));
            }
        }
    }
}
